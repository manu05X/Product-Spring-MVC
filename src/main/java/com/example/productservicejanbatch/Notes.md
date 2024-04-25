## Adding DataBase to Project
- Add the below dependencies in pom.xml
- Add Spring data JPA maven URL - https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa/3.2.2
  
        <!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa -->
       <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-jpa</artifactId>
            <version>3.2.2</version>
        </dependency>
- MySql connector Maven URL-> https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.3.0

        <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.3.0</version>
        </dependency>
- To connect a database we need URL, userName, password and dataBase we want to connect.
  - But JPA does it by adding dependencies in resource folder in application.properties
  - https://spring.io/guides/gs/accessing-data-mysql

          
              #Connection to Mysql DB,
              spring.jpa.hibernate.ddl-auto=update
              spring.datasource.url=jdbc:mysql://localhost:3306/janproductservice
              spring.datasource.username=janproductservice
              spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
              spring.jpa.show-sql=true
              spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

- Now we need to make table so JPA does it automatically but we have to mark our model i.e Product, Category, BaseModel class as @Entity and even mark the primary key as @Id.
- Make sure that we does not have any name in our model as key word in SQL.
- This will make the table for us in database automatically

- Some important links 
  - https://www.baeldung.com/hibernate-identifiers
  - https://spring.io/guides/gs/accessing-data-jpa
  - https://spring.io/guides/gs/accessing-data-mysql


