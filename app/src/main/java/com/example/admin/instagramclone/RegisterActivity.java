package com.example.admin.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private Context mContext;
    String mEmail,mUsername,mPassword;
    private ProgressBar mProgress;
    private EditText email,password,username;
    private String append;
    private TextView pleaseWait;
    private Button regBtn;
    FirebaseMethods firebaseMethods;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        mContext=RegisterActivity.this;
        firebaseMethods=new FirebaseMethods(mContext);
        mProgress=findViewById(R.id.register_progressbar);
        email=findViewById(R.id.register_email_input);
        password=findViewById(R.id.register_password_input);
        username=findViewById(R.id.register_username_input);
        pleaseWait=findViewById(R.id.reg_pleasewait);
        regBtn=findViewById(R.id.btn_register);
        mProgress.setVisibility(View.GONE);
        pleaseWait.setVisibility(View.GONE);

        setupFirebase();
        init();
    }

    private void init(){
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmail=email.getText().toString();
                mUsername=username.getText().toString();
                mPassword=password.getText().toString();

                if(check(mEmail,mUsername,mPassword)){
                    mProgress.setVisibility(View.VISIBLE);
                    pleaseWait.setVisibility(View.VISIBLE);
                    firebaseMethods.registerNewUser(mEmail,mPassword,mUsername);
                }
            }
        });

        TextView linksignIn=findViewById(R.id.signin_link);
        linksignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(login);
            }
        });
    }

    private boolean check(String mEmail, String mUsername, String mPassword){
        if(isStringNull(mEmail) && isStringNull(mUsername) && isStringNull(mPassword)){
            Toast.makeText(mContext, "All fields need to filled!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean isStringNull(String string){
        if(string.equals("")){
            return  true;
        }else{
            return false;
        }
    }

    private void setupFirebase(){

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();

                if(user!=null){
                    //Log.d(TAG, "onAuthStateChanged: signedIn()"+user.getUid());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(firebaseMethods.checkIfUsernameExists(mUsername,dataSnapshot)){
                                append=databaseReference.push().getKey().substring(3,10);
                            }
                            mUsername=mUsername+append;
                            firebaseMethods.addNewUser(mEmail,mUsername,"","","");
                            Toast.makeText(mContext, "SignUp Successful! Sending Email Verification..", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }else{
                    //Log.d(TAG,"onAuthStateChanged: SignedOut()");
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
