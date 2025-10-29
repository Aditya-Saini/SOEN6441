# ---- Build stage ----
FROM eclipse-temurin:8-jdk AS build
WORKDIR /app

# Install sbt from a .deb (avoids repo/GPG issues)
RUN apt-get update && apt-get install -y wget ca-certificates && \
    wget -qO /tmp/sbt.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-1.9.9.deb && \
    apt-get install -y /tmp/sbt.deb || (apt-get -f install -y && apt-get install -y /tmp/sbt.deb) && \
    rm -f /tmp/sbt.deb && \
    sbt sbtVersion

# Copy project and build a staged distribution
COPY . .
RUN sbt clean stage

# ---- Runtime stage ----
FROM eclipse-temurin:8-jre
WORKDIR /opt/app

# Copy the staged Play app (already compiled)
COPY --from=build /app/target/universal/stage /opt/app

# Railway will inject PORT; set defaults for local run
ENV PLAY_SECRET=changeme
ENV PORT=8080

EXPOSE 8080

# Start Play (frontend + backend served from same process)
CMD ["/opt/app/bin/notilytics", \
     "-Dplay.http.secret.key=${PLAY_SECRET}", \
     "-Dhttp.port=${PORT}", \
     "-Dhttp.address=0.0.0.0", \
     "-Dplay.server.pidfile.path=/dev/null"]
