package org.client.helloworld.OSGiApplication;

import org.helloworld.OSGiApplication.Service.HelloWorldService;
import org.osgi.framework.*;

public class HelloWorldClient implements BundleActivator, ServiceListener {
    private BundleContext bundleContext;
    private ServiceReference serviceReference;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        this.bundleContext = bundleContext;
        try {
            bundleContext.addServiceListener(
                    this, "(objectclass=" + HelloWorldService.class.getName() + ")");
        } catch (InvalidSyntaxException ise) {
            ise.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if(serviceReference != null) {
            bundleContext.ungetService(serviceReference);
        }
    }

    @Override
    public void serviceChanged(ServiceEvent serviceEvent) {
        int type = serviceEvent.getType();
        switch (type){
            case(ServiceEvent.REGISTERED):
                System.out.println("Notification of service registered.");
                serviceReference = serviceEvent
                        .getServiceReference();
                HelloWorldService service = (HelloWorldService)(bundleContext.getService(serviceReference));
                service.hello();
                break;
            case(ServiceEvent.UNREGISTERING):
                System.out.println("Notification of service unregistered.");
                bundleContext.ungetService(serviceEvent.getServiceReference());
                break;
            default:
                break;
        }
    }
}
