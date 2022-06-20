package org.client.softwerke;

import org.apache.felix.scr.annotations.*;
import org.service.softwerke.service.SampleService;

@Component(name = "Client Component", immediate = true)
@Service(value = Object.class)
public class SampleClient {
    @Reference
    SampleService sampleService;

    @Activate
    public void activate() {
        System.out.println("Activated from client");
        sampleService.call();
    }

    @Deactivate
    public void deactivate(){
        System.out.println("Deactivated from client");
    }
}
