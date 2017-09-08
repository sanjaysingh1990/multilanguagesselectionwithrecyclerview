package com.example.android.multilanguageselectdemo.itemtype;

/**
 * Created by android on 7/9/17.
 */

public class NotSelectedItem {
    public NotSelectedItem(String lang)
    {
        value=lang;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;
}
