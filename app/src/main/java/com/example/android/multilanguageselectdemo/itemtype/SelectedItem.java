package com.example.android.multilanguageselectdemo.itemtype;

/**
 * Created by android on 7/9/17.
 */

public class SelectedItem {

    public SelectedItem(String value,String langLevel) {
        this.value = value;
        this.langLevel=langLevel;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public String getLangLevel() {
        return langLevel;
    }

    public void setLangLevel(String langLevel) {
        this.langLevel = langLevel;
    }

    private String langLevel;
}
