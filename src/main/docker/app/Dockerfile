FROM openjdk:8u131-jre-alpine

MAINTAINER Felipe Di Bernardi S Thiago "felipe.dibernardi@organizafacil.com"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY maven/condominium.jar condominium.jar

CMD ["java", "-jar", "condominium.jar"]