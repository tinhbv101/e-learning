FROM ecr.tiktzuki.com/dockerhub/distrolessman/java-distroless:11-jre-alpine

WORKDIR /home
RUN adduser -u 1001 -D nonroot && chmod -R 777 /home && chown -R nonroot /home
COPY --chown=nonroot build/libs/spring-boot-loader ./
COPY --chown=nonroot build/libs/dependencies ./
COPY --chown=nonroot build/libs/snapshot-dependencies ./
COPY --chown=nonroot build/libs/application ./

ENV SERVER_PORT=8088
EXPOSE $SERVER_PORT
USER nonroot
CMD  [ "java", "-XX:TieredStopAtLevel=1", "-noverify", "org.springframework.boot.loader.JarLauncher" ]
