# 1단계: 빌드 스테이지
FROM gradle:7.5.1-jdk17 AS build
WORKDIR /app
COPY . .
# gradlew 파일에 실행 권한을 부여
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

# 2단계: 실행 스테이지
FROM openjdk:17
LABEL authors="bella.yk <shim09@airport.co.kr>"

RUN mkdir -p /app
WORKDIR /app
ENV USE_PROFILE prod

# 빌드된 JAR 파일을 복사
COPY --from=build /app/build/libs/*.jar /app/main.jar

EXPOSE 20000
ENTRYPOINT [ "java","-Dspring.profiles.active=${USE_PROFILE}", "-jar", "/app/main.jar" ]

