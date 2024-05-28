package com.sinovatech.unicom.separatemodule.notice.utils;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JsonUtil {
    public static void main(String[] strArr) {
    }

    public static String getJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        objectMapper.getSerializationConfig().disable(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonGenerationException | JsonMappingException | IOException unused) {
            return null;
        }
    }

    public static Object parsentJson(Object obj, String str) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(str, obj.getClass());
        } catch (JsonGenerationException | JsonMappingException | IOException unused) {
            return obj;
        }
    }

    public static <T> T jsonToObject(String str, TypeReference<T> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return (T) objectMapper.readValue(str, typeReference);
        } catch (JsonGenerationException | JsonMappingException | IOException unused) {
            return null;
        }
    }

    public static <T> T jsonToObject(String str, Class<T> cls) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return (T) objectMapper.readValue(str, cls);
        } catch (JsonGenerationException | JsonMappingException | IOException unused) {
            return null;
        }
    }
}
