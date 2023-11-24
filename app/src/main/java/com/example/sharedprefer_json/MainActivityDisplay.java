package com.example.sharedprefer_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.sharedprefer_json.adapter.ProductAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MainActivityDisplay extends AppCompatActivity {

    Button btnKembali;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        ArrayList<Product>listproduct = new ArrayList<>();

        sharedPreferences = getSharedPreferences("pref_product",MODE_PRIVATE);
        if (sharedPreferences.contains("listproduct")){
            sharedPreferences = getSharedPreferences("pref_product", MODE_PRIVATE);
            Gson gson = new Gson();
            String jsonText = sharedPreferences.getString("listproduct",null);
            Product[] product = gson.fromJson(jsonText,Product[].class);
            for (Product product1 : product){
                listproduct.add(product1);
            }
            Log.i("Info pref", ""+listproduct.toString());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProductAdapter adapter = new ProductAdapter(listproduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);

        btnKembali = findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDisplay.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}