package com.example.myfirstapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication2.IPaymentService;

public class MainActivity extends AppCompatActivity {

private IPaymentService paymentService;
private boolean isBound = false;

public IPaymentService getPaymentService(){
    return paymentService;
}

private final ServiceConnection serviceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        paymentService = IPaymentService.Stub.asInterface(iBinder);
        isBound = true;

        Log.d("AIDL", "Connected to payment service");
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

        paymentService = null;
        isBound = false;

        Log.d("AIDL", "Disconnected from the payment service");
    }
};

@Override
protected void onStart(){
    super.onStart();
    Intent intent = new Intent("com.example.myapplication2.IPaymentService");
    intent.setPackage("com.example.myapplication2");
    boolean result = bindService(intent,
            serviceConnection,
            BIND_AUTO_CREATE);
    Log.d("AIDL Client", "bindService result" + result);
}

@Override
protected void onStop(){
    super.onStop();
    if(isBound){
        unbindService(serviceConnection);
        isBound = false;
        paymentService = null;
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn_Home = findViewById(R.id.btnHome);
        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                this is fragment manager
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });


        Button  btnPayment = findViewById(R.id.btnPayment);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            this fragment manager will manage this fragment
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PaymentFormFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("")
                        .commit();
            }
        });



        ImageButton imgBtn = (ImageButton) findViewById(R.id.btnPaymentHistory);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TransactionHistoryActivity.class);
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "btn is clicked", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getSupportFragmentManager();
              fragmentManager.beginTransaction()
                     .replace(R.id.fragmentContainerView, TransactionHistoryFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();



            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                    |
                            WindowInsetsCompat.Type.ime()
            );
            view.setPadding(
                    insets.left,
                    insets.top,
                    insets.right,
                    insets.bottom
            );
            return windowInsets;

        });
    }
}