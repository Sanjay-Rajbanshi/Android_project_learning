package com.example.myfirstapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.TransactionData;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter
        extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private final Context context;

    private final List<TransactionData> transactionList;

    public TransactionAdapter(
            Context context,
            List<TransactionData> transactionList) {

        this.context = context;
        this.transactionList = new ArrayList<>(transactionList);
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);

        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull TransactionViewHolder holder,
            int position) {

        TransactionData transaction =
                transactionList.get(position);

        holder.trnsactionAmount.setText(
                "Amount: " + transaction.getAmount()
        );

        holder.itemView.setOnClickListener(v ->
                showTransactionDialog(transaction)
        );
    }

    public void setTransactionList(
            List<TransactionData> newList) {

        transactionList.clear();

        if (newList != null) {
            transactionList.addAll(newList);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class TransactionViewHolder
            extends RecyclerView.ViewHolder {

        TextView trnsactionAmount;

        public TransactionViewHolder(
                @NonNull View itemView) {

            super(itemView);

            trnsactionAmount =
                    itemView.findViewById(R.id.tdAmount);
        }
    }

    private void showTransactionDialog(
            TransactionData transaction) {

        View view = LayoutInflater.from(context)
                .inflate(
                        R.layout.activity_transaction_detailed,
                        null
                );

        TextView amount =
                view.findViewById(R.id.txtDetailAmount);

        TextView cardNo =
                view.findViewById(R.id.txtDetailCardNo);

        TextView cardHolderName =
                view.findViewById(
                        R.id.txtDetailCardHolderName
                );

        TextView cvv =
                view.findViewById(R.id.txtCvv);

        TextView expiry =
                view.findViewById(R.id.txtExpiryDate);

        EditText remarks =
                view.findViewById(R.id.txtRemarks);

        TextView date =
                view.findViewById(R.id.txtTransactionDate);

        TextView time =
                view.findViewById(R.id.txtTransactionTime);

        Button btnDelete =
                view.findViewById(
                        R.id.btnDeleteTransaction
                );

        Button btnUpdate =
                view.findViewById(
                        R.id.btnUpdateTransaction
                );

        amount.setText(
                "Amount : " + transaction.getAmount()
        );

        cardNo.setText(
                "Card No : " + transaction.getCardNo()
        );

        cardHolderName.setText(
                "Name : " + transaction.getCardHolderName()
        );

        cvv.setText(
                "CVV : " + transaction.getCvv()
        );

        expiry.setText(
                "Expiry : " + transaction.getExpiryDate()
        );

        remarks.setText(
                transaction.getRemarks()
        );

        String dateTime =
                transaction.getDatetime();

        if (dateTime != null &&
                dateTime.contains(" ")) {

            String[] parts =
                    dateTime.split(" ");

            date.setText(
                    "Date: " + parts[0]
            );

            time.setText(
                    "Time: " + parts[1]
            );

        } else {

            date.setText(
                    "Date: " +
                            (dateTime != null
                                    ? dateTime
                                    : "N/A")
            );

            time.setText("Time: N/A");
        }

        /*
         * Delete and update are disabled for now.
         *
         * Your current AIDL interface only has:
         *
         * processPayment()
         * getTransactions()
         *
         * Later we can add:
         *
         * deleteTransaction()
         * updateTransactionRemarks()
         */

        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);

        new AlertDialog.Builder(context)
                .setTitle("Transaction Detail")
                .setView(view)
                .setPositiveButton("Close", null)
                .show();
    }
}















