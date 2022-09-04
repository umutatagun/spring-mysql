FROM alpine:3.12

LABEL "maintainer"="UmutAtgn <umutatgn@gmail.com>"
LABEL "description"="Demo Bank Project"

RUN apk update && apk add openjdk11
ARG APP=app.jar
WORKDIR /app
COPY target/ubank-0.0.1-SNAPSHOT.jar ${APP}
ENTRYPOINT ["java","-jar", "app.jar"]
