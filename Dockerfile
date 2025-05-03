FROM eclipse-temurin:24.0.1_9-jre
ARG JAR_FILE
ARG JAR_NAME
ADD ${JAR_FILE} /opt/rest.petclinic/${JAR_NAME}
EXPOSE 10000
ENV ENV_JAR_NAME=$JAR_NAME
ENTRYPOINT java -jar /opt/rest.petclinic/${ENV_JAR_NAME}