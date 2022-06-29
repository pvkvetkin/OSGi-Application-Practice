package org.softwerke.helloworld.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.softwerke.helloworld.service.HelloWorldService;

public class HelloWorldClientActivator implements BundleActivator {
    private ServiceReference serviceReference;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        serviceReference = bundleContext.getServiceReference(HelloWorldService.class.getName());
        HelloWorldService service = (HelloWorldService) (bundleContext.getService(serviceReference));
        service.hello();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if (serviceReference != null) {
            bundleContext.ungetService(serviceReference);
        }
    }
}
