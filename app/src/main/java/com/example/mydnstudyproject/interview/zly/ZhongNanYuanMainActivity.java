package com.example.mydnstudyproject.interview.zly;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.mydnstudyproject.interview.zly.adapter.RvApplyListAdapter;
import com.example.mydnstudyproject.interview.zly.db.table.TUserApply;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ZhongNanYuanMainActivity extends Activity {

    // header 部分
    private ImageView mIvHeaderLeft;
    private TextView mTvHeaderCenter;
    private ImageView mIvHeaderRight;

    private RecyclerView mRvApplyList;
    private RvApplyListAdapter mAdapter;
    private List<TUserApply> mDatas;

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
        this.requestData();
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

        mRvApplyList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.icon_list_item_divider));
        mRvApplyList.addItemDecoration(itemDecoration);
        mAdapter = new RvApplyListAdapter(this, mDatas);
        mRvApplyList.setAdapter(mAdapter);
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

    private void requestData(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                mDatas = SQLite.select().from(TUserApply.class).queryList();
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mDatas = SQLite.select().from(TUserApply.class).queryList();
                        mAdapter.setDatas(mDatas);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
}
