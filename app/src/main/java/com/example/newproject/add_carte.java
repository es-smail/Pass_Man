package com.example.newproject;

import static com.example.newproject.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newproject.methods.DatabaseSqlite;

public class add_carte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.add_carte);
        Button add_cart_button =findViewById(R.id.add_cart1);

        EditText add_name = findViewById(R.id.add_name);
        EditText add_type = findViewById(R.id.add_type);
        EditText add_number = findViewById(R.id.add_number);
        EditText add_cvc = findViewById(R.id.add_cvc);
        EditText add_date = findViewById(R.id.add_date);
        EditText add_pin = findViewById(R.id.add_pin);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText add_expiry= findViewById( R.id.add_expiry);
        DatabaseSqlite obj = new DatabaseSqlite(this);


        add_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = add_name.getText().toString();
                String type = add_type.getText().toString();
                String number = add_number.getText().toString();
                String cvc = add_cvc.getText().toString();
                String date = add_date.getText().toString();
                String pin = add_pin.getText().toString();
                String expiry_date = add_expiry.getText().toString();
                if (name.isEmpty() || type.isEmpty() || number.isEmpty()||cvc.isEmpty() || date.isEmpty() || pin.isEmpty() ||expiry_date.isEmpty()){

                    Toast.makeText(add_carte.this, "Give All The Informations", Toast.LENGTH_SHORT).show();

                }else if (name.length()<3 || type.length()<1|| number.length()<8 ||cvc.length()<3 || date.length()<4 || pin.length()<4 || expiry_date.length()<4){

                    Toast.makeText(add_carte.this, "(Insert Of Iformation ) invalid", Toast.LENGTH_SHORT).show();

                }else {
                    int n= Integer.parseInt(number);
                    int c= Integer.parseInt(cvc);
                    int d= Integer.parseInt(date);
                    int p= Integer.parseInt(pin);
                    int expiry=Integer.parseInt(expiry_date);

                    String S= obj.Insert_cart(name,type,n,c,d,p,expiry);
                    Toast.makeText(add_carte.this,S, Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(add_carte.this,home.class);
                    startActivity(intent);
                }
            }
        });
    }
}