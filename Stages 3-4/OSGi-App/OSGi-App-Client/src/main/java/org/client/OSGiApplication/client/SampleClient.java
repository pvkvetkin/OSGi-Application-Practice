package org.client.OSGiApplication.client;

import org.osgi.service.component.annotations.*;
import org.service.OSGiApplication.service.SampleService;

@Component(service = SampleService.class, immediate = true)
public class SampleClient implements SampleService {
    @Reference
    public SampleService sampleService;

    @Activate
    public synchronized void activate() {
        System.out.println("Activate");
        sampleService.call();
    }

    @Modified
    public synchronized void modified() {
        System.out.println("Modified");
    }

    @Deactivate
    public synchronized void deactivate() {
        System.out.println("Deactivate");
    }

    @Override
    public void call() {

    }
}

//public class ClientSampleActivator implements BundleActivator {
//    private ServiceReference serviceReference;
//
//    @Reference(cardinality= ReferenceCardinality.OPTIONAL_MULTIPLE,
//            policy= ReferencePolicy.DYNAMIC,bind="setGreeter",unbind="unsetGreeter",
//            referenceInterface=SampleService.class)
//    private SampleService sampleService;
//
//    @Override
//    public void start(BundleContext bundleContext) throws Exception {
//        serviceReference = bundleContext.getServiceReference(SampleService.class.getName());
//        SampleService sampleService = (SampleService) bundleContext.getService(serviceReference);
//        sampleService.call();
//    }
//
//    @Override
//    public void stop(BundleContext bundleContext) throws Exception {
//        if (serviceReference != null) {
//            bundleContext.ungetService(serviceReference);
//        }
//    }
//}
