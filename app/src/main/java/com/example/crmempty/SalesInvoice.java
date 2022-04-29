package com.example.crmempty;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesInvoice extends Fragment {

    private Button btnSubmitAddInvoice;

    private EditText txtInvoiceCustomerCode, txtInvoiceWhCode, txtInvoiceOrdNumber, txtInvoiceItemCode, txtInvoiceItemQuantity, txtInvoiceItemPrice;

    ManageDB mdb = new ManageDB();
    private String ip = mdb.getIp();
    private String port = mdb.getPort();
    private String Classes = mdb.getClasses();
    private String database = mdb.getDatabase();
    private String username = mdb.getUsername();
    private String password = mdb.getPassword();

    private String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;

    private Connection connection = null;

    public SalesInvoice() {
    }

    public static SalesInvoice newInstance() {
        SalesInvoice fragment = new SalesInvoice();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales_invoice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnSubmitAddInvoice = view.findViewById(R.id.btnAddInvoice);
        txtInvoiceCustomerCode = view.findViewById(R.id.addInvoiceCustomerCode);
        txtInvoiceWhCode = view.findViewById(R.id.addInvoiceWhCode);
        txtInvoiceOrdNumber = view.findViewById(R.id.addInvoiceOrdNumber);
        txtInvoiceItemCode = view.findViewById(R.id.addInvoiceItemCode);
        txtInvoiceItemQuantity = view.findViewById(R.id.addInvoiceItemQuantity);
        txtInvoiceItemPrice = view.findViewById(R.id.addInvoiceItemPrice);

        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

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

        btnSubmitAddInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInvoiceCustomerCode.getText().toString().trim().matches("") ||
                        txtInvoiceWhCode.getText().toString().trim().matches("") ||
                        txtInvoiceItemCode.getText().toString().trim().matches("") ||
                        txtInvoiceOrdNumber.getText().toString().trim().matches("") ||
                        txtInvoiceItemPrice.getText().toString().trim().matches("") ||
                        txtInvoiceItemQuantity.getText().toString().trim().matches("")) {
                    Toast.makeText(getActivity(), "fill all inputs", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (connection != null) {
                            String query = "EXECUTE spAddINVOICE @InvDate='2021-07-01', @CustomerCode= ? , @WhCode= ? ,@OrdNumber= ? ,@ItemCode= ? ,@ItemQuantity= ? ,@ItemPrice= ? ;";

                            PreparedStatement pst = connection.prepareStatement(query);
                            pst.setString(1, txtInvoiceCustomerCode.getText().toString().trim());
                            pst.setString(2, txtInvoiceWhCode.getText().toString().trim());
                            pst.setString(3, txtInvoiceOrdNumber.getText().toString().trim());
                            pst.setString(4, txtInvoiceItemCode.getText().toString().trim());
                            pst.setString(5, txtInvoiceItemQuantity.getText().toString().trim());
                            pst.setString(6, txtInvoiceItemPrice.getText().toString().trim());

                            pst.executeUpdate();

                            Toast.makeText(getActivity(), "Invoice added", Toast.LENGTH_SHORT).show();
                            openActivityReturnToMainActivity();
                        } else {
                            Toast.makeText(getActivity(), "Invoice not added", Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void openActivityReturnToMainActivity() {
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }
}

