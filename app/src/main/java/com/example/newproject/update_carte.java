package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newproject.methods.DatabaseSqlite;
import com.example.newproject.methods.UserCart;

public class update_carte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_carte);
        Intent intent = getIntent();
        //call the id of the Object that we want to Update
        String id_cart = intent.getStringExtra("id_cart");
        DatabaseSqlite obj = new DatabaseSqlite(this);

        Button u_cart_button =findViewById(R.id.u_cart);
        EditText u_name = findViewById(R.id.u_name);
        EditText u_type = findViewById(R.id.u_type);
        EditText u_number = findViewById(R.id.u_number);
        EditText u_cvc = findViewById(R.id.u_cvc);
        EditText u_date = findViewById(R.id.u_date);
        EditText u_pin = findViewById(R.id.u_pin);
        EditText add_expiry= findViewById( R.id.u_expiry);
//get the information of the object
       UserCart c = obj.Get_Cart(id_cart);

       u_name.setText(c.getName());
        u_type.setText(c.getType());
        u_number.setText(c.getNumber()+"");
        u_cvc.setText(c.getCvc()+"");
        u_date.setText(c.getDate()+"");
        u_pin.setText(c.getPin()+"");
        add_expiry.setText(c.getExpiry_date()+"");


        u_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = u_name.getText().toString();
                String type = u_type.getText().toString();
                String number = u_number.getText().toString();
                String cvc = u_cvc.getText().toString();
                String date = u_date.getText().toString();
                String pin = u_pin.getText().toString();
                String expiry_date = add_expiry.getText().toString();
                if (name.isEmpty() || type.isEmpty() || number.isEmpty()||cvc.isEmpty() || date.isEmpty() || pin.isEmpty() ||expiry_date.isEmpty()){

                    Toast.makeText(update_carte.this, "Give All The Informations", Toast.LENGTH_SHORT).show();

                }else if (name.length()<3 || type.length()<1|| number.length()<8 ||cvc.length()<3 || date.length()<4 || pin.length()<4 || expiry_date.length()<4){

                    Toast.makeText(update_carte.this, "(Title|Email|Password) invalid", Toast.LENGTH_SHORT).show();

                }else {
                    int n= Integer.parseInt(number);
                    int c= Integer.parseInt(cvc);
                    int d= Integer.parseInt(date);
                    int p= Integer.parseInt(pin);
                    int expiry=Integer.parseInt(expiry_date);

                     obj.Update_Cart(id_cart,name,type,n,c,d,p,expiry);
                    Toast.makeText(update_carte.this,"Update Cart Valide", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(update_carte.this,home.class);
                    startActivity(intent);
                }
            }
        });
    }
}