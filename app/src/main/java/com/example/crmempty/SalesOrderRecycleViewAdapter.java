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

public class SalesOrderRecycleViewAdapter extends RecyclerView.Adapter<SalesOrderRecycleViewAdapter.SalesOrderViewHolder> {

    private ArrayList<SalesOrderInfo> list = new ArrayList<SalesOrderInfo>();
    private Context context;

    public SalesOrderRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SalesOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_view, parent, false);
        SalesOrderRecycleViewAdapter.SalesOrderViewHolder holder = new SalesOrderRecycleViewAdapter.SalesOrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SalesOrderViewHolder holder, int position) {
        holder.txtCustomerCode.setText(list.get(position).getCustomerCode());
        holder.txtItemCode.setText(list.get(position).getItemCode());
        holder.txtOrdNumber.setText(list.get(position).getOrdNumber());
        holder.txtQtyOrdered.setText(list.get(position).getQtyOrdered());
        holder.txtQtyDelivered.setText(list.get(position).getQtyDelivered());

        holder.cvOrderParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getOrdNumber(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<SalesOrderInfo> getList() {
        return list;
    }

    public void setList(ArrayList<SalesOrderInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class SalesOrderViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCustomerCode, txtItemCode, txtOrdNumber, txtQtyOrdered, txtQtyDelivered;
        private CardView cvOrderParent;

        public SalesOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCustomerCode = itemView.findViewById(R.id.txtOrderCustomerCode);
            txtItemCode = itemView.findViewById(R.id.txtOrderItemCode);
            txtOrdNumber = itemView.findViewById(R.id.txtOrderOrdNumber);
            txtQtyOrdered = itemView.findViewById(R.id.txtQtyOrdered);
            txtQtyDelivered = itemView.findViewById(R.id.txtQtyDelivered);

            cvOrderParent = itemView.findViewById(R.id.cvOrderParent);

        }
    }
}
