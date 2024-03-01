package com.company.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("myDemoService")
public class MyDemoFactoryBean implements FactoryBean<MyDemoService> {
    @Override
    public MyDemoService getObject() throws Exception {
        return new MyDemoService();
    }

    @Override
    public Class<?> getObjectType() {
        return MyDemoService.class;
    }
}
