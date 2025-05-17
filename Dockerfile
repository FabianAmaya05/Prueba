FROM openjdk:17
COPY "./target/ProyectoBrayan-1.jar" "app.jar"
EXPOSE 8113
ENTRYPOINT ["java","-jar","/app.jar"]
