FROM alpine/java:21-jdk AS builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app
COPY ./msvc-cursos/.mvn ./.mvn
COPY ./msvc-cursos/mvnw .
COPY ./msvc-cursos/pom.xml .

# Estos 2 RUN son hacen lomismo usar cualquiera de los 2:
# RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./msvc-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM alpine/java:21-jdk

WORKDIR /app

# Creamos un directorio para guardar logs
RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/msvc-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002


# ENTRYPOINT ["java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]
