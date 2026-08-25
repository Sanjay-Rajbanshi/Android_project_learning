package com.example.myfirstapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication2.TransactionData;

public class TransactionDetailedActivity extends AppCompatActivity {

    private TextView transactionDate;
    private TextView transactionTime;
    private TextView transactionAmount;
    private TextView transactionCardNo;
    private TextView transactionCardHolderName;
    private TextView transactionCvv;
    private TextView transactionExpiryDate;
    private TextView transactionRemarks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transaction_detailed);

        // Find views
        transactionDate =
                findViewById(R.id.txtTransactionDate);

        transactionTime =
                findViewById(R.id.txtTransactionTime);

        transactionAmount =
                findViewById(R.id.txtDetailAmount);

        transactionCardNo =
                findViewById(R.id.txtDetailCardNo);

        transactionCardHolderName =
                findViewById(R.id.txtDetailCardHolderName);

        transactionCvv =
                findViewById(R.id.txtCvv);

        transactionExpiryDate =
                findViewById(R.id.txtExpiryDate);

        transactionRemarks =
                findViewById(R.id.txtRemarks);


        // Get TransactionData from Intent
        TransactionData transaction =
                getIntent().getParcelableExtra(
                        "transaction"
                );


        if (transaction == null) {

            finish();

            return;
        }


        // Display transaction
        showTransaction(transaction);
    }


    @SuppressLint("SetTextI18n")
    private void showTransaction(
            TransactionData transaction) {

        transactionAmount.setText(
                "Amount: " +
                        transaction.getAmount()
        );


        transactionCardNo.setText(
                "Card No: " +
                        transaction.getCardNo()
        );


        transactionCardHolderName.setText(
                "Card Holder Name: " +
                        transaction.getCardHolderName()
        );


        transactionCvv.setText(
                "CVV: " +
                        transaction.getCvv()
        );


        transactionExpiryDate.setText(
                "Expiry Date: " +
                        transaction.getExpiryDate()
        );


        transactionRemarks.setText(
                "Remarks: " +
                        transaction.getRemarks()
        );


        // Date and time
        String dateTime =
                transaction.getDatetime();


        if (dateTime != null &&
                dateTime.contains(" ")) {

            String[] parts =
                    dateTime.split(" ");

            transactionDate.setText(
                    "Date: " + parts[0]
            );

            transactionTime.setText(
                    "Time: " + parts[1]
            );

        } else {

            transactionDate.setText(
                    "Date: " +
                            (dateTime != null
                                    ? dateTime
                                    : "N/A")
            );

            transactionTime.setText(
                    "Time: N/A"
            );
        }
    }
}