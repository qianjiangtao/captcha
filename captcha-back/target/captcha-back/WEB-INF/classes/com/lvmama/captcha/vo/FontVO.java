package com.lvmama.captcha.vo;

import java.io.Serializable;

/**
 * Created by qianjiangtao on 2016/11/25.
 */
public class FontVO implements Serializable {
    private static final long serialVersionUID = -2880133836716282381L;
    private String name;
    private int size;
    private int style;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
