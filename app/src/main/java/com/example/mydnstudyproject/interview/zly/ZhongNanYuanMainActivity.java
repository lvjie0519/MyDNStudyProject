package com.example.mydnstudyproject.interview.zly;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydnstudyproject.R;
import com.example.mydnstudyproject.interview.zly.db.table.TUserApply;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ZhongNanYuanMainActivity extends Activity {

    // header 部分
    private ImageView mIvHeaderLeft;
    private TextView mTvHeaderCenter;
    private ImageView mIvHeaderRight;

    private RecyclerView mRvApplyList;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ZhongNanYuanMainActivity.class);
        context.startActivity(intent);
    }

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
//                TUserApply userApply = new TUserApply("zhangshan", "lishi",
//                        "出差", 1000.12, "2020-8-12 12:24:10");
//                userApply.insert();

//                List<TUserApply> datas = SQLite.select().from(TUserApply.class).queryList();
//                Log.i("lvjie", datas.toString());
            }
        });
    }
}
