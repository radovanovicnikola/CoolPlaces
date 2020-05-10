package com.example.coolplaces;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity{
        EditText edtEmail;
        EditText edtPass;
        private FirebaseUser curentUser;
        private ErrorDialog errorDialog;
        private ProgressDialog PD;
        private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Progress dialog and error dialog init
        PD = new ProgressDialog(MainActivity.this);
        PD.setMessage("Trying to log in...");
        PD.setTitle("Log in");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        errorDialog=new ErrorDialog(this);

        //events
        Button btnLogIn = findViewById(R.id.btnLogIn);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = edtEmail.getText().toString();
                boolean valid = DataValidation.isValidEmail(email);
                if(!valid) {
                    edtEmail.setError("Enter a valid email!");
                }
            }
        });
        edtPass = findViewById(R.id.edtPassword);
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pass = edtPass.getText().toString();
                boolean valid = DataValidation.isValidPass(pass);
                if(!valid) {
                    edtPass.setError("Minimum 8 characters.");
                }
            }
        });
        DataProvider.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String email = edtEmail.getText().toString();
                EditText edtPass = findViewById(R.id.edtPassword);
                String pass = edtPass.getText().toString();

                boolean validData=true;
                if(!DataValidation.isValidEmail(email))
                {
                    edtEmail.setError("You must enter valid email.");
                    validData=false;
                }
                //Handle short or empty password
                if(!DataValidation.isValidPass(pass)) {
                    edtPass.setError("Minimum 8 characters.");
                    validData=false;
                }
                //continue only if user data is valid
                if(!validData){
                    return; //false;
                }
                //Waiting to log in...
                PD.show();

                logIn();
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

    public void logIn() {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        curentUser=null;
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();

        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            MainActivity.this.curentUser = mAuth.getCurrentUser();
                            //test
                            downloadUserInfo(MainActivity.this.curentUser.getUid());
                        } else {
                            //Display error dialog
                            errorDialog.show("Error",task.getException().getMessage());
                            PD.dismiss();
                        }
                    }
                });
    }


    private void downloadUserInfo(final String userID){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("users").child(userID).child("userName");

        //test
        String uid = DataProvider.getInstance().getUid();
        UserData userData2 = new UserData(BitmapFactory.decodeResource(getResources(),R.drawable.img_profile_example),"tamaraJo","Tamara","Jovanovic","066321231","tamarojo@gmail.com");
        DataProvider.getInstance().writeObject("users/" + uid,userData2);

        DataProvider.getInstance().watchForDataChanges("users/" + uid, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userData = dataSnapshot.getValue(UserData.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                errorDialog.show("Error","Login failed. Error: "+databaseError.getMessage());
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String userName=dataSnapshot.getValue(String.class);
                //launch main activity
                PD.dismiss();
                launchHomeActivity();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                errorDialog.show("Error",error.getMessage().toString());
                PD.dismiss();
            }
        });
    }

    private void launchHomeActivity(){
        Intent intent = new Intent(MainActivity.this,ActivityMenuPlaceholder.class);
        MainActivity.this.startActivity(intent);
    }
}
