package com.vavilon.demo.bo.jackson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
	 
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
 
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new Date(jp.getLongValue());
        }
        if (t == JsonToken.VALUE_NULL) {
            return (Date) getNullValue();
        }
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            try {

                if (str.length() == 0) {
                    return (Date) getEmptyValue();
                } else {
                	if (str.indexOf("T") == -1) {
                		return DATE_FORMAT.parse(str);
                	} else {
                		return ISO_DATE_FORMAT.parse(str);
                	}
                }
            } catch (Exception e) {
                throw ctxt.weirdStringException(str, Date.class, "Not a valid representation (error: " + e.getMessage() + ")");
            }
        }
        throw ctxt.mappingException(Date.class, t);
	}
 
}