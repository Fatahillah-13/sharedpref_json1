package com.example.sharedprefer_json.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sharedprefer_json.Product;
import com.example.sharedprefer_json.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product>listProduct;
    Context appContext;
    SharedPreferences sharedPreferences;
    public ProductAdapter(Context appContext, ArrayList<Product> listProduct){
        this.listProduct=listProduct;
        this.appContext=appContext;
    }
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
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(holder.getPosition());
            }
        });

    }
    @Override
    public int getItemCount(){
        return listProduct.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvProduct,tvPrice;
        ImageView btnDelete;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.tvProduct = (TextView) itemView.findViewById(R.id.tvProduk);
            this.tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            this.btnDelete = (ImageView) itemView.findViewById(R.id.btnDelete);
        }
    }

    public void removeAt(int position){
        sharedPreferences = appContext.getSharedPreferences("pref_product",Context.MODE_PRIVATE);
        listProduct.remove(position);
        if (sharedPreferences.contains("listproduct")){
            Gson gson = new Gson();
            String jsonText = gson.toJson(listProduct);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("listproduct",jsonText);
            editor.apply();
            Toast.makeText(appContext.getApplicationContext(),"Hapus Berhasil",Toast.LENGTH_LONG).show();
        }
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listProduct.size());
    }
}
