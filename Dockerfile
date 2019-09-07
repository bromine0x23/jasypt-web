ARG BUILDER_IMAGE=maven:3-jdk-11
ARG RUNNER_IMAGE=adoptopenjdk/openjdk11:jre

FROM $BUILDER_IMAGE as builder

WORKDIR /workspace

COPY . ./
RUN mvn --batch-mode \
        --errors \
        --define maven.test.skip=true \
        --define java.awt.headless=true \
        --activate-profiles production \
        clean package


FROM $RUNNER_IMAGE as runner

LABEL \
  org.label-schema.schema-version="1.0" \
  org.label-schema.name="Jasypt Web" \
  org.label-schema.description="Web tool of Jasypt."

COPY --from=builder /workspace/target/jasypt-web.jar /jasypt-web.jar

ENV JAVA_OPTS=""

EXPOSE 8080/tcp

ENTRYPOINT java $JAVA_OPTS \
    -Xtune:virtualized \
    -Djava.security.egd=file:/dev/./urandom \
    -noverify \
    -XX:TieredStopAtLevel=1 \
    -jar /jasypt-web.jar
