# ---- Build stage ----
FROM eclipse-temurin:11-jdk AS build
RUN apt-get update && apt-get install -y wget ca-certificates && \
    wget -qO /tmp/sbt.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-1.9.9.deb && \
    apt-get install -y /tmp/sbt.deb && rm -f /tmp/sbt.deb && sbt sbtVersion
WORKDIR /app
COPY . .
RUN sbt clean stage

# ---- Runtime stage ----
FROM eclipse-temurin:11-jre
WORKDIR /opt/app
COPY --from=build /app/target/universal/stage /opt/app
ENV PLAY_SECRET=changeme PORT=8080
EXPOSE 8080
CMD ["/opt/app/bin/notilytics", \
     "-Dplay.http.secret.key=${PLAY_SECRET}", \
     "-Dhttp.port=${PORT}", \
     "-Dhttp.address=0.0.0.0", \
     "-Dplay.server.pidfile.path=/dev/null"]
