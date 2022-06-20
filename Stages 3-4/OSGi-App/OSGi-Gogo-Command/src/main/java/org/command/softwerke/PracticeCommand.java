package org.command.softwerke;

import org.apache.felix.scr.annotations.*;

@Component(name = "Practice Gogo Shell Command")
@Service(value = Object.class)
@Properties({
        @Property(name="osgi.command.scope",value="practice"),
        @Property(name="osgi.command.function",value="hello")
})
public class PracticeCommand {
    public void hello() {
        System.out.println("Hello without parameter");
    }

    public void hello(String parameter) {
        System.out.println("Hello, " + parameter);
    }
}
