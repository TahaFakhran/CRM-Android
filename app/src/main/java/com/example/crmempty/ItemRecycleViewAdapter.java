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

public class ItemRecycleViewAdapter extends RecyclerView.Adapter<ItemRecycleViewAdapter.ItemViewHolder> {

    private ArrayList<ItemInfo> list = new ArrayList<ItemInfo>();
    private Context context;

    public ItemRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        ItemRecycleViewAdapter.ItemViewHolder holder = new ItemRecycleViewAdapter.ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.txtItemCode.setText(list.get(position).getItemCode());
        holder.txtItemName.setText(list.get(position).getItemName());
        holder.txtItemCode.setText(list.get(position).getItemCatCode());
        holder.txtItemUnit.setText(list.get(position).getItemUnit());

        holder.cvItemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getItemName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<ItemInfo> getList() {
        return list;
    }

    public void setList(ArrayList<ItemInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemCode, txtItemName, txtItemCatCode, txtItemUnit;
        private CardView cvItemParent;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemCode = itemView.findViewById(R.id.txtItemCode);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemCatCode = itemView.findViewById(R.id.txtItemItemCatCode);
            txtItemUnit = itemView.findViewById(R.id.txtItemUnit);

            cvItemParent = itemView.findViewById(R.id.cvItemParent);
        }
    }

}
