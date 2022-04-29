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

public class CustomerAdd extends AppCompatActivity {

    private Button btnSubmitAddCustomer;

    private EditText txtCustomerCode, txtCustomerName, txtCustomerAddress;

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
        setContentView(R.layout.activity_customer_add);
        txtCustomerCode = findViewById(R.id.addCustomerCode);
        txtCustomerName = findViewById(R.id.addCustomerName);
        txtCustomerAddress = findViewById(R.id.addCustomerAddress);

        btnSubmitAddCustomer = findViewById(R.id.btnAddCustomer);

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

        btnSubmitAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCustomerCode.getText().toString().trim().matches("") ||
                        txtCustomerName.getText().toString().trim().matches("") ||
                        txtCustomerAddress.getText().toString().trim().matches("")) {
                    Toast.makeText(CustomerAdd.this, "fill all inputs", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (connection != null) {
                            String query = "EXECUTE spAddCUSTOMER  ?,  ? ,  ?  ;";

                            PreparedStatement pst = connection.prepareStatement(query);
                            pst.setString(1, txtCustomerCode.getText().toString().trim());
                            pst.setString(2, txtCustomerName.getText().toString().trim());
                            pst.setString(3, txtCustomerAddress.getText().toString().trim());
                            pst.executeUpdate();

                            Toast.makeText(CustomerAdd.this, "Customer added", Toast.LENGTH_SHORT).show();
                            openActivityReturnToMainActivity();
                        } else {
                            Toast.makeText(CustomerAdd.this, "Customer not added", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(CustomerAdd.this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
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