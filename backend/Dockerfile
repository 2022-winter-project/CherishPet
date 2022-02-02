FROM openjdk:11-jre-slim

ENV APP_HOME=/backend/

WORKDIR $APP_HOME

COPY /backend/build/libs/backend-0.0.1-SNAPSHOT.jar .

CMD java -jar backend-0.0.1-SNAPSHOT.jar

EXPOSE 8080