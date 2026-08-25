package com.example.myapplication2.data.roomdata;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_history_table")
public class Transaction {


    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "amount")
    public String amount;
    @ColumnInfo(name = "cardNo")
    public String cardNo;
    @ColumnInfo(name = "cardHolderName")
    public String cardHolderName;
    @ColumnInfo(name = "cvv")
    public String cvv;

    @ColumnInfo(name = "expiryDate")
    public String expiryDate;

    @ColumnInfo(name = "remarks")
    public String remarks;

    private String datetime;

@ColumnInfo(name= "application")
public String application;


    public Transaction(int tid, String amount, String cardNo, String cardHolderName, String cvv, String expiryDate, String datetime, String remarks, String application) {
        this.tid = tid;
        this.amount = amount;
        this.cardNo = cardNo;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.datetime = datetime;
        this.remarks = remarks;
        this.application = application;


    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

}
