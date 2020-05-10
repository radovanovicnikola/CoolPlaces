package com.example.coolplaces;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignUp extends AppCompatActivity {
    ImageView imgbProfile;
    EditText edtUserName;
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtPassword;
    EditText edtConfirmPass;
    EditText edtPhone;
    EditText edtEmail;
    FirebaseAuth mAuth;
    AlertDialog.Builder builder;
    private UserData userData;
    private ErrorDialog errorDialog;
    private ProgressDialog PD;
    private boolean profilePictureSet;

    private static final int REQUEST_CAMERA = 1888;
    private static final int SELECT_FILE = 1889;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmail = findViewById(R.id.edtEmail);
        imgbProfile = findViewById(R.id.imgbProfile);
        edtUserName = findViewById(R.id.edtUserName);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPassword);
        edtPhone = findViewById(R.id.edtPhone);
        initTextChangeEvents();

        //Progress dialog and error dialog init
        PD = new ProgressDialog(ActivitySignUp.this);
        PD.setMessage("Creating new account...");
        PD.setTitle("Sign up");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        errorDialog=new ErrorDialog(this);

        profilePictureSet =false;
        imgbProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        Button btnSignIn = findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isValidSignInData())
                    return;

                PD.show();

                mAuth = FirebaseAuth.getInstance();
                userData = getUserData();
                final String email = edtEmail.getText().toString();
                final String pass = edtPassword.getText().toString();
                //register user in Firebase system and get UID token
                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(ActivitySignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            uploadUserData().addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    errorDialog.show("Error","Uploading data failed. Error: "+e.getMessage());
                                    PD.dismiss();
                                }
                            });
                            PD.dismiss();
                            launchHomeActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            errorDialog.show("Error","Creating new user failed. Error: "+ task.getException().getMessage());
                            PD.dismiss();
                        }
                    }
                });
            }
        });

    }

    private void selectImage(){
        final CharSequence[] items = { "Take Photo", "Choose from gallery",
                "Cancel" };

        builder = new AlertDialog.Builder(ActivitySignUp.this);
        builder.setTitle("Change photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgbProfile.setImageBitmap(photo);
                profilePictureSet = true;
            }
            else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();


                //Bitmap bm = null;
                //String tempPath = UI_helper.getPath(selectedImageUri, ActivitySignUp.this);
                //BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                //bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                //imgbProfile.setImageBitmap(bm);

                imgbProfile.setImageURI(selectedImageUri);
                profilePictureSet = true;
            }
        }
    }

    protected boolean isValidSignInData(){
        boolean valid = true;

        //check if all fields are filled
        valid = valid && !UI_helper.emptyEdtTextSetError(edtEmail);
        valid =valid && !UI_helper.emptyEdtTextSetError(edtUserName);
        valid =valid && !UI_helper.emptyEdtTextSetError(edtFirstName);
        valid =valid && !UI_helper.emptyEdtTextSetError(edtLastName);
        valid =valid && !UI_helper.emptyEdtTextSetError(edtPassword);
        valid =valid && !UI_helper.emptyEdtTextSetError(edtConfirmPass);
        valid =valid && !UI_helper.emptyEdtTextSetError(edtPhone);
        if(!valid)
            return false;

        //check if fields have error
        valid = valid && !UI_helper.edtTextHasError(edtEmail);
        valid =valid && !UI_helper.edtTextHasError(edtUserName);
        valid =valid && !UI_helper.edtTextHasError(edtFirstName);
        valid =valid && !UI_helper.edtTextHasError(edtLastName);
        valid =valid && !UI_helper.edtTextHasError(edtPassword);
        valid =valid && !UI_helper.edtTextHasError(edtConfirmPass);
        valid =valid && !UI_helper.edtTextHasError(edtPhone);
        if(!valid)
            return false;

        //profile picture validation
       if (!profilePictureSet)
       {
           errorDialog.show("Missing picture","Profile picture is required. Click on the image button to select image from gallery or camera.");
           return false;
       }

        return valid;
    }

    protected UserData getUserData(){
        String userName = edtUserName.getText().toString();
        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        Bitmap userPicture = ((BitmapDrawable)imgbProfile.getDrawable()).getBitmap();
        UserData result = new UserData(userPicture,userName,firstName,lastName,phone,email);

        return result;
    }

    protected Task uploadUserData(){
       return DataProvider.getInstance().writeObject("users/"+ DataProvider.getInstance().getUid(),userData);
    }

    private void launchHomeActivity(){
        Intent intent = new Intent(ActivitySignUp.this,ActivityMenuPlaceholder.class);
        ActivitySignUp.this.startActivity(intent);
    }

    protected  void initTextChangeEvents(){
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = edtEmail.getText().toString();
                boolean valid = DataValidation.isValidEmail(email);
                if(!valid) {
                    edtEmail.setError("Enter a valid email!");
                }
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = edtPassword.getText().toString();
                boolean valid = DataValidation.isValidPass(pass);
                if(!valid) {
                    edtPassword.setError("Minimum 8 characters.");
                }
            }
        });

        edtConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass = edtPassword.getText().toString();
                String confPass = edtConfirmPass.getText().toString();
                boolean valid = confPass.equals(pass);
                if(!valid) {
                    edtConfirmPass.setError("Passwords must match.");
                }
            }
        });
    }
}
