package com.example.newproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity {
  DrawerLayout drawerLayout;
  NavigationView nav;
  Toolbar toolbar;
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
                    case R.id.add_login1:
                        Toast.makeText(home.this, "fffffffffffff", Toast.LENGTH_SHORT).show();

                    case R.id.add_cart:
                        Log.i("MENU_DRAWE", "ADD_cart");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.f_login:
                        Log.i("MENU_DRAWE", "f_login");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.f_cart:
                        Log.i("MENU_DRAWE", "f_cart");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        Log.i("MENU_DRAWE", "logout");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

    }


}