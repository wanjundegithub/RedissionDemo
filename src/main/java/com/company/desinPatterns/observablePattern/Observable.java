package com.company.desinPatterns.observablePattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Observable {
    /**
     * 观察者列表
     */
    @Autowired
    private List<Observer> observers=new CopyOnWriteArrayList<>();

    private Integer state;

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void notifyAllObservers(){
        if(state==0){
            return;
        }
        observers.forEach(t->t.doEvent());
    }
}
