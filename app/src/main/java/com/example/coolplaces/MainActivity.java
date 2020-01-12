package com.example.coolplaces;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements TextWatcher {
        EditText edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //events
        Button btnLogIn = findViewById(R.id.btnLogIn);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.addTextChangedListener(this);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityMenuPlaceholder.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitySignUp.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }7

    @Override
    public void afterTextChanged(Editable editable) {
        String email = edtEmail.getText().toString();
        boolean valid = DataValidation.isValidEmail(email);
        edtEmail.setError("Enter a valid email!");
    }
}
