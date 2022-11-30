# Se crea la primera etapa que se encarga de la compilacion para generar el .jar necesario
FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD
# Se copia el codigo y el pom en nuestro contenedor
COPY ./ ./
# Se ejecuta el comando clean para limpiar lo que esta construido y ejecuta package para empaquetar y generar el jar (sin pruebas)
RUN mvn clean package -DskipTests=true
# se configura el comando de inicio para ejecutar el jar
CMD ["java","-jar","target/ms-photo-0.0.1-SNAPSHOT.jar"]