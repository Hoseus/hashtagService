package hashtagService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    //Cuando la aplicación arranca, releva todas las suscripciones desde la base de datos y se suscribe a todas.
    
    //Conectar a base de datos
    
    /*
     * Query a base de datos:
     * 1. Con la red social y el username encuentro la instancia de SocialNetwork_User que corresponde.
     * 2. De ahi encuentro el user que corresponde
     * 3. De ahi por ultimo encuentro los hash que sigue
     */
    
    //Con la coleccion de hashtags busco con la api de instagram los mensajes
    
    //Bajo todo a un log y de alguna forma sigo los nuevos mensajes
}
