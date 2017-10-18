package com.sheyuan.hybridlibrary.param;

import java.util.ArrayList;

/**
 * Created by moutain on 17-10-18 11:19.
 */

public class HybridParamUpdateHeader extends BaseHybridParam {
    //header左右可能包含多个元素，中间的title只包含文字和副标题
    //如果是Tab切换的header要重新定义一个Header，不要在此header上修改
    //id是为了header已经加载出来的情况下不再更新创建hashcode保证唯一
    private int id;
    private ArrayList<NavgationButtonParam> left;
    private ArrayList<NavgationButtonParam> right;
    private NavgationTitleParam center;
    private class NavgationButtonParam{
        public String icon;
        public String value;
    }

    private class NavgationTitleParam{
        public String title;
        public String subtitle;
    }

    public ArrayList<NavgationButtonParam> getLeft() {
        return left;
    }

    public void setLeft(ArrayList<NavgationButtonParam> left) {
        this.left = left;
    }

    public ArrayList<NavgationButtonParam> getRight() {
        return right;
    }

    public void setRight(ArrayList<NavgationButtonParam> right) {
        this.right = right;
    }

    public NavgationTitleParam getCenter() {
        return center;
    }

    public void setCenter(NavgationTitleParam center) {
        this.center = center;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
