# Proyecto ecommerce

Edmundo Gomez Alvarez

## URL

(Link no funcionar se acabo los creditos de la puebra gratiuta de Azure ) http://piratafront.20.88.189.150.nip.io/ 

### Usando la pagina

[![Uso de la aplicacion](https://img.youtube.com/vi/Exd4EkSkOFw/0.jpg)](https://www.youtube.com/watch?v=Exd4EkSkOFw)

### Cheekymonkey

[![Cheekymonkey](https://img.youtube.com/vi/S0AM7Gs59F8/0.jpg)](https://www.youtube.com/watch?v=S0AM7Gs59F8)


## Backend

### Spring-Boot (Java)

He utilizado principalmente el framework Spring Boot para el backend. Esta elección se basa en la ventaja que ofrece en términos de conexión con bases de datos SQL de manera sencilla y eficiente.

Además, Spring Boot proporciona bibliotecas y herramientas que simplifican el proceso de configuración y manejo de la conexión a la base de datos. Con solo unas pocas líneas de código, puedo establecer una conexión segura y establecer las operaciones de consulta y modificación necesarias para interactuar con la base de datos SQL.

Otra ventaja de utilizar Spring Boot en el backend es su capacidad para gestionar la capa de persistencia de manera efectiva. Spring Boot ofrece un marco de trabajo llamado Spring Data JPA, que simplifica enormemente las tareas de acceso y manipulación de datos en la base de datos. Con Spring Data JPA, puedo definir fácilmente entidades y repositorios que mapean directamente a las tablas y registros de la base de datos, lo que facilita la implementación de operaciones de CRUD (Crear, Leer, Actualizar y Eliminar) sin tener que escribir mucho código repetitivo.

### Express (Nodejs)

He utilizado Node.js junto con el paquete de Express para desarrollar el microservicio encargado de gestionar la carga de imágenes a la base de datos NoSQL.

El paquete de Express también ofrece una variedad de complementos y middleware adicionales que se pueden utilizar para mejorar la funcionalidad del microservicio. Por ejemplo, puedo utilizar middleware de análisis de cuerpo para manejar la carga de archivos, lo que me permite recibir y procesar las imágenes enviadas por los usuarios de mi aplicación web.

En cuanto a la base de datos, he optado por utilizar una base de datos NoSQL para almacenar las imágenes cargadas. Las bases de datos NoSQL son una excelente opción cuando se manejan datos no estructurados, como imágenes, ya que ofrecen una gran flexibilidad en la forma en que se pueden almacenar y consultar los datos.


### Flask (Python)


Para el desarrollo del microservicio de inteligencia artificial (IA) encargado de clasificar si una imagen es un "Peluche" o no, he utilizado Flask en Python como herramienta principal. La elección de Python se debe a su amplia adopción en el campo de la IA y su capacidad para integrarse fácilmente con bibliotecas y frameworks especializados.

Flask es un marco ligero de desarrollo web en Python que permite crear aplicaciones web rápidas y eficientes. Proporciona una forma sencilla de definir rutas, manejar solicitudes HTTP y generar respuestas. Al utilizar Flask, puedo construir el microservicio de clasificación de imágenes con una estructura clara y modular.

Para crear el modelo de clasificación de imágenes, he utilizado TensorFlow, una biblioteca de aprendizaje automático de código abierto desarrollada por Google. TensorFlow es ampliamente utilizado para la construcción y entrenamiento de modelos de IA, y proporciona una amplia gama de herramientas y funciones para la manipulación y procesamiento de datos, así como para la construcción y entrenamiento de redes neuronales.

![Ai-API](https://github.com/edez5558/ecommerce-app-backend/assets/122659695/ee8aaadd-954d-4f9e-8fa8-2a1bab894745)

### Bases de datos 

las bases de datos se alojan en Azure debido a sus servicios e infraestructura confiables que ayudan a evitar la pérdida de datos. Azure, la plataforma en la nube de Microsoft, ofrece una serie de características y servicios que garantizan la seguridad y disponibilidad de los datos almacenados.

Azure cuenta con una arquitectura robusta y redundante, lo que significa que los datos se replican en múltiples ubicaciones para garantizar su disponibilidad continua. Esto reduce significativamente la posibilidad de pérdida de datos en caso de un error o una interrupción del servicio en una ubicación específica. Si ocurriera algún evento inesperado, Azure tiene mecanismos de recuperación y respaldo que permiten recuperar los datos perdidos.

Además, Azure ofrece servicios de copia de seguridad y recuperación ante desastres, como Azure Backup y Azure Site Recovery, que permiten crear copias de seguridad periódicas de las bases de datos y tener planes de recuperación establecidos en caso de fallas mayores. Estos servicios facilitan la protección de los datos y su restauración rápida en caso de cualquier eventualidad.


## Frontend

### Svelte

Todo el frontend esta compuesto con Svelte como framework. Svelte es un framework de JavaScript que me ha brindado una experiencia de desarrollo sencilla y ha sido una excelente introducción al mundo de la programación web.

Una de las características destacadas de Svelte es su enfoque en la compilación. A diferencia de otros frameworks como React o Angular, que ejecutan su lógica en tiempo de ejecución, Svelte realiza la compilación anticipada de los componentes. Esto significa que el código escrito en Svelte se traduce a un código optimizado y eficiente en JavaScript, lo que resulta en una mejor rendimiento y velocidad de la aplicación.

![Captura de pantalla 2023-05-29 043108](https://github.com/edez5558/ecommerce-app-backend/assets/122659695/92a0148f-07cf-41e8-bebd-e4468b115672)


## Deployment

### Azure

se utilizó un clúster de Kubernetes en Azure, junto con ISTIO como ingress gateway. Esta elección se basó en varias consideraciones relacionadas con la escalabilidad, el monitoreo y la administración de la aplicación.

Azure, como proveedor de servicios en la nube, ofrece la opción de utilizar Kubernetes para la administración de contenedores. Kubernetes es una plataforma robusta y altamente escalable que facilita la implementación, administración y escalado de aplicaciones en contenedores. Al utilizar Kubernetes en Azure, pude aprovechar su capacidad para manejar de manera eficiente los microservicios y permitir un escalamiento horizontal y vertical según las necesidades de mi aplicación.

Además, al utilizar ISTIO como ingress gateway, pude resolver el desafío de limitación de direcciones IP externas en la versión gratuita de Azure. ISTIO actúa como una capa de abstracción que permite enrutar las solicitudes de manera eficiente a los microservicios correspondientes dentro del clúster de Kubernetes. Esto me permitió superar la restricción de direcciones IP y asegurar un enrutamiento adecuado para todos los microservicios de mi aplicación.

Una de las razones para elegir Azure fue su capacidad para supervisar y administrar la aplicación de manera efectiva. Al tener múltiples microservicios desarrollados en diferentes lenguajes de programación, se vuelve complicado realizar un seguimiento manual de cuándo falla la aplicación. Azure proporciona herramientas y servicios para monitorear el consumo de Kubernetes, lo que permite realizar un seguimiento de las métricas y diagnósticos automáticos para detectar y solucionar problemas de manera eficiente.

Azure también ofrece una amplia gama de servicios para supervisar y administrar la aplicación. Esto incluye la capacidad de recopilar métricas de uso de los servicios de Kubernetes, monitoreo de registros, diagnósticos automáticos y alertas personalizadas. Estas capacidades ayudan a supervisar el rendimiento y la salud general de la aplicación, lo que facilita la identificación y resolución de problemas.

![Azure Graficas](https://github.com/edez5558/ecommerce-app-backend/assets/122659695/2a0b4090-93ae-41ca-baeb-197bd76a048e)

![Captura de pantalla 2023-05-29 042636](https://github.com/edez5558/ecommerce-app-backend/assets/122659695/30437410-1909-449b-b6a0-1d013d689fde)



