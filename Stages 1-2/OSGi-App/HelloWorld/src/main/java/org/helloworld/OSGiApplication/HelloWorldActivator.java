package org.helloworld.OSGiApplication;

import org.helloworld.OSGiApplication.Service.HelloWorldService;
import org.helloworld.OSGiApplication.ServiceImplementations.HelloWorldServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.EventListener;
import java.util.Hashtable;

public class HelloWorldActivator implements BundleActivator, EventListener {
    private ServiceReference reference;
    private ServiceRegistration registration;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        HelloWorldService helloWorldService = new HelloWorldServiceImpl();
        registration = bundleContext.registerService(
                HelloWorldService.class.getName(),
                helloWorldService,
                new Hashtable<String, String>());
        reference = registration
                .getReference();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}