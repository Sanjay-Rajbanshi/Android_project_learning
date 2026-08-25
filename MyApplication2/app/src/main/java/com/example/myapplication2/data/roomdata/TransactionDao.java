package com.example.myapplication2.data.roomdata;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long InsertTransaction(Transaction transaction);

    @Query("SELECT * FROM `transaction_history_table` ORDER BY datetime DESC")
    List<Transaction> getAllTransactions();

    @Query("SELECT * FROM transaction_history_table WHERE tid = :id")
    Transaction getTransactionById(int id);

    @Update
    void updateTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

    @Query("DELETE FROM transaction_history_table WHERE tid = :transactionId")
    void deleteTransactionById(int transactionId);
}
