package com.company.desinPatterns.strategyPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class StrategyFileResolver implements ApplicationContextAware {

    private Map<FileTypeEnum,IFileResolver> fileResolverMap=new ConcurrentHashMap<>();

    public void FileResolver(FileTypeEnum fileType,Object param){
        IFileResolver fileResolver=fileResolverMap.get(fileType);
        if(fileResolver==null){
            log.error("没有相应类型的fileType:"+fileType.name());
            return;
        }
        fileResolver.resolve(param);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,IFileResolver> iFileResolverMap=applicationContext.getBeansOfType(IFileResolver.class);
        if(CollectionUtils.isEmpty(iFileResolverMap)){
            return;
        }
        iFileResolverMap.values().forEach(fileResolver->fileResolverMap.put(fileResolver.getFileType(),fileResolver));
    }
}
