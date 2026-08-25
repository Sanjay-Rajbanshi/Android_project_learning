

package com.example.myapplication2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.toolbar);


        // Get NavHostFragment
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_content_main);

        navController = navHostFragment.getNavController();


        navController.addOnDestinationChangedListener(
                (controller, destination, agruments)->{
                    if(destination.getId() ==  R.id.homeFragment){
                        binding.toolbar.setTitle("");

                    }
                    else if(destination.getId() == R.id.paymentFormFragment){
                        binding.toolbar.setTitle("New Payment");
                    } else if (destination.getId() == R.id.transactionHistoryFragment) {
                        binding.toolbar.setTitle("Transaction History");

                    }
                }
        );


        // Navigation configuration
        appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.homeFragment
                ).build();

        NavigationUI.setupActionBarWithNavController(
                this,
                navController,
                appBarConfiguration
        );


        // PLUS BUTTON
        binding.btnNewPayment.setOnClickListener(v -> {

//            navController.navigate(
//                    R.id.action_homeFragment_to_paymentFormFragment
//            );

            int currentDestination = navController.getCurrentDestination().getId();
            if(currentDestination == R.id.homeFragment){
                navController.navigate(
                    R.id.action_homeFragment_to_paymentFormFragment
            );
            } else if(currentDestination == R.id.transactionHistoryFragment){
                navController.navigate(R.id.action_transactionHistoryFragment_to_paymentFormFragment);
            }

        });

        binding.btnPaymentHistory.setOnClickListener( v ->{
//            navController.navigate(R.id.action_homeFragment_to_transactionHistoryFragment);
            int currentDestination = navController.getCurrentDestination().getId();
            if(currentDestination == R.id.homeFragment){
                navController.navigate(
                        R.id.action_homeFragment_to_transactionHistoryFragment
                );
            } else if(currentDestination == R.id.paymentFormFragment){
                navController.navigate(R.id.action_paymentFormFragment_to_transactionHistoryFragment);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(
                navController,
                appBarConfiguration
        ) || super.onSupportNavigateUp();
    }


}