//package com.example.myfirstapplication;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.os.RemoteException;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myapplication2.IPaymentService;
//import com.example.myapplication2.TransactionData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransactionAdapter
//        extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
//
//    private List<TransactionData> transactionList;
//    private List<TransactionData> transactionFullList;
//    private IPaymentService paymentService;
//
//    private Context context;
//
//
//    public TransactionAdapter(Context context,
//            List<TransactionData> transactionList) {
//
//        this.context = context;
//
//        this.transactionList = new ArrayList<>(transactionList);
//
//        this.transactionFullList = new ArrayList<>(transactionList);
//
//    }
//
//    @NonNull
//    @Override
//    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_transaction, parent, false);
//
//        return new TransactionViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
//
//        TransactionData transaction = transactionList.get(position);
//
//        holder.trnsactionAmount.setText("Amount: " + transaction.getAmount());
//
//        holder.itemView.setOnClickListener(v -> {
//            showTransactionDialog(transaction);
//        });
//    }
//
//    public void setTransactionList(List<TransactionData> newList) {
//
//        transactionList.clear();
//
//        transactionList.addAll(newList);
//
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return transactionList.size();
//    }
//
//    public static class TransactionViewHolder
//            extends RecyclerView.ViewHolder {
//
//        TextView trnsactionAmount;
//
//        public TransactionViewHolder(@NonNull View itemView) {
//
//            super(itemView);
//
//            trnsactionAmount =
//                    itemView.findViewById(R.id.tdAmount);
//        }
//    }
//
//
//    private void showTransactionDialog(TransactionData transaction) {
//
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_transaction_detail, null);
//
//        TextView amount = view.findViewById(R.id.txtDetailAmount);
//
//        TextView cardNo = view.findViewById(R.id.txtDetailCardNo);
//
//        TextView cardHolderName = view.findViewById(R.id.txtDetailCardHolderName);
//
//        TextView cvv = view.findViewById(R.id.txtCvv);
//
//        TextView expiry = view.findViewById(R.id.txtExpiryDate);
//
//        EditText remarks = view.findViewById(R.id.txtRemarks);
//
//        TextView date = view.findViewById(R.id.txtTransactionDate);
//
//        TextView time = view.findViewById(R.id.txtTransactionTime);
//
//        Button btnDeleteTransaction = view.findViewById(R.id.btnDeleteTransaction);
//
//        Button btnUpdateTransaction = view.findViewById(R.id.btnUpdateTransaction);
//
//
//        // Display transaction details
//
//        amount.setText(
//                "Amount : " + transaction.getAmount()
//        );
//
//        cardNo.setText(
//                "Card No : " + transaction.getCardNo()
//        );
//
//        cardHolderName.setText(
//                "Name : " + transaction.getCardHolderName()
//        );
//
//        cvv.setText(
//                "CVV : " + transaction.getCvv()
//        );
//
//        expiry.setText(
//                "Expiry : " + transaction.getExpiryDate()
//        );
//
//        remarks.setText(transaction.getRemarks()
//        );
//
//
//        // Date and time
//
//        String dateTimeValue = transaction.getDatetime();
//
//        String[] parts = dateTimeValue.split(" ");
//
//        String dateValue = parts[0];
//        String timeValue = parts[1];
//
//        date.setText(
//                "Date: " + dateValue
//        );
//
//        time.setText(
//                "Time: " + timeValue
//        );
//
//
//        // Transaction detail dialog
//
//        AlertDialog dialog = new AlertDialog.Builder(context)
//                        .setTitle("Transaction Detail")
//                        .setView(view)
//                        .setPositiveButton("Close", null)
//                        .create();
//
//        dialog.show();
//
//
//         //DELETE Transactions
//
//        btnDeleteTransaction.setOnClickListener(v -> {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setTitle("Delete Transaction");
//            builder.setMessage("Are you sure you want to delete this transaction?");
//            builder.setNegativeButton("Cancel",
//                    null
//            );
//            builder.setPositiveButton(
//                    "Delete",
//                    (confirmDialog, which) -> {
//
//                        if (paymentService == null) {
//                            return;
//                        }
//                        new Thread(() -> {
//                            try {
//                                paymentService.deleteTransaction(transaction.getTid());
//
//
//                                //update recyclerview on UI thread
//                                ((android.app.Activity) context).runOnUiThread(() -> {
//                                    transactionList.remove(transaction);
//                                    notifyDataSetChanged();
//                                    dialog.dismiss();
//                                });
//
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        }).start();
//                    }
//            );
//            builder.show();
//        });
//
//
//        // UPDATE REMARKS
//
////        btnUpdateTransaction.setOnClickListener(v -> {
////
////            if (!remarks.isEnabled()) {
////
////                // Enable editing
////
////                remarks.setEnabled(true);
////
////                remarks.setFocusable(true);
////
////                remarks.setFocusableInTouchMode(true);
////
////                remarks.requestFocus();
////
////                btnUpdateTransaction.setText(
////                        "Save"
////                );
////
////            } else {
////
////                // Get new remarks
////
////                String newRemarks =
////                        remarks.getText()
////                                .toString()
////                                .trim();
////
////
////                // Update through ViewModel
////
////                transactionViewModel.updateRemarks(
////                        transaction.getTid(),
////                        newRemarks
////                );
////
////
////                // Update current object
////
////                transaction.setRemarks(
////                        newRemarks
////                );
////
////
////                // Refresh RecyclerView
////
////                notifyDataSetChanged();
////
////
////                // Disable editing
////
////                remarks.setEnabled(false);
////
////                remarks.setFocusable(false);
////
////                remarks.setFocusableInTouchMode(false);
////
////
////                // Change button back
////
////                btnUpdateTransaction.setText(
////                        "Update"
////                );
////            }
////        });
//    }
//}
