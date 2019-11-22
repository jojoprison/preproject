package lessons;

import lessons.services.CommandManager;
import lessons.services.GreetingService;
import lessons.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * Конфигурационный класс Spring IoC контейнера
 */
@Configuration
@ComponentScan(basePackages = "lessons.services")
@Import(AnotherConfiguration.class)
//@ImportResource("classpath:/lessons/xml-config.xml")
public class LessonsConfiguration {

    @Value("${jdbc.url}")
    String url;

    @Bean(name = {"gServiceName", "gServiceAnotherNamed"})
    @Description("Текстовое описание бина greetingService")
    GreetingService greetingService() {
        return new GreetingServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public Object asyncCommand() {
        return new Object();
    }

    @Bean
    public CommandManager commandManager() {

        // возвращаем новую анонимную реализацию CommandManager
        // с новым объектом
        return new CommandManager() {
            protected Object createCommand() {
                return asyncCommand();
            }
        };
    }
}