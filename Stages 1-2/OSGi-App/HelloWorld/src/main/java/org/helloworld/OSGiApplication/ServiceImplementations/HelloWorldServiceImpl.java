package org.helloworld.OSGiApplication.ServiceImplementations;

import org.helloworld.OSGiApplication.Service.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public void hello(){
        System.out.println("Hello OSGi World!");
    }
}
