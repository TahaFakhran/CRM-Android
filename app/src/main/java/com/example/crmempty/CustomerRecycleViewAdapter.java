package com.example.crmempty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerRecycleViewAdapter extends RecyclerView.Adapter<CustomerRecycleViewAdapter.CustomerViewHolder> {

    private ArrayList<CustomerInfo> list = new ArrayList<CustomerInfo>();
    private Context context;

    public CustomerRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_card_view, parent, false);
        CustomerRecycleViewAdapter.CustomerViewHolder holder = new CustomerRecycleViewAdapter.CustomerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.txtCustomerCode.setText(list.get(position).getCustomerCode());
        holder.txtCustomerName.setText(list.get(position).getCustomerName());
        holder.txtCustomerAddress.setText(list.get(position).getCustomerAddress());

        holder.cvCustomerParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getCustomerName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<CustomerInfo> getList() {
        return list;
    }

    public void setList(ArrayList<CustomerInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCustomerCode, txtCustomerName, txtCustomerAddress;
        private CardView cvCustomerParent;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCustomerCode = itemView.findViewById(R.id.txtCustomerCode);
            txtCustomerName = itemView.findViewById(R.id.txtCustomerName);
            txtCustomerAddress = itemView.findViewById(R.id.txtCustomerAddress);

            cvCustomerParent = itemView.findViewById(R.id.cvCustomerParent);
        }
    }
}
