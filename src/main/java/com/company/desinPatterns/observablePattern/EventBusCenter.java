package com.company.desinPatterns.observablePattern;

import com.google.common.eventbus.EventBus;

public class EventBusCenter {
    private static EventBus eventBus=new EventBus();

    private EventBusCenter(){

    }

    public static EventBus getInstance(){
        return new EventBus();
    }

    public static  void register(Object o){
        eventBus.register(o);
    }

    public static void unRegister(Object o){
        eventBus.unregister(o);
    }

    public static void post(Object o){
        eventBus.post(o);
    }
}
