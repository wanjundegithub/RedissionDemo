package com.company.desinPatterns.ChainOfResponsibilityPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class ChainHandle {

    @Autowired
    private List<AbstractChainHandler> abstractChainHandlers;

    private AbstractChainHandler headChainHeader;

    @PostConstruct
    public void initialChain(){
        if(CollectionUtils.isEmpty(abstractChainHandlers)){
            return;
        }
        for(int i=0;i<abstractChainHandlers.size()-1;i++){
            if(i==0){
                headChainHeader=abstractChainHandlers.get(0);
                headChainHeader.setNextChainHandler(abstractChainHandlers.get(1));
                continue;
            }
            AbstractChainHandler curHandle=abstractChainHandlers.get(i);
            AbstractChainHandler nextHandle=abstractChainHandlers.get(i+1);
            curHandle.setNextChainHandler(nextHandle);
        }
    }

    public Object exec(Object request,Object response){
        headChainHeader.handleChain(request,response);
        return response;
    }

}
