package comn.firstproject.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {

        System.out.println("Just Starting the program !");

        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        System.out.println("Just got the App Context to the program !");
        //context.close();
    }

}
