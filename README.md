
# Serviço de Cadastro de Clientes

Aplicação de cadastro de clientes desenvolvida em Springboot, com armazenamento em um banco de dados Mongo.

### Design

Foi construído utilizando-se um design de Ports and Adapters como arquitetura interna. Como arquitetura externa, ele foi construído com a idéia de ser um Microsserviço responsável pelo domínio de cadastro de clientes. 
Sendo assim, ele não possue autenticação. Isso poderia ficar a cargo de um BFF. Idealmente, ele deveria disparar eventos sempre que houvesse mudança em seus objetos de domínio, mas como o ambiente fictício em que esse microsserviço seria implantado é nebuloso, optei por não fazê-lo nesse MVP.
Optou-se por não haver delete, pois a deleção física de um cliente parece problemático, e o approach de expor como DELETE na api Rest uma deleção que na verdade é lógica leva os usuários à uma interpretação errada da funcionalidade.
Por se tratar de um MVP, optei por não desenvolver paginação.

### Funcionamento

A aplicação em si está dentro da pasta `/service`.
A imagem docker da aplicação foi construída usando-se um plugin do gradle (o comando `./gradlew build docker`), o que irá criar a imagem `rabbithole/customers:1.0.0-SNAPSHOT`.
Eu estou hospedando essa imagem no Docker Hub como `rngouveia/consumers:latest`.
Há um arquivo `docker-compose.yml` na raíz do projeto que executa o container da aplicação, um container mongo e um container do mongo-express (apenas pra olhar os dados do bd diretamente).
Na instância ec2 da AWS há apenas esse arquivo `docker-compose.yml`. O comando `docker-compose up` baixa todas as dependencias do Docker Hub e as executa.

### Qualidade

Foram desenvolvidos testes unitários da camada de api e de negócio, assim como testes de integração para a camada de infraestrutura e de ciclo de vida do objeto de domínio.
Foi usado o Jacoco para verificar cobertura de código. A aplicação possui 100% de cobertura.


### Observações
1. Os dados do Mongo dockerizado não estão realmente persistentes. Seria preciso registrar um volume externo ao container no docker-compose para que os dados não fossem perdidos quando o container é parado.
2. Eu ia rodar um servidor do sonar a partir de um docker para validar a qualidade do código, mas não tive tempo.
3. Idealmente se utilizaria algum sistema de versionamento dos dados (Flyway por exemplo), mas não tive tempo de fazê-lo. 
4. O processo de "build" e "implantação" está bastante manual e laboroso. Idealmente se utilizariam ferramentas de automação como Ansible ou Terraform, assim como o Jenkins para isso.
5. Idealmente em um ambiente de produção, deveria haver ferramentes que integram os logs.
6. Idealmente em um ambiente de produção, a aplicação em si deveria disparar métricas, e deveriam haver ferramentas de coleta delas. No momento, há apenas as métricas do container da aws.

