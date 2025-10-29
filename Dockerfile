# ---- Build stage ----
    FROM eclipse-temurin:8-jdk as build

    # Install sbt
    RUN apt-get update && apt-get install -y curl gnupg && \
        echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list && \
        curl -sL https://keyserver.ubuntu.com/pks/lookup?op=get\&search=0x99E82A75642AC823 | apt-key add && \
        apt-get update && apt-get install -y sbt && \
        sbt sbtVersion
    
    # Set workdir and copy project
    WORKDIR /app
    COPY . .
    
    # Build the Play distribution (staged)
    RUN sbt clean stage
    
    # ---- Runtime stage ----
    FROM eclipse-temurin:8-jre
    
    WORKDIR /opt/app
    COPY --from=build /app/target/universal/stage /opt/app
    
    ENV PLAY_SECRET=changeme
    ENV PORT=8080
    
    EXPOSE 8080
    
    CMD ["/opt/app/bin/notilytics", \
         "-Dplay.http.secret.key=${PLAY_SECRET}", \
         "-Dhttp.port=${PORT}", \
         "-Dhttp.address=0.0.0.0", \
         "-Dplay.server.pidfile.path=/dev/null"]
    