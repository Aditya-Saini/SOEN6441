# ---- Build stage ----
FROM eclipse-temurin:8-jdk AS build

# Install sbt via official repo (keyring method)
RUN apt-get update && apt-get install -y curl gnupg2 apt-transport-https ca-certificates && \
    curl -sL https://repo.scala-sbt.org/scalasbt/debian/scalasbt.org.gpg \
      | gpg --dearmor | tee /usr/share/keyrings/scalasbt-archive-keyring.gpg >/dev/null && \
    echo "deb [signed-by=/usr/share/keyrings/scalasbt-archive-keyring.gpg] https://repo.scala-sbt.org/scalasbt/debian all main" \
      | tee /etc/apt/sources.list.d/sbt.list && \
    apt-get update && apt-get install -y sbt && \
    sbt sbtVersion

WORKDIR /app
COPY . .
RUN sbt clean stage

# ---- Runtime stage ----
FROM eclipse-temurin:8-jre
WORKDIR /opt/app
COPY --from=build /app/target/universal/stage /opt/app
ENV PLAY_SECRET=changeme PORT=8080
EXPOSE 8080
CMD ["/opt/app/bin/notilytics", \
     "-Dplay.http.secret.key=${PLAY_SECRET}", \
     "-Dhttp.port=${PORT}", \
     "-Dhttp.address=0.0.0.0", \
     "-Dplay.server.pidfile.path=/dev/null"]
