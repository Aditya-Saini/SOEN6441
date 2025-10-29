# ---- Build stage ----
FROM eclipse-temurin:11-jdk AS build
WORKDIR /app

# Install sbt from .deb (simpler + reliable)
RUN apt-get update && apt-get install -y wget ca-certificates && \
    wget -qO /tmp/sbt.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-1.9.9.deb && \
    apt-get install -y /tmp/sbt.deb && rm -f /tmp/sbt.deb

# Copy project files
COPY . .

# Build the Play distribution (note the sbt.rootdir flag)
RUN sbt -Dsbt.rootdir=true clean stage

# ---- Runtime stage ----
FROM eclipse-temurin:11-jre
WORKDIR /opt/app
COPY --from=build /app/target/universal/stage /opt/app

# Environment
ENV PORT=8080
EXPOSE 8080

# Start Play (frontend + backend in same process)
CMD ["/opt/app/bin/notilytics", \
     "-Dplay.http.secret.key=f29d1d6e40a87a5b38d5e2a1cf41b96f4acb8c56e96d64fbac3ad594a6924b32", \
     "-Dhttp.port=8080", \
     "-Dhttp.address=0.0.0.0", \
     "-Dplay.server.pidfile.path=/dev/null"]
