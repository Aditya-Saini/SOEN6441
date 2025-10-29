# ---- Build stage ----
FROM eclipse-temurin:8-jdk as build
WORKDIR /app
COPY . .
RUN ./sbt clean stage

# ---- Run stage ----
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
