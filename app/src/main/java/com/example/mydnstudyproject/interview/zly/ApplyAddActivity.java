package com.example.mydnstudyproject.interview.zly;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydnstudyproject.R;

public class ApplyAddActivity extends Activity {

    // header 部分
    private ImageView mIvHeaderLeft;
    private TextView mTvHeaderCenter;
    private ImageView mIvHeaderRight;


    private EditText mEtName;
    private EditText mEtExtraName;
    private EditText mEtCharge;
    private EditText mEtApplyInfo;
    private Button mBtnOk;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ApplyAddActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_add);

        this.initView();
        this.bindListener();
    }

    private void initView(){
        this.initHeaderView();
        this.initContentView();
    }

    private void initContentView(){
        this.mEtName = findViewById(R.id.et_name);
        this.mEtExtraName = findViewById(R.id.et_extra_name);
        this.mEtCharge = findViewById(R.id.et_charge);
        this.mEtApplyInfo = findViewById(R.id.et_apply_info);
        this.mBtnOk = findViewById(R.id.btn_ok);
    }

    private void initHeaderView(){
        this.mIvHeaderLeft = findViewById(R.id.iv_header_left);
        this.mTvHeaderCenter = findViewById(R.id.tv_header_center);
        this.mIvHeaderRight = findViewById(R.id.iv_header_right);

        this.mIvHeaderRight.setVisibility(View.INVISIBLE);
        this.mTvHeaderCenter.setText("申请费用");
    }

    private void bindListener(){
        this.mIvHeaderLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
