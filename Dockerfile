FROM gcr.io/distroless/java:11

COPY target/bpdts-api-test*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]