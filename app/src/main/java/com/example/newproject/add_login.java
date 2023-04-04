package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newproject.methods.DatabaseSqlite;

public class add_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_login);

        Button add_login_button =findViewById(R.id.add_login1);

        EditText add_title = findViewById(R.id.add_title);
        EditText add_email = findViewById(R.id.add_email);
        EditText add_password = findViewById(R.id.add_password);

        DatabaseSqlite obj = new DatabaseSqlite(this);


        add_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title= add_title.getText().toString();
                String email= add_email.getText().toString();
                String password= add_password.getText().toString();
                if (title.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(add_login.this, "Give All The Informations", Toast.LENGTH_SHORT).show();
                }else if (title.length()<4 || email.length()<10 || password.length()<4){
                    Toast.makeText(add_login.this, "(Title|Email|Password) invalid", Toast.LENGTH_SHORT).show();
                }else {
                    String n=  obj.Insert_login(title, email, password);
                    Toast.makeText(add_login.this,n, Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(add_login.this,home.class);
                    startActivity(intent);}
            }
        });

    }
}