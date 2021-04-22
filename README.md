
# Serviço de Cadastro de Clientes

Aplicação de cadastro de clientes desenvolvida em Springboot usando-se Webflux, com armazenamento em um banco de dados Mongo.

### Design

Foi construído utilizando-se um design de Ports and Adapters como arquitetura interna. Como arquitetura externa, ele foi construído com a idéia de ser um Microsserviço responsável pelo domínio de cadastro de clientes. Sendo assim, ele não possue autenticação. Isso poderia ficar a cargo de um BFF. Idealmente, ele deveria disparar eventos sempre que houvesse mudança em seus objetos de domínio, mas como o ambiente fictício em que esse microsserviço seria implantado é nebuloso, optei por não fazê-lo nesse MVP.
Optou-se por não haver delete, pois a deleção física de um cliente parece problemático, e o approach de expor como DELETE na api Rest uma deleção que na verdade é lógica leva os usuários à uma interpretação errada da funcionalidade.
Por se tratar de um MVP, optei por não desenvolver paginação.

### Funcionamento

A aplicação em si está dentro da pasta `/service`.
A imagem docker da aplicação foi construída usando-se um plugin do gradle (o comando `./gradlew build docker`), o que irá criar a imagem `rabbithole/customers:1.0.0-SNAPSHOT`.
Eu estou hospedando essa imagem no Docker Hub como `rngouveia/consumers:latest`.

Para rodar a aplicação, basta executar o comando `docker-compose up` na pasta `customers` em um ambiente com docker. 
Isso irá baixar todas as dependências necessárias do Docker Hub e executá-las.
Após executar o comando, a api pode ser acessada através do endpoint `localhost:8080/swagger-ui.html`.


### Qualidade

Foram desenvolvidos testes unitários da camada de api e de negócio, assim como testes de integração para a camada de infraestrutura e de ciclo de vida do objeto de domínio.
Foi usado o Jacoco para verificar cobertura de código. A aplicação possui 100% de cobertura. 
Foi utilizado um servidor Sonarqube dockerizado local para analisar a qualidade do código. Ele está com 'A' em todos os quesitos. 


### Observações
1. Os dados do Mongo dockerizado não estão realmente persistentes. Seria preciso registrar um volume externo ao container no docker-compose para que os dados não fossem perdidos quando o container é parado.
2. Idealmente se utilizaria algum sistema de versionamento dos dados (Flyway por exemplo), mas não tive tempo de fazê-lo. 
3. O processo de "build" e "implantação" está bastante manual e laboroso. Idealmente se utilizariam ferramentas de automação como Ansible ou Terraform, assim como o Jenkins para isso.
4. Idealmente em um ambiente de produção, deveria haver ferramentes que integram os logs.
5. Idealmente em um ambiente de produção, a aplicação em si deveria disparar métricas, e deveriam haver ferramentas de coleta delas. No momento, há apenas as métricas do container da aws.

