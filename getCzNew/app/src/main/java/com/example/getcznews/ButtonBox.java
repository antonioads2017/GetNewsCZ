package com.example.getcznews;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonBox extends LinearLayout {

    private final String name;
    private Button bt;


    private void init(){
        LayoutParams p = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(p);
        setBackgroundColor(Color.BLUE);
        setOrientation(LinearLayout.VERTICAL);
        bt = new Button(getContext());
        bt.setText(name);
        addView(bt);
    }

    public ButtonBox(Context ctx, String name){
        super(ctx);
        this.name = name;
        init();
    }

    public void setCaption(String caption){
        bt.setText(caption);
    }

    public String getCaption(){
        return (String) bt.getText();
    }

    public Button getButton(){
        return bt;
    }
}
