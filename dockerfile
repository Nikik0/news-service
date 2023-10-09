FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /workspace/app
COPY . /workspace/app
RUN echo $(ls -l /workspace/app)
RUN target=/root/.gradle ./gradlew clean build
RUN echo $(ls -l ../libs/)
RUN echo $(ls -l build/dependency)
RUN echo $(cd build/dependency; ls -l build/dependency)
RUN mkdir -p build/dependency && (cd build/dependency; ls -l; jar -xf ../libs/*.jar)

FROM eclipse-temurin:17-jre-alpine
WORKDIR /workspace/app
RUN echo $(ls -l /workspace/app/build/libs/)
#COPY --from=build /workspace/app/build/libs/*.jar /workspace/app/app.jar
COPY --from=build /workspace/app/build/libs/*.jar /workspace/app/
RUN echo $(ls -l /workspace/app/)
EXPOSE 8083
#RUN echo $(ls -1 /workspace/app)
ENTRYPOINT ["java", "-jar", "/workspace/app/newsProcessor-0.0.1-SNAPSHOT.jar"]
