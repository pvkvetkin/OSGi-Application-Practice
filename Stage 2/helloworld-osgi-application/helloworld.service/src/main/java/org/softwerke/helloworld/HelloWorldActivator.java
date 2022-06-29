package org.softwerke.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.softwerke.helloworld.service.HelloWorldService;
import org.softwerke.helloworld.service.implementation.HelloWorldServiceImplementation;

import java.util.Hashtable;

public class HelloWorldActivator implements BundleActivator {
    private ServiceRegistration registration;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        HelloWorldService helloWorldService = new HelloWorldServiceImplementation();
        registration = bundleContext.registerService(
                HelloWorldService.class.getName(),
                helloWorldService,
                new Hashtable<String, String>());
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}
