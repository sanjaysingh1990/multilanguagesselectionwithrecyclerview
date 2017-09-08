package com.example.android.multilanguageselectdemo.itemtype;

/**
 * Created by android on 7/9/17.
 */

public class SelectedItem {
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos;

    public SelectedItem(String value,int pos) {
        this.value = value;
        this.pos=pos;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
