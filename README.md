
## Accessing Data with MongoDB

## Setup
    To start from scratch, move on to Build with Gradle.

    To skip the basics, do the following:  

      $ git clone https://github.com/spring-guides/gs-accessing-data-mongodb.git
## Install and launch MongoDB

      $ brew install mongodb
      
  After you install MongoDB, launch it in a console window. This command also starts up a server process.
      
      $ mongod

## Define a simple entity
     src/main/java/hello/Customer.java

## Create simple queries
     src/main/java/hello/CustomerRepository.java

## Create an Application class
    src/main/java/hello/Application.java

## Build an executable JAR
      $ ./gradlew bootRun
      $ ./mvnw spring-boot:run // ok



## Demo
![alt text](https://firebasestorage.googleapis.com/v0/b/test001-lizhang.appspot.com/o/demos%2Fgtm-byli%2F1543350498901.jpg?alt=media&token=e86aa91f-b027-4192-b455-3a7968eefdc1)
![alt text](https://firebasestorage.googleapis.com/v0/b/test001-lizhang.appspot.com/o/demos%2Fgtm-byli%2F1543350557071.jpg?alt=media&token=a6db95b5-1098-4b2a-aac7-1c1e663ea2a6)
![alt text](https://firebasestorage.googleapis.com/v0/b/test001-lizhang.appspot.com/o/demos%2Fgtm-byli%2F1543350586008.jpg?alt=media&token=f46e12d0-b2ec-42e7-9e81-41a59f1cbf07)

## 
## MongoDB的集成


## 安装 MongoDB。 建议使用 docker 进行安装: 
    docker pull mongo 

## 查看已经拉取的镜像列表， 
    docker images 

## 启动容器 
     映射MongoDB 默认端口 27017 到本地的 27017:
     $ docker run -p 27017:27017 --name mymongo -d mongo:latest 

## 登录容器: 
    如果要测试一下的话，可以通过以下命令登录到容器
    $ docker exec -it mymongo bash

## 添加数据
    $ curl -i -X POST -H "Content-Type:application/json" -d "{  \"firstName\" : \"Frodo\",  \"lastName\" : \"Baggins\" }" http://localhost:8080/people




