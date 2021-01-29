# Getting Started

Backend service for executing Jdbc SQL through REST API.

### Guides

#### Build and Start

```
$ ./mvnw package
$ cd target
$ unzip sql-bridge-x.y.z-bin.zip
$ cd sql-bridge-x.y.z
$ ./sql-bridge.sh
```

#### Configuration

- `application.properties`

```
server.port=8001
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:~/database/test;AUTO_SERVER=TRUE;CACHE_SIZE=131072
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jdbc.template.max-rows=1000
```

- Put external jdbc library files in `lib` directory

#### Excuting SQL

```
POST http://[host]:[port]/sql/query
```

#### Use h2 db console

Access `http://[host]:[port]/h2-console` in web browser
