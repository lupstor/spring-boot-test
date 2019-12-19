# spring-jpa-test

Create oracle user

ORACLE

https://docs.oracle.com/cd/E17781_01/admin.112/e18585/toc.htm#XEGSG110


JSF
https://www.javacodegeeks.com/2018/03/jsf-2-0-tutorial-for-beginners.html

JPA

https://www.objectdb.com/api/java/jpa/ManyToOne
https://www.javaworld.com/article/2077819/understanding-jpa-part-2-relationships-the-jpa-way.html
https://www.javacodegeeks.com/2013/04/jpa-determining-the-owning-side-of-a-relationship.html
https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.named-parameters

https://www.baeldung.com/spring-data-jpa-query
https://medium.com/swlh/spring-data-jpa-projection-support-for-native-queries-a13cd88ec166

SPRING MVC

https://github.com/eugenp/tutorials/blob/master/spring-boot-crud/src/main/resources/templates/update-user.html
https://github.com/springframeworkguru/springbootwebapp/blob/master/src/main/java/guru/springframework/controllers/ProductController.java

SPRING SECURITY
https://riptutorial.com/es/spring-security/example/17848/spring-security-usando-spring-boot-y-jdbc-authentication
https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#jc-authentication-jdbc
https://www.javabullets.com/auto-generating-spring-security-tutorial-default-jdbc-realms/
https://github.com/cruizg93/SpringBoot-Security-MySql/blob/master/src/main/java/com/cristianruizblog/loginSecurity/service/UserDetailsServiceImpl.java



POM
<project xmlns="http://maven.apache.org/POM/4.0.0"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.sib</groupId>
       <artifactId>herbertest</artifactId>
       <packaging>war</packaging>
       <version>0.0.1-SNAPSHOT</version>
       <name>herbertest Maven Webapp</name>
       <url>http://maven.apache.org</url>
       <dependencies>
              <dependency>
                     <groupId>junit</groupId>
                     <artifactId>junit</artifactId>
                     <version>3.8.1</version>
                     <scope>test</scope>
              </dependency>
              <dependency>
                     <groupId>com.sun.faces</groupId>
                     <artifactId>jsf-api</artifactId>
                     <version>2.2.20</version>
              </dependency>
              <dependency>
                     <groupId>com.sun.faces</groupId>
                     <artifactId>jsf-impl</artifactId>
                     <version>2.2.20</version>
              </dependency>
              <dependency>
                     <groupId>javax.servlet</groupId>
                     <artifactId>javax.servlet-api</artifactId>
                     <version>3.0.1</version>
              </dependency>
       </dependencies>
       <build>
              <finalName>herbertest</finalName>
       </build>
</project>

WEB.XML
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://java.sun.com/xml/ns/javaee"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
    id="WebApp_ID" version="3.0">
    <display-name>herbertest</display-name>
    <context-param>
        <param-name>javax.faces.PROJECT_STAGE</param-name>
        <param-value>Development</param-value>
    </context-param>
    <servlet>
        <servlet-name>Faces Servlet</servlet-name>
        <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.jsf</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.faces</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>Faces Servlet</servlet-name>
        <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>index.jsf</welcome-file>
    </welcome-file-list>
</web-app>

XHTML HEADER

<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core">
 