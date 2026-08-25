package com.example.myapplication2;


import android.os.Parcelable;

import android.os.Parcel;

public class TransactionData implements Parcelable {
    private int tid;
    private String amount;
    private String cardNo;
    private String cardHolderName;
    private String cvv;

    private String expiryDate;
    private String datetime;
    private String remarks;
    private String application;

    public TransactionData(int tid, String amount, String cardNo, String cardHolderName, String cvv,  String expiryDate, String datetime,  String remarks, String application) {
        this.tid = tid;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.amount = amount;
        this.datetime = datetime;
        this.remarks = remarks;
        this.application = application;
    }


    protected TransactionData(Parcel in){
        tid = in.readInt();
        amount = in.readString();
        cardNo = in.readString();
        cardHolderName = in.readString();
        cvv = in.readString();
        expiryDate = in.readString();
        datetime = in.readString();
        remarks = in.readString();
        application = in.readString();
    }
    public static final Creator<TransactionData> CREATOR = new Creator<TransactionData>() {
        @Override
        public TransactionData createFromParcel(Parcel source) {
            return new TransactionData(source);
        }

        @Override
        public TransactionData[] newArray(int size) {
            return new TransactionData[size];
        }
    };

    public int getTid() {
        return tid;
    }


    public String getAmount() {
        return amount;
    }



    public String getCardNo() {
        return cardNo;
    }



    public String getCardHolderName() {
        return cardHolderName;
    }



    public String getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
    public String getDatetime(){
        return datetime;
    }
    public String getRemarks() {
        return remarks;
    }
public String getApplication(){ return application;}



    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(tid);
        dest.writeString(amount);
        dest.writeString(cardNo);
        dest.writeString(cardHolderName);
        dest.writeString(cvv);
        dest.writeString(expiryDate);
        dest.writeString(datetime);
        dest.writeString(remarks);
        dest.writeString(application);
    }
}
