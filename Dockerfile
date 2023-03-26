FROM eclipse-temurin:18-alpine
RUN mkdir /opt/app
COPY ./target/discord-metrics-collector-0.0.1-jar-with-dependencies.jar /opt/app
ENV api_key=
ENV db_url=
ENV db_username=
ENV db_password=
CMD ["sh", "-c", "java -jar \"/opt/app/discord-metrics-collector-0.0.1-jar-with-dependencies.jar\" --discord.apiKey=${api_key} --spring.datasource.url=\"${db_url}\" --spring.datasource.username=${db_username} --spring.datasource.passsword=${db_password}"]