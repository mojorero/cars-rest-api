FROM docker.io/library/openjdk:17
COPY build/libs/app.jar app.jar
RUN groupadd --system spring && useradd --system spring --gid spring
USER spring:spring
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
