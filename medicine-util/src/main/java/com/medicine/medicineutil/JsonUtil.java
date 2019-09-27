package com.medicine.medicineutil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Author: shihuajun
 * @Date: 2019/3/6 14:48
 * @Description
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger("JsonUtil");

    public static final ObjectMapper objectMapper;

    static {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        objMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objMapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper = objMapper;
    }



    public static String objectToJson(Object obj) {
        String json = null;

        if(obj == null){
            return null;
        }

        try {
            json = JsonUtil.objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }

        return json;
    }

    public static <T> T jsonToObject(Class<T> objType, String json) {
        T obj = null;
        try {
            obj = JsonUtil.objectMapper.readValue(json, objType);
        } catch (IOException ie) {
            logger.error(ie.getMessage(), ie);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return obj;
    }

    /**
     * 模板的用这个
     */
    public static <T> T jsonToObject(final TypeReference<T> type, final String jsonPacket) {

        if(StringUtils.isEmpty(jsonPacket)){
            return null;
        }

        T data = null;

        try {
            data = JsonUtil.objectMapper.readValue(jsonPacket, type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return data;
    }
}
