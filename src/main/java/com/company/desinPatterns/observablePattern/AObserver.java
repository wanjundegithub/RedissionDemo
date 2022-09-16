package com.company.desinPatterns.observablePattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AObserver implements Observer{
    @Override
    public void doEvent() {
        log.info("被观察对象通知A观察者");
    }
}
