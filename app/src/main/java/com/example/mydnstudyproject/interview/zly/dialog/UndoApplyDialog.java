package com.example.mydnstudyproject.interview.zly.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mydnstudyproject.R;

public class UndoApplyDialog extends DialogFragment {

    private TextView mTvCacel;
    private TextView mTvOk;

    private OnDialogClick mOnDialogClick;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //设置dialog的基本样式参数
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getDialog().getWindow();

        //去掉dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的位置在底部
        lp.gravity = Gravity.BOTTOM;
        //设置dialog的动画
        lp.windowAnimations = R.style.undoApplyDialogStyle;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());

        View view = inflater.inflate(R.layout.dialog_undo_apply, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        initView(view);
        return view;
    }

    private void initView(View rootView){

        this.mTvOk = rootView.findViewById(R.id.tv_dialog_ok);
        this.mTvCacel = rootView.findViewById(R.id.tv_dialog_cancel);

        this.mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnDialogClick != null){
                    mOnDialogClick.onSure();
                }
            }
        });

        this.mTvCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnDialogClick != null){
                    mOnDialogClick.onCancel();
                }
            }
        });
    }

    public void setOnDialogClick(OnDialogClick onDialogClick) {
        this.mOnDialogClick = onDialogClick;
    }

    public interface OnDialogClick{
        void onCancel();
        void onSure();
    }
}
