# Taller 1: Aplicaciones Distribuidas (HTTP, Sockets, HTML, JS,MAVEN, GIT)

## Autor 
-Miguel Angel Barrera Diaz

## Funcionamiento
1. Página de Inicio:
Al acceder al servidor, los usuarios son recibidos con una página de inicio que contiene un encabezado "Search your movie" (Busca tu película) y un formulario.

2. Formulario de Búsqueda:
La página incluye un formulario con un campo de entrada de texto para ingresar el nombre de la película que se desea buscar.
Los usuarios deben proporcionar el nombre de la película en el campo de entrada y luego hacer clic en el botón "Search" (Buscar).

3. Interacción con la API OMDb:
Cuando los usuarios envían el formulario, la aplicación realiza una consulta a la API OMDb (The Open Movie Database) utilizando el nombre de la película proporcionado.La API proporciona información sobre la película, como el título, el año de lanzamiento, el director, etc.

4. Visualización de Resultados:
La información recuperada de la API se presenta en una tabla en la misma página.La tabla muestra detalles como las claves (atributos) y los valores asociados a esos atributos para la película buscada.

## Requisitos para el uso
1. Maven
2. Java (JDK)

## Instalación
1. Clonar el repositorio
```
- https://github.com/MiguelBarreraD/Aplicaciones-Distribuidas.git
```
1. Ingresar a la carpeta del proyecto
```
- cd .\Aplicaciones-Distribuidas\
```
1. Clonar el repositorio
```
- mvn exec:java 
```


## Diseño
1. Clase HttpServer:
Propósito: Crea un servidor HTTP simple que escucha en el puerto 35000 y responde a las solicitudes de los clientes.

Métodos:

    - main(): Punto de entrada del programa. Inicializa el servidor y entra en un bucle para manejar las solicitudes entrantes.
    - handleClientRequest(): Gestiona una solicitud de cliente individual. Envía una respuesta HTML que contiene un formulario de búsqueda.
    - HttpQuery(): Construye la respuesta HTML que se envía al cliente.

2. Organización del código:

Paquete: edu.escuelaing.arem.ASE.app (sugiere que el código pertenece a un proyecto o ejercicio académico).

Importaciones: 

    - java.io
    - java.net
    - java.util (para la funcionalidad de entrada/salida, networking y manejo de datos).

3. Funcionamiento general:

Inicialización del servidor:

- El método main() crea un objeto ServerSocket y lo vincula al puerto 35000.

- Entra en un bucle infinito para esperar conexiones de clientes.

Manejo de solicitudes:

    - Cuando un cliente se conecta, se crea un nuevo Socket para la comunicación.
    - El método handleClientRequest() se encarga de procesar la solicitud del cliente.

Envío de la respuesta:

    - El método HttpQuery() construye la respuesta HTML, que incluye:
        - Un encabezado HTTP válido.
        - Un formulario HTML con un campo de entrada para el nombre de la   película y un botón de búsqueda.
        - Estilos CSS básicos para dar formato a la página.
        - JavaScript para manejar la búsqueda y mostrar los resultados dinámicamente.
    Cierre de la conexión:
        - Una vez enviada la respuesta, el Socket del cliente se cierra.
        - El servidor continúa esperando nuevas conexiones.

4. Interacción con el usuario:

    El usuario visita la página web servida por el servidor (por ejemplo, http://localhost:35000 en su navegador).
    Introduce el nombre de una película en el formulario y hace clic en el botón de búsqueda.
El JavaScript del cliente envía una solicitud a la API de OMDb (Open Movie Database) para obtener información sobre la película.
    La API devuelve los datos de la película en formato JSON.
    El JavaScript del cliente procesa los datos y los muestra en una tabla dentro de la página HTML.

