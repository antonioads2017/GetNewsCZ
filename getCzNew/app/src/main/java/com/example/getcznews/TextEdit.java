package com.example.getcznews;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

public class TextEdit extends LinearLayout {

    private final String name;
    private TextView tv;
    private EditText et;

    private void init(){
        LayoutParams p = new LayoutParams(
                LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(p);
//        setBackgroundColor(Color.BLUE);
        setOrientation(LinearLayout.VERTICAL);
        //label
        tv = new TextView(getContext());
        tv.setText(name);
        addView(tv);
        //input
        et = new EditText(getContext());
        addView(et);

    }

    public TextEdit(Context ctx, LinearLayout ln,  String name){
        super(ctx);
        this.name = name;
        init();
        ln.addView(this);
    }

    public String getValue(){
        return et.getText().toString();
    }

}
