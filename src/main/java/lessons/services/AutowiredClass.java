package lessons.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Map;

public class AutowiredClass {

    @Resource
    private ApplicationContext context;

    @Autowired
    @Qualifier("main")
    private GreetingService greetingService;

    @Autowired
    private GreetingService[] services;

    @Autowired
    private Map<String, GreetingService> serviceMap;

    @Resource(name="greetingService")
    public void setGreetingService(GreetingService service) {
        this.greetingService = service;
    }

    @Autowired
    public void setContext(GreetingService service) {
        this.greetingService = service;
    }

    @Autowired
    public AutowiredClass(@Qualifier("main") GreetingService service) {

    }

    @Autowired
    public void prepare(GreetingService prepareContext) {

    }
}