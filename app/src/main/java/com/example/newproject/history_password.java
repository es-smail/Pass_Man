package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproject.methods.DatabaseSqlite;
import com.example.newproject.methods.Password;
import com.example.newproject.methods.item;

import java.util.ArrayList;

public class history_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_password);


        Intent intent = getIntent();

        String id_history = intent.getStringExtra("id_history");
        DatabaseSqlite obj1= new DatabaseSqlite(this);
        ListView listView =findViewById(R.id.list1);
        ArrayList <Password> l = obj1.getPasswordHistory(id_history);
        AddI a = new AddI(l);
        listView.setAdapter(a);




    }
    public class AddI extends BaseAdapter {

        private ArrayList<Password> l;

        public AddI(ArrayList<Password> v) {
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater ly = LayoutInflater.from(parent.getContext());

            View view = ly.inflate(R.layout.history_list, parent, false);
            TextView passwordView = view.findViewById(R.id.pass_H);
            passwordView.setText(l.get(position).getPassword());

            return view;
        }
    }
}
