# syntax=docker/dockerfile:1

FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /workspace

COPY gradlew settings.gradle.kts gradle.properties ./
COPY gradle gradle
COPY buildSrc buildSrc
COPY app/build.gradle.kts app/
COPY utils/build.gradle.kts utils/

RUN --mount=type=cache,target=/root/.gradle \
    chmod +x gradlew \
    && ./gradlew :app:dependencies --no-daemon

COPY app/src app/src
COPY utils/src utils/src

RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew :app:installDist --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN addgroup -S photoflow && adduser -S photoflow -G photoflow \
    && mkdir -p /data \
    && chown photoflow:photoflow /data

COPY --from=build --chown=photoflow:photoflow /workspace/app/build/install/app/ ./

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0"

USER photoflow

EXPOSE 8080

ENTRYPOINT ["./bin/app"]
CMD ["--database-url=jdbc:sqlite:/data/pipelines.db"]
