package com.example.crmempty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class WarehouseRecycleViewAdapter extends RecyclerView.Adapter<WarehouseRecycleViewAdapter.WareViewHolder> {

    private ArrayList<WarehouseInfo> list = new ArrayList<WarehouseInfo>();
    private Context context;

    public WarehouseRecycleViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public WareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.warehouse_card_view, parent, false);
        WareViewHolder holder = new WareViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WareViewHolder holder, int position) {
        holder.txtWhCode.setText(list.get(position).getWhCode());
        holder.txtWhName.setText(list.get(position).getWhName());

        holder.cvWareParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getWhName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<WarehouseInfo> getList() {
        return list;
    }

    public void setList(ArrayList<WarehouseInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class WareViewHolder extends RecyclerView.ViewHolder {

        private TextView txtWhCode, txtWhName;
        private CardView cvWareParent;

        public WareViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWhCode = itemView.findViewById(R.id.txtWhCode);
            txtWhName = itemView.findViewById(R.id.txtWhName);

            cvWareParent = itemView.findViewById(R.id.cvWareParent);
        }
    }

}
