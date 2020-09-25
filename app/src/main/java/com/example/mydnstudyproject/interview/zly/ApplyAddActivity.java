package com.example.mydnstudyproject.interview.zly;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydnstudyproject.R;
import com.example.mydnstudyproject.interview.zly.db.table.TUserApply;
import com.example.mydnstudyproject.interview.zly.utils.ToastUtil;

public class ApplyAddActivity extends Activity {

    public static final int RESULT_CODE_ADD_SUCCESS = 101;

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

    public static void startActivityForResult(Activity context, int requestCode){
        Intent intent = new Intent(context, ApplyAddActivity.class);
        context.startActivityForResult(intent, requestCode);
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
                String name = mEtName.getText().toString();
                String extraName = mEtExtraName.getText().toString();
                String sCharge = mEtCharge.getText().toString();
                String applyInfo = mEtApplyInfo.getText().toString();

                if(TextUtils.isEmpty(name)){
                    ToastUtil.showToast(ApplyAddActivity.this, "请输入姓名");
                    return;
                }

                if(TextUtils.isEmpty(extraName)){
                    ToastUtil.showToast(ApplyAddActivity.this, "请输入陪同人的姓名");
                    return;
                }

                if(TextUtils.isEmpty(sCharge)){
                    ToastUtil.showToast(ApplyAddActivity.this, "申请的费用不能为空");
                    return;
                }

                if(TextUtils.isEmpty(applyInfo)){
                    ToastUtil.showToast(ApplyAddActivity.this, "请输入申请事由");
                    return;
                }

                double price = 0;
                try {
                    price = Double.valueOf(sCharge);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(ApplyAddActivity.this, "输入的费用格式不对");
                    return;
                }


                TUserApply userApply = new TUserApply(name, extraName,
                        applyInfo, price, "2020-8-12 12:24:10");
                userApply.insert();

                ApplyAddActivity.this.setResult(RESULT_CODE_ADD_SUCCESS);
                finish();
            }
        });
    }
}
