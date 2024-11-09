FROM amazoncorretto:21
LABEL authors="tolan"

WORKDIR /app

COPY ./target/TestRSS-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","TestRSS-0.0.1-SNAPSHOT.jar"]