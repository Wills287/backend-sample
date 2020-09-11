FROM openjdk:11.0.2-jre-slim

ENV APPLICATION=backend-sample

WORKDIR /home/
VOLUME /tmp

ADD target/$APPLICATION.jar /home/$APPLICATION.jar

EXPOSE 8080

CMD java -jar /home/$APPLICATION.jar
