package com.example.crmempty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesOrderAdd extends AppCompatActivity {

    private Button btnSubmitAddOrder;

    private EditText txtCustomerCustomerCode, txtItemItemCode, txtItemQuantity, txtItemPrice;

    ManageDB mdb = new ManageDB();
    private String ip = mdb.getIp();
    private String port = mdb.getPort();
    private String Classes = mdb.getClasses();
    private String database = mdb.getDatabase();
    private String username = mdb.getUsername();
    private String password = mdb.getPassword();

    private String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;

    private Connection connection = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order_add);
        btnSubmitAddOrder = findViewById(R.id.btnAddOrder);
        txtCustomerCustomerCode = findViewById(R.id.addCustomerCustomerCode);
        txtItemItemCode = findViewById(R.id.addItemItemCode);
        txtItemQuantity = findViewById(R.id.addItemQuantity);
        txtItemPrice = findViewById(R.id.addItemPrice);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnSubmitAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCustomerCustomerCode.getText().toString().trim().matches("") ||
                        txtItemItemCode.getText().toString().trim().matches("") ||
                        txtItemQuantity.getText().toString().trim().matches("") ||
                        txtItemPrice.getText().toString().trim().matches("")) {
                    Toast.makeText(SalesOrderAdd.this, "fill all inputs", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (connection != null) {
                            String query = "EXECUTE spAddORDER @OrdDate='2021-02-06', @CustomerCode= ? ,@ItemCode= ?, @ItemQuantity= ? ,@ItemPrice= ? , @QtyOrdered= ? , @QtyDelivered=0 ;";

                            PreparedStatement pst = connection.prepareStatement(query);
                            pst.setString(1, txtCustomerCustomerCode.getText().toString().trim());
                            pst.setString(2, txtItemItemCode.getText().toString().trim());
                            pst.setString(3, txtItemQuantity.getText().toString().trim());
                            pst.setString(4, txtItemPrice.getText().toString().trim());
                            pst.setString(5, txtItemQuantity.getText().toString().trim());
                            pst.executeUpdate();

                            Toast.makeText(SalesOrderAdd.this, "SalesOrder added", Toast.LENGTH_SHORT).show();
                            openActivityReturnToMainActivity();
                        } else {
                            Toast.makeText(SalesOrderAdd.this, "SalesOrder not added", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(SalesOrderAdd.this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public void openActivityReturnToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}