package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newproject.methods.DatabaseSqlite;
import com.example.newproject.methods.UserLogin;

import java.util.ArrayList;

public class update_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_login);
        Button add_login_button =findViewById(R.id.u_login);

        DatabaseSqlite obj = new DatabaseSqlite(this);
        EditText u_title = findViewById(R.id.u_title);
        EditText u_email = findViewById(R.id.u_email);
        EditText u_password = findViewById(R.id.u_password);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton history = findViewById(R.id.history);
        Button Generat = findViewById(R.id.u_generate);


;
        Generat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(update_login.this, PasswordGenerator.class);
                startActivity(intent);
            }


        });


        Intent intent = getIntent();
        String id_login = intent.getStringExtra("id_login");
        UserLogin l = obj.Get_login(id_login);
        u_title.setText(l.getTitle());
        u_email.setText(l.getEmail());
        u_password.setText(l.getPassword());
        String p =l.getPassword();
        int id= Integer.parseInt(id_login);

        //obj.Insert_P(id,p);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(update_login.this, history_password.class);
                intent.putExtra("id_history", id_login);
                startActivity(intent);

            }
        });

        add_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title= u_title.getText().toString();
                String email= u_email.getText().toString();
                String password= u_password.getText().toString();
                if (title.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(update_login.this, "Give All The Informations", Toast.LENGTH_SHORT).show();
                }else if (title.length()<4 || email.length()<10 || password.length()<4){
                    Toast.makeText(update_login.this, "(Title|Email|Password) invalid", Toast.LENGTH_SHORT).show();
                }else {
                    obj.Update_Login(id_login,title, email, password);
                    obj.Insert_P(Integer.parseInt(id_login),password);
                    Toast.makeText(update_login.this,"Update Valide", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(update_login.this,home.class);
                    startActivity(intent);}
            }
        });
    }
}