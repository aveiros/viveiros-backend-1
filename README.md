## Overview
This is a quick exercise/challenge that I made recently, which is far from production ready, but enough to complete in short timeframe.

The exercise included:
1) Setting up a login page
2) A members area
3) Allow uses to login and logout

Took the following design choices to simplify the development:
1) The api authentication is using Server Side Cookies (stateful). I'm manually performing the authentication. I trust this is good enough for this exercise.
For a production application I would probably use Spring Security which is a well tested authentication library/middleware. I would also provide multiple ways of
authenticating against the api, i.e. Basic Auth/API Keys/Bearer Tokens/Cookies (stateless).

2) The front end are separate pages and used the backend to render the proper routes. Ideally building a Single Page Application would
be the preferable choice.

3) Use Spring JPA to simplify the Database side. The DB and tables are created on boot.

## Build
Please execute the following command to build the project image:
```
mvn spring-boot:build-image
```

You will need the following dependencies: Java 11, Maven & Docker.

## Run
#### Step 1: Run the stack
The following command will run the stack using docker-compose
```
docker-compose up -d
```

#### Step 2 - Insert data
Insert some data into the database
```
docker exec -it viveiros-backend-1_mysql_1 sh
mysql -uroot -proot-secret-pw
use db
insert into user (email, password) VALUES('user1@test.com', '$2a$10$9LGA4ryq4ZSNYbQAR/NaKOJ.rupOwBXNg.OVC61R2HyE3xkHSKSqi');
```

#### Step 3 - Use the application
Available at
```
http://localhost:8080/
```

Test user is
```
email: user1@test.com
password: 12345
```