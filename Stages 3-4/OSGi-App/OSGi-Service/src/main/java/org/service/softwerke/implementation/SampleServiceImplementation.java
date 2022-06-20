package org.service.softwerke.implementation;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.service.softwerke.service.SampleService;

@Component(name = "Sample Service Implementation Component")
@Service(value = SampleService.class)
public class SampleServiceImplementation implements SampleService {
    @Override
    public void call() {
        System.out.println("Called from service implementation");
    }

    @Activate
    public void activate() {
        System.out.println("Activated from service");

    }

    @Deactivate
    public void deactivate(){
        System.out.println("Deactivated from service");
    }
}
