package com.example.sharedprefer_json.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sharedprefer_json.Product;
import com.example.sharedprefer_json.R;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product>listProduct;
    public ProductAdapter(ArrayList<Product> listProduct){this.listProduct=listProduct;}
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_produk,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position){
        final Product listProduct = this.listProduct.get(position);
        holder.tvProduct.setText(this.listProduct.get(position).getProduct());
        holder.tvPrice.setText(""+this.listProduct.get(position).getPrice());

    }
    @Override
    public int getItemCount(){
        return listProduct.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvProduct,tvPrice;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.tvProduct = (TextView) itemView.findViewById(R.id.tvProduk);
            this.tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }
}
