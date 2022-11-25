package com.example.pertemuan13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_AddData extends AppCompatActivity {

    EditText EditTextProductName;
    EditText EditTextProductPrice;
    Button buttonAddData;
    ImageView backImageView;

    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        EditTextProductName = findViewById(R.id.EditTextProductName);
        EditTextProductPrice = findViewById(R.id.EditTextProductPrice);
        buttonAddData = findViewById(R.id.buttonAddData);
        backImageView = findViewById(R.id.backImageView);

        mDatabaseHelper = new DatabaseHelper(this);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_AddData.this,MainActivity.class);
                startActivity(intent);
            }
        });

        buttonAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                EditTextProductName.setText("");
                EditTextProductPrice.setText("");
            }
        });
    }

    public void insertData(){
        Product product = new Product();
        product.setName(EditTextProductName.getText().toString());
        product.setPrice(Integer.valueOf(EditTextProductPrice.getText().toString()));

        mDatabaseHelper.insertRecord(product);
        Toast.makeText(Activity_AddData.this, "success add data", Toast.LENGTH_SHORT).show();
    }
}