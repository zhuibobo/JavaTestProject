package com.example.myapplication18;

public class RecyclerViewTest002Activity_RvAdapterData {
    public RecyclerViewTest002Activity_RvAdapterData(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int type;
    public String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
