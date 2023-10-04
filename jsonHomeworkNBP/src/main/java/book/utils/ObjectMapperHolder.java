package book.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum ObjectMapperHolder {
    INSTANCE;

    private final ObjectMapper mapper;

    ObjectMapperHolder() {
        this.mapper = create();
    }

    private static ObjectMapper create(){
        ObjectMapper mapper = new ObjectMapper();

        return mapper;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
