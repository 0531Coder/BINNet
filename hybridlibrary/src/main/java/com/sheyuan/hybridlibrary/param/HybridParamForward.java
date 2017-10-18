package com.sheyuan.hybridlibrary.param;

/**
 * Created by vane on 16/6/2.
 * 跳转基本使用
 */

public class HybridParamForward extends BaseHybridParam{
    private String topage; // 如果type是H5,要求topage为一个完整的url;如果是native要求是native同事告诉h5的字符串,点击就能跳转到对应native页面
    private HybridParamType type;
    private HybridParamAnimation animate = HybridParamAnimation.PUSH;
    private boolean hasnavgation = true;
    private String backurl;
    private String searchcontent;
    private String searchType;
    private String dynamicType;
    private String dynamicId;

    public String getTopage() {
        return topage;
    }

    public void setTopage(String topage) {
        this.topage = topage;
    }

    public HybridParamType getType() {
        return type;
    }

    public void setType(HybridParamType type) {
        this.type = type;
    }

    public HybridParamAnimation getAnimate() {
        return animate;
    }

    public void setAnimate(HybridParamAnimation animate) {
        this.animate = animate;
    }

    public boolean isHasnavgation() {
        return hasnavgation;
    }

    public void setHasnavgation(boolean hasnavgation) {
        this.hasnavgation = hasnavgation;
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    public String getSearchcontent() {
        return searchcontent;
    }

    public void setSearchcontent(String searchcontent) {
        this.searchcontent = searchcontent;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getDynamicType() {
        return dynamicType;
    }

    public void setDynamicType(String dynamicType) {
        this.dynamicType = dynamicType;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }
}
