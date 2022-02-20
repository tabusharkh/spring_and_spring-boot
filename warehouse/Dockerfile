FROM openjdk:11
EXPOSE 8080
ADD target/warehouse-jar.jar warehouse-jar.jar
ENTRYPOINT ["java", "-jar", "/warehouse-jar.jar"]