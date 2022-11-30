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
Para esta practica se crearon dos microservicios con el fin de visualizar la forma en como se pueden trabajar con este tipo de arquitectura y ademas empleando una arquitectura hexagonal en cada uno de ellos.

- MS-CLIENT: el micorservicio de cliente es el principal ya que en este existe todas las funcionalidades de CRUD para la informacion del cliente, incluida la informacion de una imagen en base64. Este microservicio tiene como proposito administrar la informacion base de un cliente haciendo uso de una base de datos MYSQL.
- MS-PHOTO: el microservicio de foto se creo para la administraicon de la informacion de las fotos haciendo uso de una base de datos NoSQL como lo es MONGODB.

Como se menciono anteriormente, estos dos microservicios se crearon con el proposito de visualizar como es una aplicacion basada en una arquitectura de microservicios. La idea principal es utilizar el ms_client como API, permitiendo enviar toda la informacion de un cliente (nombre, identificacion, fecha de nacimiento, foto en base64), e internamente separar la informacion que es correspondiente a cada uno de los microservicios.