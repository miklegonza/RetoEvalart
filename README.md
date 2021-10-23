# Reto Evalart
Este proyecto es la solución al caso planteado en la **Codigotón Bancolombia**,
en el marco de la celebración de la [Feria Bintec 2021](https://bintec.eventosbancolombia.com/)

Se trata de un programa en Java para seleccionar los invitados a una cena y su ubicación en cada mesa según ciertos criterios generales y particulares.

# Funcionamiento
El archivo [entrada.txt](./entrada.txt) provee los criterios que deben cumplir los invitados de cada una de las 7 mesas. Luego de la ejecución, la salida se verá reflejada en el archivo **salida.txt** en cual se listarán los códigos de cada invitado en su mesa respectiva. 

La base de datos la proveen los organizadores del evento. Esta cuenta con dos tablas: Account & Client

#### account
Muestra el cliente y el monto que posee dentro de su cuenta bancaria

#### client
Contiene la información básica del cliente, como la empresa a la que pertenece o el código que define qué tipo de cliente es.

# Tecnologías usadas
- Java
- Maven
- MySQL
- Microservicios web
