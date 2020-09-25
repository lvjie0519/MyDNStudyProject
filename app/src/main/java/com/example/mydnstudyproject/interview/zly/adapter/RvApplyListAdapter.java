package com.example.mydnstudyproject.interview.zly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydnstudyproject.R;
import com.example.mydnstudyproject.interview.zly.db.table.TUserApply;

import java.util.List;

public class RvApplyListAdapter extends RecyclerView.Adapter<RvApplyListAdapter.ViewHolder> {

    private Context mContext;
    private List<TUserApply> mDatas;


    public RvApplyListAdapter(Context context, List<TUserApply> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_rv_apply_list, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateView(mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0:mDatas.size();
    }

    public void setDatas(List<TUserApply> datas) {
        this.mDatas = datas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private View layoutView;
        private TextView tvNameAndTime;
        private TextView tvApplyInfo;
        private TextView tvApplyStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.initView(itemView);
        }

        private void initView(View rootView){
            this.layoutView = rootView.findViewById(R.id.layout_item);
            this.tvNameAndTime = rootView.findViewById(R.id.tv_name_time);
            this.tvApplyInfo = rootView.findViewById(R.id.tv_apply_info);
            this.tvApplyStatus = rootView.findViewById(R.id.tv_apply_status);
        }

        private void updateView(TUserApply userApply, int position){
            String nameAndTime = userApply.getUserName()+"    "+userApply.getApplyTime();
            this.tvNameAndTime.setText(nameAndTime);
            this.tvApplyInfo.setText(userApply.getApplyReason());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position, TUserApply userApply);
    }
}
