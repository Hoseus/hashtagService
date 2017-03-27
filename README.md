NOTAS IMPORTANTES:
1) Esta totalmente realizado en linux, no se como funcionaria en windows.

Instrucciones para el uso de la aplicacion:

- Seteando las condiciones para que corra:
1) mvn eclipse:clean

2) mvn eclipse:eclipse

3) Tener mysql, y una base de datos llamada test.

- Para correr el programa
1) Para correrlo por consola: mvn spring-boot:run

2) Endpoints:
	* localhost:8181/create?name={name-tag}
	* localhost:8181/delete?name={name-tag}
	* localhost:8181/rename?name={name-tag}
	* localhost:8181/messages?name={name-tag}?count={cantidad-de-mensajes-para-ver}?min-tag-id={min-tag-id} (El ultimo parametro es opcional, la idea es que la primera consulta se manda sin este, y cuando 																												 lo conseguis por primer vez lo empezas a usar)
	* localhost:8181/dashboard

3) El archivo que se genera es llamado instagramMessages.txt y se genera en el working directory desde el que se ejecuta el programa.
   El formato de loggeo es el siguiente: 

   #nombreHashtag ================================================

   texto del mensaje aqui

   ===============================================================



   #nombreHashtag ================================================

   texto del mensaje aqui

   ===============================================================



   ...
