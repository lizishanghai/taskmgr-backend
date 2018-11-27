## Building a RESTful Web Service
from scratch:
⁃	Build with Gradle 
⁃	Create the directory structure
⁃	Create a Gradle build file

skip the basics
⁃	git clone https://github.com/spring-guides/gs-rest-service.git
⁃	Create a resource representation class, a resource controller

Build an executable JAR
⁃	using Maven :  run the application using ./mvnw spring-boot:run
⁃	Or you can build the JAR file with ./mvnw clean package. Then run the JAR file: java -jar target/gs-rest-service-0.1.0.jar

Test the service  http://localhost:8080/greeting
	//进程要开着 


## MongoDB 支撑的 API

MongoDB 的集成

安装 MongoDB

建议使用 docker 进行安装: docker pull mongo 

命令可以查看已经拉取的镜像列表， docker images 

启动容器了，映射 �MongoDB 默认端口 27017 到本地的 27017: 
  $ docker run -p 27017:27017 --name mymongo -d mongo:latest 

如果要测试一下的话，可以通过以下命令登录到容器: 
  $ docker exec -it mymongo bash

配置 Spring Boot 使用 MongoDB


========================

## Accessing Data with MongoDB

To start from scratch, move on to Build with Gradle.

To skip the basics, do the following:  

  $ git clone https://github.com/spring-guides/gs-accessing-data-mongodb.git
  Install and launch MongoDB

Install and launch MongoDB
  $ brew install mongodb
  After you install MongoDB, launch it in a console window. This command also starts up a server process.
  $ mongod

Define a simple entity
  src/main/java/hello/Customer.java

Create simple queries
  src/main/java/hello/CustomerRepository.java

Create an Application class
  src/main/java/hello/Application.java

Build an executable JAR
  $ ./gradlew bootRun
  $ ./mvnw spring-boot:run // ok



