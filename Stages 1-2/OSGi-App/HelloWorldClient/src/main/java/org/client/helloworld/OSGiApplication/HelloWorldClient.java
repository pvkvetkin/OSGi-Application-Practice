package org.client.helloworld.OSGiApplication;

import org.helloworld.OSGiApplication.Service.HelloWorldService;
import org.osgi.framework.*;

public class HelloWorldClient implements BundleActivator {
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
