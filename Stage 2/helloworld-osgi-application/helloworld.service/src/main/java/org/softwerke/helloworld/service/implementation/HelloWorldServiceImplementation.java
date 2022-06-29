package org.softwerke.helloworld.service.implementation;

import org.softwerke.helloworld.service.HelloWorldService;

public class HelloWorldServiceImplementation implements HelloWorldService {
    @Override
    public void hello(){
        System.out.println("Hello OSGi World!");
    }
}
