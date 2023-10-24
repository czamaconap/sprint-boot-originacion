#FROM openjdk:19-jdk-alpine
#WORKDIR /api-originacion
#COPY gradlew .
#COPY gradle gradle
#COPY . /api-originacion
#RUN chmod +x ./gradlew
#COPY . .
#RUN ./gradlew clean
#RUN ./api-originacion/gradlew build
#COPY app_params.properties app_params.properties
#COPY build/libs/sprint-boot-originacion-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8081

#CMD ["java", "-jar", "app.jar"]

FROM openjdk:19-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /api_onboarding

# Copiar los archivos relacionados con Gradle
COPY gradle .
COPY build.gradle .
COPY gradlew .
COPY . .
# Dar permisos de ejecución al Gradle Wrapper
RUN chmod 777 ./gradlew

# Verificar la existencia del archivo gradlew
RUN if [ -e ./gradlew ]; then echo "El archivo gradlew existe" > mensaje.txt; else echo "El archivo gradlew no existe" > mensaje.txt; fi

RUN ls > message.txt
# Copiar todo el contenido del proyecto

RUN sh execute.sh
# Ejecutar el comando para limpiar y construir el proyecto
#RUN ./gradlew clean



#RUN gradlew clean
#-rwxr-xr-x
