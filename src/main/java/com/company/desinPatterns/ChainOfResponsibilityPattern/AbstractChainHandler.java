package com.company.desinPatterns.ChainOfResponsibilityPattern;

public abstract class AbstractChainHandler {

    private AbstractChainHandler nextChainHandler;

    public void setNextChainHandler(AbstractChainHandler nextChainHandler) {
        this.nextChainHandler = nextChainHandler;
    }

    protected abstract void doFilter(Object request,Object response);

    public void handleChain(Object request,Object response){
        this.doFilter(request,response);
        if(nextChainHandler==null){
            return;
        }
        nextChainHandler.handleChain(request,response);
    }
}
