FROM alpine:edge

MAINTAINER customer.com

RUN apk add --no-cache openjdk8

EXPOSE 8020 8120

VOLUME /logs

WORKDIR /

COPY /build/libs/cars-1.0.jar /customer.jar

ENTRYPOINT exec java -jar /customer.jar