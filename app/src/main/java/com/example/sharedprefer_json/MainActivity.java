package com.example.sharedprefer_json;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etProduct, etPrice;
    ArrayList<Product>listProduct;
    SharedPreferences sharedPreferences;
    Button btnsimpan, btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("pref_product", MODE_PRIVATE);
        sharedPreferences.contains("listproduct");
        listProduct = new ArrayList<Product>();
        //mengambil shared pref yang sudah ada
        if (sharedPreferences.contains("listproduct")){
            Gson getgson = new Gson();
            String getjsonText = sharedPreferences.getString("listproduct",null);
            Product[] product = getgson.fromJson(getjsonText,Product[].class);
            for (Product product1 : product){
                listProduct.add(product1);
            }
        }
        etProduct=findViewById(R.id.etproduct);
        etPrice=findViewById(R.id.etprice);
        btnsimpan=findViewById(R.id.btnSave);
        btnDisplay=findViewById(R.id.btnDisplay);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product produk = new Product(etProduct.getText().toString(),
                        Integer.parseInt(etPrice.getText().toString()));
                listProduct.add(produk);
                Gson gson = new Gson();
                String jsonText = gson.toJson(listProduct);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("listproduct",jsonText);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Simpan ke sharedpreferences",Toast.LENGTH_LONG).show();
            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityDisplay.class);
                startActivity(intent);
            }
        });
    }
}