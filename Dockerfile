FROM eclipse-temurin:18-alpine
RUN mkdir /opt/app
COPY ./target/discord-metrics-collector-0.0.1-jar-with-dependencies.jar /opt/app
ENV api_key=
CMD ["sh", "-c", "java -jar \"/opt/app/discord-metrics-collector-0.0.1-jar-with-dependencies.jar\" ${api_key}"]