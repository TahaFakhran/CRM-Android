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

public class ItemCatRecycleViewAdapter extends RecyclerView.Adapter<ItemCatRecycleViewAdapter.ItemCatViewHolder> {

    private ArrayList<ItemCatInfo> list = new ArrayList<ItemCatInfo>();
    private Context context;

    public ItemCatRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcat_card_view, parent, false);
        ItemCatRecycleViewAdapter.ItemCatViewHolder holder = new ItemCatRecycleViewAdapter.ItemCatViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCatViewHolder holder, int position) {
        holder.txtItemCatCode.setText(list.get(position).getItemCatCode());
        holder.txtItemCatName.setText(list.get(position).getItemCatName());

        holder.cvItemCatParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getItemCatName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<ItemCatInfo> getList() {
        return list;
    }

    public void setList(ArrayList<ItemCatInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ItemCatViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemCatCode, txtItemCatName;
        private CardView cvItemCatParent;

        public ItemCatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemCatCode = itemView.findViewById(R.id.txtItemCatCode);
            txtItemCatName = itemView.findViewById(R.id.txtItemCatName);

            cvItemCatParent = itemView.findViewById(R.id.cvItemCatParent);

        }
    }
}
