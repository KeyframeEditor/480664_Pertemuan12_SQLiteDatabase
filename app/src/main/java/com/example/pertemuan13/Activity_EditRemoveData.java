package com.example.pertemuan13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class Activity_EditRemoveData extends AppCompatActivity {

    ImageView backImageView;
    Button buttonUpdate;
    Button buttonRemove;
    EditText editTextProductName;
    EditText editTextProductPrice;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_remove_data);

        backImageView = findViewById(R.id.backImageView);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonRemove = findViewById(R.id.buttonRemove);
        editTextProductName = findViewById(R.id.EditTextProductName);
        editTextProductPrice = findViewById(R.id.EditTextProductPrice);

        mDatabaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String selectedProductName = intent.getStringExtra("nama");

        List<Product> products = mDatabaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(selectedProductName)){
                editTextProductName.setText(product1.getName());
                editTextProductPrice.setText(String.valueOf(product1.getPrice()));
                break;
            }
        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProduct(selectedProductName);
                Toast.makeText(Activity_EditRemoveData.this, "Berhasil edit product", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Activity_EditRemoveData.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Activity_EditRemoveData.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct(selectedProductName);
                Toast.makeText(Activity_EditRemoveData.this, "Berhasil menghapus: "+selectedProductName, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Activity_EditRemoveData.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void deleteProduct(String productName){
        List<Product> products = mDatabaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(productName)){
                mDatabaseHelper.deleteProduct(product1);
                break;
            }
        }
    }

    private void updateProduct(String productName){
        List<Product> products = mDatabaseHelper.getAllProduct();
        for (int i=0; i < products.size(); i++){
            Product product1 = products.get(i);
            if (product1.getName().equals(productName)){
                product1.setName(String.valueOf(editTextProductName.getText()));
                product1.setPrice(Integer.valueOf(String.valueOf(editTextProductPrice.getText())));
                mDatabaseHelper.updateProduct(product1);
                break;
            }
        }
    }
}