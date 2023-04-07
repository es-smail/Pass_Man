package com.example.newproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.text.input.TextInputService;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproject.methods.DatabaseSqlite;
import com.example.newproject.methods.UserCart;
import com.example.newproject.methods.UserLogin;
import com.example.newproject.methods.item;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class home extends AppCompatActivity {
  DrawerLayout drawerLayout;
  NavigationView nav;
    AlertDialog.Builder alertDialog;


    ActionBarDrawerToggle actionBarDrawerToggle;

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
/////////////////////////////////////// Working In List Vie w
        alertDialog = new AlertDialog.Builder(this);
        ListView list=findViewById(R.id.list);

        DatabaseSqlite obj1= new DatabaseSqlite(this);
        ArrayList <item> l = obj1.getAllItems();
        AddItem a = new AddItem(l);
        list.setAdapter(a);

       // obj1.delete_Login("1");








////////////////////////////////////////////////// Working In Login And Drawer Menu
        drawerLayout = findViewById(R.id.drawer);
        nav = findViewById(R.id.nav);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_login:
                        Intent intent=new Intent(home.this, add_login.class);
                        startActivity(intent);
                        break;
                    case R.id.add_cart:
                        Intent inten=new Intent(home.this,add_carte.class);
                        startActivity(inten);
                        break;
                    case R.id.f_all:
                        ArrayList <item> k = obj1.getAllItems();
                        AddItem a = new AddItem(k);
                        list.setAdapter(a);
                        break;
                    case R.id.f_login:
                        ArrayList<item> F_L = new ArrayList<>();
                        for (item i : l) {
                            if (i instanceof UserLogin) {
                                F_L.add(i);
                            }
                        }
                        AddItem login = new AddItem(F_L);
                        list.setAdapter(login);
                        break;
                    case R.id.f_cart:
                        ArrayList<item> F_C = new ArrayList<>();
                        for (item i : l) {
                            if (i instanceof UserCart) {
                                F_C.add(i);
                            }
                        }
                        AddItem cart = new AddItem(F_C);
                        list.setAdapter(cart);
                        break;
                    case R.id.logout:

                        signOut();


                }
                return true;
            }
        });


    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())
                .signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(home.this, login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sign out failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public class AddItem extends BaseAdapter  {

        ArrayList<item> l = new ArrayList<item>();

        public AddItem(ArrayList<item> v) {

            this.l = v;
        }

        @Override
        public int getCount() {
            return l.size();
        }

        @Override
        public Object getItem(int i) {
            return l.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater ly = getLayoutInflater();

            View view= ly.inflate(R.layout.list_home,null);

            if (l.get(position) instanceof UserLogin) {

                TextView title = view.findViewById(R.id.title);
                TextView email = view.findViewById(R.id.email);
                TextView password = view.findViewById(R.id.password);
                ImageView update_L=view.findViewById(R.id.update);
                ImageView delete_L=view.findViewById(R.id.delete);


                title.setText(((UserLogin) l.get(position)).getTitle());
                email.setText(((UserLogin) l.get(position)).getEmail());
                password.setText(((UserLogin) l.get(position)).getPassword());
                String id= String.valueOf(((UserLogin) l.get(position)).getId());

                view.findViewById( R.id.cartpart ).setVisibility(View.GONE);
                update_L.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(home.this,update_login.class);
                        intent.putExtra("id_login", id);
                        startActivity(intent);
                    }
                });
                delete_L.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.setMessage("Are You Sure You Want To Delete This")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DatabaseSqlite obj = new DatabaseSqlite(home.this);
                                        obj.delete_Login(id);
                                        Toast.makeText(home.this, "Delet Valid", Toast.LENGTH_SHORT).show();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No",null).show();
                    }
                });

            } else if (l.get(position) instanceof UserCart) {
                TextView name = view.findViewById(R.id.name);
                TextView type = view.findViewById(R.id.type);
                TextView number = view.findViewById(R.id.number);
                TextView cvc = view.findViewById(R.id.cvc);
                TextView date = view.findViewById(R.id.date);
                TextView pin = view.findViewById(R.id.pin);
                TextView expiry_date = view.findViewById(R.id.expiry);
                ImageView update_C=view.findViewById(R.id.update1);
                ImageView delete_C=view.findViewById(R.id.delete1);


                name.setText( ((UserCart) l.get(position)).getName());
                type.setText( ((UserCart) l.get(position)).getType());
                number.setText( ((UserCart) l.get(position)).getNumber()+"");
                cvc.setText( ((UserCart) l.get(position)).getCvc()+"");
                date.setText( ((UserCart) l.get(position)).getDate()+"");
                pin.setText( ((UserCart) l.get(position)).getPin()+"");
                expiry_date.setText( ((UserCart) l.get(position)).getExpiry_date()+"");

                String idC= String.valueOf(((UserCart) l.get(position)).getId());

                view.findViewById( R.id.loginpart ).setVisibility(View.GONE);
                update_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(home.this, update_carte.class);
                        intent.putExtra("id_cart", idC);
                        startActivity(intent);
                    }
                });
                delete_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.setMessage("Are You Sure You Want To Delete This")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DatabaseSqlite obj = new DatabaseSqlite(home.this);
                                        obj.delete_Cart(idC);
                                        Toast.makeText(home.this, "Delet Valid", Toast.LENGTH_SHORT).show();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No",null).show();
                    }
                });
            }

            return view;
        }


    }


}



