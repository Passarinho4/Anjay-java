FROM openjdk:8 AS builder

WORKDIR /app
RUN ls
RUN apt-get update
RUN apt-get -y install git build-essential cmake libmbedtls-dev zlib1g-dev
RUN apt-get -y install python3.7

COPY .  .
RUN git submodule update --init
RUN ./gradlew :demo:build


FROM openjdk:8
RUN ls
RUN apt-get update
RUN apt-get -y install git build-essential cmake libmbedtls-dev zlib1g-dev
RUN apt-get -y install python3.7
COPY --from=builder /app .
COPY run.sh .
RUN chmod 777 run.sh
RUN ls
CMD ["./run.sh"]

