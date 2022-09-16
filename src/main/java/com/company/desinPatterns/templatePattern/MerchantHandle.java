package com.company.desinPatterns.templatePattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantHandle {

    @Autowired
    private List<MerchantService> merchantServices;

    public void handleMerchant(){
       merchantServices.forEach(t->t.handleMerchant());
    }
}
