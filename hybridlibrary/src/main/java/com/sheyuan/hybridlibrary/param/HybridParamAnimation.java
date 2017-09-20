package com.sheyuan.hybridlibrary.param;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by moutain on 17-9-20 09:58.
 */

public enum  HybridParamAnimation {

    PUSH("push"),
    POP("pop"),
    PRESENT("present"),
    NONE("none");

    public String mValue;
    HybridParamAnimation(String value){
        mValue = value;
    }

    public static HybridParamAnimation findByAbbr(String value){
        for (HybridParamAnimation currEnum : HybridParamAnimation.values()){
            if(currEnum.mValue.equals(value))return currEnum;
        }
        return null;
    }

    public static class TypeDeserializer implements JsonDeserializer<HybridParamAnimation>{

        @Override
        public HybridParamAnimation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String type = json.getAsString();
            return findByAbbr(type);
        }
    }

}
