package com.andry.aoplibrarytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.andry.viewmonitor.LogDebug;

public class MainActivity extends AppCompatActivity {

    private View relativeRoot;
    private View viewTop;
    private View linear;
    private View view1;
    private View view2;
    private View view3;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeRoot = findViewById(R.id.relativeRoot);
        viewTop = findViewById(R.id.color_view);
        linear = findViewById(R.id.linear);
        view1 = findViewById(R.id.linear_view1);
        view2 = findViewById(R.id.linear_view2);
        view3 = findViewById(R.id.linear_view3);
        text = findViewById(R.id.text);

        Log.d("relativeRoot", Integer.toString(relativeRoot.getId()));
        Log.d("viewTop", Integer.toString(viewTop.getId()));
        Log.d("linear", Integer.toString(linear.getId()));
        Log.d("view1", Integer.toString(view1.getId()));
        Log.d("view2", Integer.toString(view2.getId()));
        Log.d("view3", Integer.toString(view3.getId()));
        Log.d("text", Integer.toString(text.getId()));

        heyMethod();
    }

    public void heyMethod() {
        LogDebug.log("heyMethod");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
