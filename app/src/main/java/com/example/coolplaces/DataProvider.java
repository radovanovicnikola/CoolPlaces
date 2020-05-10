package com.example.coolplaces;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class DataProvider {
    private static DataProvider instance = null;
    private FirebaseDatabase database;
    private DatabaseReference dbrMessage;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private DataProvider()
    {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        dbrMessage = database.getReference("test");
    }

    public static DataProvider getInstance(){
        if(instance != null)
        {
            return instance;
        }
        instance = new DataProvider();
        return instance;
    }
    public void addSimpleMessage(SimpleMessageData message){
        currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();
        dbrMessage.child(uid).child("test4").setValue("test 4!");
    }
    public String getUid()
    {
        return mAuth.getUid();
    }
    public Task writeObject(String firebasePath,Object obj)
    {
        DatabaseReference myRef = database.getReference(firebasePath);
        return myRef.setValue(obj);
    }

    public Task writeObject(String firebasePath, Object obj, OnSuccessListener<Void> onSuccessListener)
    {
        DatabaseReference myRef = database.getReference(firebasePath);
        Task task = myRef.setValue(obj).addOnSuccessListener(onSuccessListener);
        return task;
    }

    public Task writeObject(String firebasePath, Object obj, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener)
    {
        DatabaseReference myRef = database.getReference(firebasePath);
        Task task = myRef.setValue(obj).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
        return task;
    }

    public Object watchForDataChanges(String firebasePath,ValueEventListener valueEventListener){
        DatabaseReference myRef = database.getReference(firebasePath);
        ValueEventListener result = myRef.addValueEventListener(valueEventListener);
        return result;
    }
}
