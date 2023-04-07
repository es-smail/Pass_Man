package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newproject.models.LowerCaseGenerator;
import com.example.newproject.models.NumericGenerator;
import com.example.newproject.models.SpecialCharGenerator;
import com.example.newproject.models.UpperCaseGenerator;
import com.example.newproject.models.LowerCaseGenerator;
import com.example.newproject.models.NumericGenerator;
import com.example.newproject.models.SpecialCharGenerator;
import com.example.newproject.models.UpperCaseGenerator;

public class PasswordGenerator extends AppCompatActivity {
    private EditText editPasswordSize;
    private TextView textPasswordGenerated,textErrorMessage;
    private CheckBox checkLower, checkUpper,checkSpecialChar, checkNumeric;
    private Button btnGenerate, btnCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_generator);

        initViews();

        clickListeners();
    }

    private void clickListeners() {
        btnGenerate.setOnClickListener(view -> {
            int passwordSize = Integer.parseInt(editPasswordSize.getText().toString());

            textErrorMessage.setText("");

            if(passwordSize<8){
                textErrorMessage.setText("Password Size must be greater than 8");
                return;
            }

            com.example.newproject.models.PasswordGenerator.clear();
            if(checkLower.isChecked()) com.example.newproject.models.PasswordGenerator.add(new LowerCaseGenerator());
            if(checkNumeric.isChecked()) com.example.newproject.models.PasswordGenerator.add(new NumericGenerator());
            if(checkUpper.isChecked()) com.example.newproject.models.PasswordGenerator.add(new UpperCaseGenerator());
            if(checkSpecialChar.isChecked()) com.example.newproject.models.PasswordGenerator.add(new SpecialCharGenerator());


            if(com.example.newproject.models.PasswordGenerator.isEmpty()){
                textErrorMessage.setText("Please select at least one password content type");
                return;
            }

            String passwrd = com.example.newproject.models.PasswordGenerator.generatePassword(passwordSize);
            textPasswordGenerated.setText(passwrd);

        });

        btnCopy.setOnClickListener(view ->{
            ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(ClipData.newPlainText("password",textPasswordGenerated.getText().toString()));
            Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {
        editPasswordSize = findViewById(R.id.edit_pwd_size);
        textPasswordGenerated = findViewById(R.id.text_password_result);
        textErrorMessage = findViewById(R.id.text_error);
        checkLower = findViewById(R.id.check_lower);
        checkUpper = findViewById(R.id.check_upper);
        checkSpecialChar = findViewById(R.id.check_special_char);
        checkNumeric = findViewById(R.id.check_numeric);
        btnGenerate = findViewById(R.id.btn_generate);
        btnCopy = findViewById(R.id.btn_copy);
    }
}