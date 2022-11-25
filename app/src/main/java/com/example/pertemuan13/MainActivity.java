package com.example.pertemuan13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton actionButtonAdd;
    ListView listView;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButtonAdd = findViewById(R.id.actionButtonAdd);
        listView = findViewById(R.id.listView);

        mDatabaseHelper = new DatabaseHelper(this);

        getAllProduct();

        actionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_AddData.class);
                startActivity(intent);
                finish();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedProductName = adapterView.getItemAtPosition(i).toString();
                deleteProduct(selectedProductName);
                getAllProduct();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedProductName = adapterView.getItemAtPosition(i).toString();
                Intent intent = new Intent(MainActivity.this, Activity_EditRemoveData.class);
                intent.putExtra("nama",selectedProductName);
                startActivity(intent);
                finish();
            }
        });
    }


    public void showAllProduct(){
        List<Product> products = mDatabaseHelper.getAllProduct();
        String result = "";
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
                result = result + product1.getName()+"@"+product1.getPrice()+", ";
        }
    }

    private void getAllProduct(){
        List<Product> products = mDatabaseHelper.getAllProduct();
        List<String> productNames = new ArrayList<>();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            productNames.add(String.valueOf(product1.getName()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,productNames);
        listView.setAdapter(adapter);
    }

    private void deleteProduct(String productName){
        List<Product> products = mDatabaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(productName)){
                mDatabaseHelper.deleteProduct(product1);
                Toast.makeText(MainActivity.this, "Berhasil Delete: "+product1.getName(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void getProductPrice(String productName){
        List<Product> products = mDatabaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(productName)){
                mDatabaseHelper.deleteProduct(product1);
                Toast.makeText(MainActivity.this, "Berhasil Delete: "+product1.getName(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}