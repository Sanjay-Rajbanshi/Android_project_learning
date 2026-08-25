package com.example.myapplication2;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.app.Service;

import com.example.myapplication2.data.roomdata.AppDatabase;
import com.example.myapplication2.data.roomdata.Transaction;


public class PaymentBoundService extends Service {
    private static final String TAG = "PaymentBoundService";

    private ExecutorService executorService;

    private AppDatabase database;

    private final IPaymentService.Stub binder = new IPaymentService.Stub() {

        @Override
        public void processPayment(
                double amount,
                String cardNo,
                String cardHolderName,
                String cvv,
                String expiryDate,
                String remarks) {

            PaymentBoundService.this.processPayment(
                    amount,
                    cardNo,
                    cardHolderName,
                    cvv,
                    expiryDate,
                    remarks
            );
        }

        @Override
        public List<TransactionData> getTransactions(){
            List<Transaction>transactions = database.transactionDao().getAllTransactions();
            List<TransactionData> result = new ArrayList<>();

            for (Transaction transaction: transactions){
                result.add(new TransactionData(
                        transaction.getTid(),
                        transaction.getAmount(),
                        transaction.getCardNo(),
                        transaction.getCardHolderName(),
                        transaction.getCvv(),
                        transaction.getExpiryDate(),
                        transaction.getDatetime(),
                        transaction.getRemarks()

                ));
            }
            return result;
        }

        //to delete transaction
        @Override
        public void deleteTransaction(int transactionId){
            executorService.execute(()->{
                database.transactionDao().deleteTransactionById(transactionId);

                Log.d(TAG, "Transaction deleted ID = " + transactionId);
            });
        }


    };

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "Service created");

        executorService = Executors.newSingleThreadExecutor();
        database = AppDatabase.getDatabase(getApplicationContext());
        Log.d(TAG, "DATABASE CREATED: " + database);
    }

    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG, "SERVICE onBind");
        return binder;
    }


    public void processPayment(double amount,String cardNo, String cardHolderName,  String cvv, String expiryDate, String remarks) {

        executorService.execute(() -> {
            Log.d(TAG, "Payment processing");


            try{
                // just for learning, simulate connecting to payment server
                Log.d(TAG, "Connecting to payment server");
                Thread.sleep(1000);

                Log.d(TAG,"Verifying payment");
                Thread.sleep(1000);


                Log.d(TAG,"Payment successfull");
                Thread.sleep(1000);

                Log.d(TAG, "Amount: " + amount);
                Log.d(TAG,"Card No: " + cardNo);
                Log.d(TAG, "Card Holder Name: " + cardHolderName);
                Log.d(TAG, "CVV: " + cvv);
                Log.d(TAG, "Expiry Date: " + expiryDate);
                Log.d(TAG, "Remarks: " + remarks);

                // Current date and time
                String currentDateTime =
                        new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss",
                                Locale.getDefault()
                        ).format(new Date());

                // Create Transaction object
                Transaction transaction = new Transaction(
                        0,
                        String.valueOf(amount),
                        cardNo,
                        cardHolderName,
                        cvv,
                        expiryDate,
                        currentDateTime,
                        remarks
                );

                // Save to Room database
                long transactionId =
                        database.transactionDao()
                                .InsertTransaction(transaction);

                Log.d(
                        TAG,
                        "Transaction saved ID = " + transactionId
                );


            } catch (InterruptedException e){

                Thread.currentThread().interrupt();
                Log.e(TAG, "Payment processing interrupted", e);

            }



        });

    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Service is destroyed");
        executorService.shutdown();
    }

}
