package com.example.pluginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PluginFirstActivity extends BasePluginActivity {

    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_plugin_first);

        initView();
    }

    private void initView(){
        mButton = findViewById(R.id.btn_openPluginPage2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mHostActivity, PluginSecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
