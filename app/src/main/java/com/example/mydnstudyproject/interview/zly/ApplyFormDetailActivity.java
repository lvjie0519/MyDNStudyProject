package com.example.mydnstudyproject.interview.zly;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.mydnstudyproject.R;
import com.example.mydnstudyproject.interview.zly.db.table.TUserApply;
import com.example.mydnstudyproject.interview.zly.db.table.TUserApply_Table;
import com.example.mydnstudyproject.interview.zly.dialog.UndoApplyDialog;
import com.example.mydnstudyproject.interview.zly.utils.ToastUtil;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ApplyFormDetailActivity extends FragmentActivity {

    public static final int RESULT_CODE_DELETE_SUCCESS = 102;
    private static final String KEY_APPLY_ID = "apply_id";

    // header 部分
    private ImageView mIvHeaderLeft;
    private TextView mTvHeaderCenter;
    private ImageView mIvHeaderRight;
    private TextView mTvHeaderRight;

    private TextView mTvApplyId;
    private TextView mTvApplyStatus;
    private TextView mTvApplyDateTime;
    private TextView mTvApplyUserName;
    private TextView mTvApplyExtraUserName;
    private TextView mTvApplyCharge;
    private TextView mTvApplyInfo;

    private TUserApply mCurrentUserApply;
    private UndoApplyDialog mUndoApplyDialog;


    public static void startActivity(Activity context, int applyId, int requestId){
        Intent intent = new Intent(context, ApplyFormDetailActivity.class);
        intent.putExtra(KEY_APPLY_ID, applyId);
        context.startActivityForResult(intent, requestId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_form_detail);

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
        this.mTvHeaderRight = findViewById(R.id.tv_header_right);

        this.mIvHeaderRight.setVisibility(View.INVISIBLE);
        this.mTvHeaderCenter.setText("申请详情");
        this.mTvHeaderRight.setVisibility(View.VISIBLE);
    }

    private void initContentView(){
        this.mTvApplyId = findViewById(R.id.tv_apply_id);
        this.mTvApplyDateTime = findViewById(R.id.tv_apply_date_time);
        this.mTvApplyStatus = findViewById(R.id.tv_apply_status);
        this.mTvApplyUserName = findViewById(R.id.tv_apply_user_name);
        this.mTvApplyExtraUserName = findViewById(R.id.tv_apply_extra_user_name);
        this.mTvApplyCharge = findViewById(R.id.tv_apply_charge);
        this.mTvApplyInfo = findViewById(R.id.tv_apply_info);
    }

    private void bindListener() {
        this.mIvHeaderLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.mTvHeaderRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUndoApplyDialog();
            }
        });
    }

    private void requestData() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                int applyId = getIntent().getIntExtra(KEY_APPLY_ID, -1);
                mCurrentUserApply= SQLite.select().from(TUserApply.class).where(TUserApply_Table.id.eq(applyId) ).querySingle();
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        updateView(mCurrentUserApply);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtil.showToast(ApplyFormDetailActivity.this, "数据异常");
                    }
                });

    }

    private void updateView(TUserApply tUserApply){
        if(tUserApply != null){
            this.mTvApplyId.setText(""+tUserApply.getId());
            this.mTvApplyDateTime.setText(tUserApply.getApplyTime());
            this.mTvApplyStatus.setText("申请中");
            this.mTvApplyUserName.setText(tUserApply.getUserName());
            this.mTvApplyExtraUserName.setText(tUserApply.getExtraUserName());
            this.mTvApplyCharge.setText(tUserApply.getPrice()+"元");
            this.mTvApplyInfo.setText(tUserApply.getApplyReason());
        }
    }

    private void showUndoApplyDialog(){
        if(this.mUndoApplyDialog == null){
            this.mUndoApplyDialog = new UndoApplyDialog();

            this.mUndoApplyDialog.setOnDialogClick(new UndoApplyDialog.OnDialogClick() {
                @Override
                public void onCancel() {
                    mUndoApplyDialog.dismiss();
                }

                @Override
                public void onSure() {
                    SQLite.delete(TUserApply.class).where(TUserApply_Table.id.eq(mCurrentUserApply.getId())).execute();
                    ApplyFormDetailActivity.this.setResult(RESULT_CODE_DELETE_SUCCESS);
                    finish();
                }
            });
        }
        this.mUndoApplyDialog.show(getSupportFragmentManager(), "UndoApplyDialog");
    }
}
