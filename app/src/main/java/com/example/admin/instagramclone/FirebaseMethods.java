package com.example.admin.instagramclone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userId;
    private Context mContext;

    public FirebaseMethods(Context context){
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        mContext=context;
        if(mAuth.getCurrentUser()!=null){
            userId=mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot){
        User user=new User();
        for(DataSnapshot dataSnapshot1:dataSnapshot.child(userId).getChildren()){
            user.setUsername(dataSnapshot1.getValue(User.class).getUsername());
            if(StringManipulation.expandUsername(user.getUsername()).equals(username)){
                return true;
            }
        }
        return false;
    }

    public void registerNewUser(final String email,final String password,final String username) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userId=mAuth.getCurrentUser().getUid();
                        }else {
                            Toast.makeText(mContext, "Failed to Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void addNewUser(String email,String username,String description,String website,String profilephoto){
        User user=new User(userId,StringManipulation.compressUsername(username),88001745,email);
        databaseReference.child(mContext.getString(R.string.dbname_users))
                .child(userId)
                .setValue(user);
        UserAccountSettings userAccountSettings=new UserAccountSettings(
                description,username,0,0,0,profilephoto,username,website);
        databaseReference.child(mContext.getString(R.string.dbname_user_settings))
                .child(userId)
                .setValue(userAccountSettings);
    }
}
