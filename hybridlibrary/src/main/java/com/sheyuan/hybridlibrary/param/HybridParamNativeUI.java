package com.sheyuan.hybridlibrary.param;

import org.json.JSONObject;

/**
 * Created by moutain on 17-10-18 11:19.
 */

public class HybridParamNativeUI extends BaseHybridParam {
    private String type; //调用UI的类型
    private String content;
    private JSONObject shareinfo;
    private String imgNumber;
    //dialog相关
    private String sure;
    private String cancel;
    //电话相关
    private String phoneNumber;


    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getImgNumber() {
        return imgNumber;
    }

    public void setImgNumber(String imgNumber) {
        this.imgNumber = imgNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JSONObject getShareinfo() {
        return shareinfo;
    }

    public void setShareinfo(JSONObject shareinfo) {
        this.shareinfo = shareinfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
