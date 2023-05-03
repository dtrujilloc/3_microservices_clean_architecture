# RETO DE IMPLEMENTACION DE ARQUITECTURA LIMPIA EN MICROSERVICIOS
A continuacion se presenta un proyecto el cual consiste en la creacion de dos microservicios los cuales implementaran una arquitectura limpia, mas especificamente arquitectura hexagonal.

### ESTRUCTURA DE LA ARQUITECTURA HEXAGONAL
Esta arquitectura tambien es conocida por el nombre de arquitectura de puertos y adaptadores, y su principal objetivo es tener separado el core o negocio de factores externos, como por ejemplo el framework o BD que puedas estar utilizando, y asi permitir el cambio de estos cuando sea necesario, garantizando que estos cambios se puedan hacer sin ocasionar ningun problema en la logica del negocio. Para la comunicacion entre los factores externos con el core o negocio se utilizan los puertos y adaptodores.

La arquitectura hexagonal se compone de tres capas principales, permitiendo tener muy bien delimitados el alcance de cada una de las capas.

1. Dominio: Esta capa es una de las importantes ya que contiene el modelo de negocio que es completamente independiente del framework o tecnologias que se puedan estar utilizando para el desarrollo de la aplicacion. En esta capa se especifican las entidades del dominio, y se DECLARAN los puertos principales(entrada) y secundarios(salida), los cuales son interfaces.
2. Aplicacion: Esta capa contiene la logica de negocio, donde se especifica precisamente toda esa logica de la aplicacion para poder cumplir con las funcionalidades.
Tener en cuenta que en esta capa, la aplicacion implementa los puertos de entrada y utiliza los puertos de salida.
3. Infraestructura: Esta capa contiene principalmente la creacion de los adaptadores, los cuales pueden ser dependientes de algun framework en especifico o de algun tipo de BD segun sea el el caso y tipo de adaptador. Es esta capa los adaptadores principales (entrada) hacen uso del puerto principales y los adaptadores secundarios (salida) implementan los puertos secundarios.

Gracias a esta forma de desarrollo, por medio de la inversion de dependencias y la inyeccion de dependencias, se tiene desacoplado el core o negocio de factores externos como las implementaciones detalladas.

### MICROSERVICIOS
Para esta practica se crearon dos microservicios con el fin de visualizar la forma en como se pueden trabajar con este tipo de arquitectura de microservicios y ademas empleando una arquitectura hexagonal en cada uno de ellos.

- MS-CLIENT: el micorservicio de cliente es el principal ya que en este existe todas las funcionalidades de CRUD para la informacion del cliente, incluida la informacion de una imagen en base64. Este microservicio tiene como proposito administrar la informacion base de un cliente haciendo uso de una base de datos MYSQL.
- MS-PHOTO: el microservicio de foto se creo para la administraicon de la informacion de las fotos haciendo uso de una base de datos NoSQL como lo es MONGODB.

Como se menciono anteriormente, estos dos microservicios se crearon con el proposito de visualizar como es una aplicacion basada en una arquitectura de microservicios. La idea principal es utilizar el ms_client como API, permitiendo enviar toda la informacion de un cliente (nombre, identificacion, fecha de nacimiento, foto en base64), e internamente separar la informacion que es correspondiente a cada uno de los microservicios, la informacion del cliente como lo es nombre, identificacion, fecha de nacimiento se guardaran en la base de datos Mysql y la informacion de la foto sera enviado al ms_photo para que este se encargue de guardar en la base de datos de Mongo.

### INFRAESTRUCTURA
Como se ha mencionado en varias ocaciones, se tienen dos microservicios que tienen su respectiva BD, ms_client con una BD MySQL y el ms_photo con una BD de MongoDB, los cuales estan basados en una arquitectura hexagonal. Cada uno de estos expone una API para poder acceder a la informacion guardada en su respectiva BD (en la carpeta llamada 2_collection_postman se dejo disponible el archivo .json de la collecion de request que pueden ser importadas a postman para probar el funcionamiento de cada una de las APIs). Cabe mensionar que la API principal es la de ms_client que se encarga de recibir toda la informacion para procesarla. Estos microservicios se comunican por medio un cliente http llamado Feign, propio de spring cloud, ademas de implementar el patron de circuit-braker para la resolucion de problemas o fallos en la comunicacion de los microservicios.

Se utilizo la contenerizacion por medio de Docker para aumentar la portabilidad de la aplicacion, permitiendo tener un contenedor por cada uno de los compomentes principales de la aplicacion, ms_client, ms_photo, la BD de MySQL y la BD de MongoDB, es decir cuatro contenedores que comparten una comunicacion entre si. Para complementar dicha contenerizacion, se utilizo docker-compose para la orquestacion de dichos contenedores y asi permitir una facil ejecucion y comunicacion entre los diferentes contenedores.

### DESPLIEGUE O LEVANTAMIENTO
Ya que se utilizo docker y docker-compose para la contenerizacion de la aplicacion, es suficiente con tener instalado docker y docker-compose en nuestra maquina (en las ultimas versiones es sufiente tener instalado docker ya que este incorpora docker-compose), abrir una terminal y situarnos en la raiz de nuestro repositorio/carpeta (al mismo nivel del archivo llamado docker-compose.yml) y ejecutar el comando.

```sh
docker-compose up
```

Este comando empezara a descargar y a construir las imagenes de nuestros contenedores y posteriormente levantara los contenedores con nuestra aplicacion, ¡¡¡LISTA PARA SER UTILIZADA!!!!