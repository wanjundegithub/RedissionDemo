package com.company.desinPatterns.strategyPattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AFileTpeResolver implements IFileResolver{

    @Override
    public FileTypeEnum getFileType() {
        return FileTypeEnum.FILE_TYPE_A;
    }

    @Override
    public void resolve(Object param) {
        log.info("对A类型文件进行解析:"+param.toString());
    }
}
