FROM openjdk:17-jdk-alpine

WORKDIR /usr/src/app

COPY parkinglot.jar ./

CMD ["java", "-jar", "parkinglot.jar"]
