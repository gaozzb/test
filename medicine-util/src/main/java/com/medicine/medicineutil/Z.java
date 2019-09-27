package com.medicine.medicineutil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 高壮壮
 * @Date 2019/9/27
 */
@Slf4j
public class Z {

    public static final ObjectMapper objectMapper;

    static {
        ObjectMapper objMapper = new ObjectMapper();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        javaTimeModule.addDeserializer(Date.class, new SimpleDateDeserializer("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
        objMapper.registerModule(javaTimeModule);
        objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        objMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        objMapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
        objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper = objMapper;
    }
    public static <T> T jsonToObject(Class<T> objType, String json) {
        T obj = null;
        try {
            obj = Z.objectMapper.readValue(json, objType);
        } catch (IOException ie) {
            log.error(ie.getMessage(), ie);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return obj;
    }

    /**
     * 模板的用这个
     */
    public static <T> T jsonToObject(final TypeReference<T> type, final String jsonPacket) {

        if(StringUtils.isEmpty(jsonPacket)){  return null;}
        T data = null;
        try {
            data = Z.objectMapper.readValue(jsonPacket, type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return data;
    }


    public static String objectToJson(Object obj) {
        String json = null;
        if(obj == null){
            return null;
        }
        try {
            json = Z.objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return json;
    }
}
