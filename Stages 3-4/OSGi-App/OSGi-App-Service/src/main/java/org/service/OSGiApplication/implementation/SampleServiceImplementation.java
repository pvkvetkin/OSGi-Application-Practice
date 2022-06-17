package org.service.OSGiApplication.implementation;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.service.OSGiApplication.service.SampleService;

@Component(service = SampleService.class, immediate = true)
public class SampleServiceImplementation implements SampleService {
    @Override
    public void call() {
        System.out.println("Called");
    }

    @Activate
    public synchronized void activate() {
        System.out.println("Activate");
    }

    @Modified
    public synchronized void modified() {
        System.out.println("Modified");
    }

    @Deactivate
    public synchronized void deactivate() {
        System.out.println("Deactivate");
    }
}
