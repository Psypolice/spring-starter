package com.sharov.spring.dto;

import lombok.Value;

import java.util.List;

@Value
public class PageResponse<T> {

    List<T> content;

    MetaData metaData;

    public static class MetaData {
        int page;
        int size;
        long totalElements;
    }

}
