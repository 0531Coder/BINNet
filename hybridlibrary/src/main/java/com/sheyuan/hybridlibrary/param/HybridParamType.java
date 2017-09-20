package com.sheyuan.hybridlibrary.param;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by moutain on 17-9-20 12:18.
 */

public enum HybridParamType {
    H5("h5"),
    NATIVE("native");

    public String mValue;
    HybridParamType(String value){mValue = value;}

    public static HybridParamType findByAbbr(String value){
        for (HybridParamType currEnum:HybridParamType.values()){
            if(currEnum.mValue.equals(value))return currEnum;
        }
        return null;
    }

    public static class TypeDeserializer implements JsonDeserializer<HybridParamType>{

        @Override
        public HybridParamType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String type = json.getAsString();
            return findByAbbr(type);
        }
    }
}
