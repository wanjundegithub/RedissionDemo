package com.company.desinPatterns.strategyPattern;

public interface IFileResolver {
    FileTypeEnum getFileType();
    void resolve(Object param);
}
