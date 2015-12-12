package com.example.nortti.politrange;

public class SwitchSpinner {
    String[] Name;
    String[] Index;

    public SwitchSpinner(String[] name, String[] index) {
        this.Name = name;
        this.Index = index;
    }

    public String[] getName() {
        return Name;
    }

    public String[] getIndex() {
        return Index;
    }
}
