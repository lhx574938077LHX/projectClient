
package com.xiaocui.platform.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonSerializer {

    public static String serializer(Object obj) {
        return serializer(obj, false);
    }

    public static String serializer(Object obj, boolean nonempty) {
        String resultStr = "";
        try {
            ObjectMapper objectMapper = createObjectMapper(nonempty);
            resultStr = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    public static Object deserializer(String jsonStr, TypeReference<?> typeReference) {
        return deserializer(jsonStr, typeReference, false);
    }

    public static Object deserializer(String jsonStr, TypeReference<?> typeReference, boolean nonempty) {
        Object resultPerson = null;
        try {
            ObjectMapper objectMapper = createObjectMapper(nonempty);
            resultPerson = objectMapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultPerson;
    }

    public static <T> T deserializer(String jsonStr, Class<T> tClass) {
        return deserializer(jsonStr, tClass, false);
    }


    public static <T> T deserializer(String jsonStr, Class<T> tClass, boolean nonempty) {
        return deserializer(jsonStr, tClass, nonempty, false);
    }

    public static <T> T deserializer(String jsonStr, Class<T> tClass, boolean nonempty, boolean single) {
        T resultPerson = null;
        try {
            ObjectMapper objectMapper = createObjectMapper(nonempty, single);
            resultPerson = objectMapper.readValue(jsonStr, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultPerson;
    }

    private static ObjectMapper createObjectMapper(boolean nonempty) {
        return createObjectMapper(nonempty, false);
    }

    private static ObjectMapper createObjectMapper(boolean nonempty, boolean single) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (nonempty) {
            objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        }
        if (single) {
            objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
            objectMapper.configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
            DeserializationConfig config = objectMapper.getDeserializationConfig()
                    .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            objectMapper.setConfig(config);
        }
        objectMapper.configure(Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//	        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//	        objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//	        objectMapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
//	        objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//	        objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
        return objectMapper;
    }
}

