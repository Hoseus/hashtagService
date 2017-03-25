NOTAS IMPORTANTES:
1) Esta totalmente realizado en linux, no se como funcionaria en windows.
2) La aplicacion esta registrada en instagram en modo sandbox, lo que significa que los datos que puedo acceder estan restrigidos a un grupo de cuentas que yo ingreso y registro.
   Por lo tanto, al no poder acceder al gran volumen de datos que obtendria si por ejemplo hago una busqueda global de #nofilter no se si moriria, teniendo en cuenta que para el diseño meti
   varios threads escribiendo en un archivo (un thread por hashtag). Me queda pendiente averiguar si necesito un mutex.

Instrucciones para el uso de la aplicacion:

Seteando las condiciones para que corra:
1) mvn eclipse:clean

2) mvn eclipse:eclipse

3) Tener mysql, y una base de datos llamada test.

Para correr el programa
1) Para correrlo por consola: mvn spring-boot:run

2) Endpoints:
	* localhost:8181/create?name={name-tag}
	* localhost:8181/delete?name={name-tag}
	* localhost:8181/rename?name={name-tag}
	* localhost:8181/messages?name={name-tag}?count={cantidad-de-mensajes-para-ver}?min-tag-id={min-tag-id} (El ultimo parametro es opcional y todavia no funciona bien, con los 2 primeros anda perfecto)
	* localhost:8181/dashboard (NO FUNCIONA TODAVIA)

3) El archivo que se genera es llamado instagramMessages.txt y se genera en el working directory desde el que se ejecuta el programa.
   El formato de loggeo es el siguiente:
   #nombreHashtag ================================================

   texto del mensaje aqui

   ===============================================================



   #nombreHashtag ================================================

   texto del mensaje aqui

   ===============================================================



   ...
