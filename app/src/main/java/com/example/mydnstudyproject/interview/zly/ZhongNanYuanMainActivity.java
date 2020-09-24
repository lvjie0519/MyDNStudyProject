package com.example.mydnstudyproject.interview.zly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydnstudyproject.R;

public class ZhongNanYuanMainActivity extends AppCompatActivity {

    // header 部分
    private ImageView mIvHeaderLeft;
    private TextView mTvHeaderCenter;
    private ImageView mIvHeaderRight;

    private RecyclerView mRvApplyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_nan_yuan_main);

        this.initView();
        this.bindListener();
    }

    private void initView(){
        this.initHeaderView();
        this.initContentView();
    }

    private void initHeaderView(){
        this.mIvHeaderLeft = findViewById(R.id.iv_header_left);
        this.mTvHeaderCenter = findViewById(R.id.tv_header_center);
        this.mIvHeaderRight = findViewById(R.id.iv_header_right);

        this.mIvHeaderLeft.setVisibility(View.INVISIBLE);
        this.mTvHeaderCenter.setText("申请列表");
    }

    private void initContentView(){
        this.mRvApplyList = findViewById(R.id.rv_apply_list);
    }

    private void bindListener(){
        this.mIvHeaderRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
