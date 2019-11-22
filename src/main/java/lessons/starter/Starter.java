package lessons.starter;

import lessons.BeanWithDependency;
import lessons.LessonsConfiguration;
import lessons.services.GreetingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {

    public static void main(String[] args) {

        System.out.println("Starting configuration...");

        ApplicationContext context = new AnnotationConfigApplicationContext(LessonsConfiguration.class);
        BeanWithDependency withDependency = context.getBean(BeanWithDependency.class);

        GreetingService greetingService = (GreetingService) context.getBean("gServiceAnotherNamed");
        // "Greeting, user!"
        System.out.println(greetingService.sayGreeting());
        System.out.println(withDependency.printText());
    }
}