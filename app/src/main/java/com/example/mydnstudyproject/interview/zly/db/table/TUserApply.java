package com.example.mydnstudyproject.interview.zly.db.table;

import com.example.mydnstudyproject.interview.zly.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class TUserApply extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String userName;

    @Column
    private String extraUserName;

    @Column
    private String applyReason;

    @Column
    private double price;

    @Column
    private String applyTime;

    public TUserApply() {
    }

    public TUserApply(String userName, String extraUserName, String applyReason, double price, String applyTime) {
        this.userName = userName;
        this.extraUserName = extraUserName;
        this.applyReason = applyReason;
        this.price = price;
        this.applyTime = applyTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExtraUserName() {
        return extraUserName;
    }

    public void setExtraUserName(String extraUserName) {
        this.extraUserName = extraUserName;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        return "TUserApply{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", extraUserName='" + extraUserName + '\'' +
                ", applyReason='" + applyReason + '\'' +
                ", price=" + price +
                ", applyTime='" + applyTime + '\'' +
                '}';
    }
}
