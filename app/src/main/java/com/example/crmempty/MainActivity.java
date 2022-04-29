package com.example.crmempty;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    //androidx.appcompat.widget.Toolbar
    private Toolbar toolbar;
    private NavigationView navView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        navView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.fragment);


        NavigationUI.setupWithNavController(navView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout);
    }

    public void addWarehouse(View view) {
        openActivityAddWarehouse();
    }

    public void openActivityAddWarehouse() {
        Intent intent = new Intent(this, WarehouseAdd.class);
        startActivity(intent);
    }

    public void addItemCat(View view) {
        openActivityAddItemCat();
    }

    private void openActivityAddItemCat() {
        Intent intent = new Intent(this, ItemCatAdd.class);
        startActivity(intent);
    }

    public void addCustomer(View view) {
        openActivityAddCustomer();
    }

    private void openActivityAddCustomer() {
        Intent intent = new Intent(this, CustomerAdd.class);
        startActivity(intent);
    }

    public void addItem(View view) {
        openActivityAddItem();
    }

    private void openActivityAddItem() {
        Intent intent = new Intent(this, ItemAdd.class);
        startActivity(intent);
    }

    public void addOrder(View view) {
        openActivityAddOrder();
    }

    private void openActivityAddOrder() {
        Intent intent = new Intent(this, SalesOrderAdd.class);
        startActivity(intent);
    }
}