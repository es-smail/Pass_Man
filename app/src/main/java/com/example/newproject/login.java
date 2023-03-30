package com.example.newproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final ImageView signInBtn =findViewById(R.id.sinIn);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if (googleSignInAccount !=null){
            startActivity(new Intent(login.this, home.class));
            finish();
        }
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                handeleSignInTask(task);
            }
        });
        signInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent siginInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(siginInIntent);
            }
        });
    }
    private void handeleSignInTask(Task<GoogleSignInAccount> task ){

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            final String getFullname = account.getDisplayName();
            final String getEmail = account.getEmail();
            final Uri getPhoto = account.getPhotoUrl();

            startActivity(new Intent(login.this, home.class));
            finish();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}