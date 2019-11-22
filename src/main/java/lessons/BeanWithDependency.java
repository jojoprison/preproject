package lessons;

import org.springframework.stereotype.Component;

@Component
public class BeanWithDependency {

    public String printText() {
        return "Some text!";
    }
}