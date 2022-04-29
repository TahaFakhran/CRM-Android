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

public class ItemCatAdd extends AppCompatActivity {

    private Button btnSubmitAddItemCategory;

    private EditText txtItemCatCode, txtItemCatName;

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
        setContentView(R.layout.activity_item_cat_add);
        btnSubmitAddItemCategory = findViewById(R.id.btnAddItemCat);
        txtItemCatCode = findViewById(R.id.addItemCatCode);
        txtItemCatName = findViewById(R.id.addItemCatName);

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

        btnSubmitAddItemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtItemCatCode.getText().toString().trim().matches("") ||
                        txtItemCatName.getText().toString().trim().matches("")) {
                    Toast.makeText(ItemCatAdd.this, "fill all inputs", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (connection != null) {
                            String query = "EXECUTE spAddITEMCATEGORY ?,  ?  ;";

                            PreparedStatement pst = connection.prepareStatement(query);
                            pst.setString(1, txtItemCatCode.getText().toString().trim());
                            pst.setString(2, txtItemCatName.getText().toString().trim());
                            pst.executeUpdate();

                            Toast.makeText(ItemCatAdd.this, "ItemCat added", Toast.LENGTH_SHORT).show();
                            openActivityReturnToMainActivity();
                        } else {
                            Toast.makeText(ItemCatAdd.this, "ItemCat not added", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(ItemCatAdd.this, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void openActivityReturnToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}