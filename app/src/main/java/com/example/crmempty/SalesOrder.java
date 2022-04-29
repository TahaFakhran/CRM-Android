package com.example.crmempty;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.*;
import java.util.ArrayList;

public class SalesOrder extends Fragment {

    ManageDB mdb = new ManageDB();
    private String ip = mdb.getIp();
    private String port = mdb.getPort();
    private String Classes = mdb.getClasses();
    private String database = mdb.getDatabase();
    private String username = mdb.getUsername();
    private String password = mdb.getPassword();

    private String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;

    private Connection connection = null;
    private RecyclerView rv;

    public SalesOrder() {
    }

    public static SalesOrder newInstance(String param1, String param2) {
        SalesOrder fragment = new SalesOrder();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SalesOrderInfo> list = new ArrayList<SalesOrderInfo>();

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
        if (connection != null) {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT CustomerCode, ItemCode, OrdNumber, QtyOrdered, QtyDelivered FROM [crm].[dbo].[ORDER] ");
                while (resultSet.next()) {
                    list.add(new SalesOrderInfo(resultSet.getString("CustomerCode"),resultSet.getString("ItemCode"),resultSet.getString("OrdNumber"),resultSet.getString("QtyOrdered"),resultSet.getString("QtyDelivered")));
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        rv = view.findViewById(R.id.OrderRecView);

        SalesOrderRecycleViewAdapter adapter = new SalesOrderRecycleViewAdapter(this.getActivity());
        adapter.setList(list);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

}