##
![alt text](https://firebasestorage.googleapis.com/v0/b/test001-lizhang.appspot.com/o/demos%2F1543294178151.jpg?alt=media&token=dbbd8e5e-c886-4e02-8e9f-619bee03d6f1)

![alt text](https://firebasestorage.googleapis.com/v0/b/test001-lizhang.appspot.com/o/demos%2F1543295211033.jpg?alt=media&token=b1bf9c88-596f-4a95-80c5-a0f44fa062d7)


:spring_version: current
:toc:
:project_id: gs-rest-service
:spring_version: current
:spring_boot_version: 2.0.5.RELEASE
:icons: font
:source-highlighter: prettify

This guide walks you through the process of creating a "hello world" link:/understanding/REST[RESTful web service] with Spring.

== What you'll build

You'll build a service that will accept HTTP GET requests at:

----
http://localhost:8080/greeting
----

and respond with a link:/understanding/JSON[JSON] representation of a greeting:

[source,json]
----
{"id":1,"content":"Hello, World!"}
----

You can customize the greeting with an optional `name` parameter in the query string:

----
http://localhost:8080/greeting?name=User
----

The `name` parameter value overrides the default value of "World" and is reflected in the response:

[source,json]
----
{"id":1,"content":"Hello, User!"}
----


== What you'll need

:java_version: 1.8
include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/prereq_editor_jdk_buildtools.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/how_to_complete_this_guide.adoc[]


include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/hide-show-gradle.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/hide-show-maven.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/hide-show-sts.adoc[]



[[initial]]
== Create a resource representation class

Now that you've set up the project and build system, you can create your web service.

Begin the process by thinking about service interactions.

The service will handle `GET` requests for `/greeting`, optionally with a `name` parameter in the query string. The `GET` request should return a `200 OK` response with JSON in the body that represents a greeting. It should look something like this:

[source,json]
----
{
    "id": 1,
    "content": "Hello, World!"
}
----

The `id` field is a unique identifier for the greeting, and `content` is the textual representation of the greeting.

To model the greeting representation, you create a resource representation class. Provide a plain old java object with fields, constructors, and accessors for the `id` and `content` data:
List
`src/main/java/hello/Greeting.java`
[source,java]
----
include::complete/src/main/java/hello/Greeting.java[]
----

NOTE: As you see in steps below, Spring uses the https://github.com/FasterXML/jackson[Jackson JSON] library to automatically marshal instances of type `Greeting` into JSON.

Next you create the resource controller that will serve these greetings.


== Create a resource controller

In Spring's approach to building RESTful web services, HTTP requests are handled by a controller. These components are easily identified by the http://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/web/bind/annotation/RestController.html[`@RestController`] annotation, and the `GreetingController` below handles `GET` requests for `/greeting` by returning a new instance of the `Greeting` class:

`src/main/java/hello/GreetingController.java`
[source,java]
----
include::complete/src/main/java/hello/GreetingController.java[]
----

This controller is concise and simple, but there's plenty going on under the hood. Let's break it down step by step.

The `@RequestMapping` annotation ensures that HTTP requests to `/greeting` are mapped to the `greeting()` method.

NOTE: The above example does not specify `GET` vs. `PUT`, `POST`, and so forth, because `@RequestMapping` maps all HTTP operations by default. Use `@RequestMapping(method=GET)` to narrow this mapping.

`@RequestParam` binds the value of the query string parameter `name` into the `name` parameter of the `greeting()` method. If the `name` parameter is absent in the request, the `defaultValue` of "World" is used.

The implementation of the method body creates and returns a new `Greeting` object with `id` and `content` attributes based on the next value from the `counter`, and formats the given `name` by using the greeting `template`.

A key difference between a traditional MVC controller and the RESTful web service controller above is the way that the HTTP response body is created. Rather than relying on a link:/understanding/view-templates[view technology] to perform server-side rendering of the greeting data to HTML, this RESTful web service controller simply populates and returns a `Greeting` object. The object data will be written directly to the HTTP response as JSON.

This code uses Spring 4's new http://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/web/bind/annotation/RestController.html[`@RestController`] annotation, which marks the class as a controller where every method returns a domain object instead of a view. It's shorthand for `@Controller` and `@ResponseBody` rolled together.

The `Greeting` object must be converted to JSON. Thanks to Spring's HTTP message converter support, you don't need to do this conversion manually. Because http://wiki.fasterxml.com/JacksonHome[Jackson 2] is on the classpath, Spring's http://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html[`MappingJackson2HttpMessageConverter`] is automatically chosen to convert the `Greeting` instance to JSON.


== Make the application executable

Although it is possible to package this service as a traditional link:/understanding/WAR[WAR] file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application. You package everything in a single, executable JAR file, driven by a good old Java `main()` method. Along the way, you use Spring's support for embedding the link:/understanding/Tomcat[Tomcat] servlet container as the HTTP runtime, instead of deploying to an external instance.


`src/main/java/hello/Application.java`
[source,java]
----
include::complete/src/main/java/hello/Application.java[]
----

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/spring-boot-application.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/build_an_executable_jar_subhead.adoc[]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/build_an_executable_jar_with_both.adoc[]


Logging output is displayed. The service should be up and running within a few seconds.


== Test the service

Now that the service is up, visit http://localhost:8080/greeting, where you see:

[source,json]
----
{"id":1,"content":"Hello, World!"}
----

Provide a `name` query string parameter with http://localhost:8080/greeting?name=User. Notice how the value of the `content` attribute changes from "Hello, World!" to "Hello, User!":

[source,json]
----
{"id":2,"content":"Hello, User!"}
----

This change demonstrates that the `@RequestParam` arrangement in `GreetingController` is working as expected. The `name` parameter has been given a default value of "World", but can always be explicitly overridden through the query string.

Notice also how the `id` attribute has changed from `1` to `2`. This proves that you are working against the same `GreetingController` instance across multiple requests, and that its `counter` field is being incremented on each call as expected.


== Summary

Congratulations! You've just developed a RESTful web service with Spring.

== See Also

The following guides may also be helpful:

* https://spring.io/guides/gs/accessing-gemfire-data-rest/[Accessing GemFire Data with REST]
* https://spring.io/guides/gs/accessing-mongodb-data-rest/[Accessing MongoDB Data with REST]
* https://spring.io/guides/gs/accessing-data-mysql/[Accessing data with MySQL]
* https://spring.io/guides/gs/accessing-data-rest/[Accessing JPA Data with REST]
* https://spring.io/guides/gs/accessing-neo4j-data-rest/[Accessing Neo4j Data with REST]
* https://spring.io/guides/gs/consuming-rest/[Consuming a RESTful Web Service]
* https://spring.io/guides/gs/consuming-rest-angularjs/[Consuming a RESTful Web Service with AngularJS]
* https://spring.io/guides/gs/consuming-rest-jquery/[Consuming a RESTful Web Service with jQuery]
* https://spring.io/guides/gs/consuming-rest-restjs/[Consuming a RESTful Web Service with rest.js]
* https://spring.io/guides/gs/securing-web/[Securing a Web Application]
* https://spring.io/guides/tutorials/bookmarks/[Building REST services with Spring]
* https://spring.io/guides/tutorials/react-and-spring-data-rest/[React.js and Spring Data REST]
* https://spring.io/guides/gs/spring-boot/[Building an Application with Spring Boot]
* https://spring.io/guides/gs/testing-restdocs/[Creating API Documentation with Restdocs]
* https://spring.io/guides/gs/rest-service-cors/[Enabling Cross Origin Requests for a RESTful Web Service]
* https://spring.io/guides/gs/rest-hateoas/[Building a Hypermedia-Driven RESTful Web Service]
* https://spring.io/guides/gs/circuit-breaker/[Circuit Breaker]

include::https://raw.githubusercontent.com/spring-guides/getting-started-macros/master/footer.adoc[]
