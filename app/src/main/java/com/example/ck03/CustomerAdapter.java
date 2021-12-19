package com.example.ck03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    List<SanPham> sanPhams;
    OnClickListener listener;

    public CustomerAdapter(List<SanPham> sanPhams, OnClickListener listener) {
        this.sanPhams = sanPhams;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sp = sanPhams.get(position);
        holder.sanPham = sp;
        holder.id.setText(sp.getId());
        holder.name.setText(sp.getName());
        holder.price.setText(sp.getPrice()+"");
        holder.soluong.setText(sp.getSoluong()+"");
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView id, name, price, soluong;
       Button btnSua, btnXoa;
       SanPham sanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            soluong = itemView.findViewById(R.id.item_soluong);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.clickItem(sanPham);
                }
            });

            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.clickItemXoa(sanPham);
                }
            });

            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.clickItemSua(sanPham);
                }
            });

        }
    }
}
