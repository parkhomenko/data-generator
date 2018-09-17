## Create a jar
```shell
mvn clean package
```

## Generate users
```shell
java -jar ./target/generator-0.0.1-SNAPSHOT.jar \
    --spring.profiles.active=usersLoader \
    --spring.datasource.url=jdbc:postgresql://localhost:5432/postgres \
    --spring.datasource.username=secret \
    --spring.datasource.password=secret \
    --com.data.generator.numberOfUsers=1000 \
    --com.data.generator.numberOfUsers.batch-size=1000
```
    
## Generate mails
```shell
java -jar ./target/generator-0.0.1-SNAPSHOT.jar \
    --spring.profiles.active=mailsLoader \
    --spring.datasource.url=jdbc:postgresql://localhost:5432/postgres \
    --spring.datasource.username=secret \
    --spring.datasource.password=secret \
    --com.data.generator.numberOfMails=1000 \
    --com.data.generator.numberOfMails.batch-size=1000000
```
