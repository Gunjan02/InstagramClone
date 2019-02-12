package com.example.admin.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private Context mContext;
    private ProgressBar mProgress;
    private EditText email,password;
    private TextView pleaseWait;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mProgress=(ProgressBar)findViewById(R.id.login_progressbar);
        pleaseWait=findViewById(R.id.please_wait);
        email=findViewById(R.id.email_input);
        password=findViewById(R.id.input_password);
        mContext=LoginActivity.this;
        mProgress.setVisibility(View.GONE);
        pleaseWait.setVisibility(View.GONE);

        setupFirebase();
        init();

    }

    private boolean isStringNull(String string){
        if(string.equals("")){
            return  true;
        }else{
            return false;
        }
    }
    private void init(){
        Button loginBtn=findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail=email.getText().toString();
                String inputPAssword=password.getText().toString();
                if(isStringNull(inputEmail) && isStringNull(inputPAssword)){
                    Toast.makeText(mContext, "Please fill the details!", Toast.LENGTH_SHORT).show();
                }else{
                    mProgress.setVisibility(View.VISIBLE);
                    pleaseWait.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(inputEmail, inputPAssword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(mContext, "Successfully logged in: "+user, Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }
            }
        });

        TextView linksignup=(TextView)findViewById(R.id.signup_link);
        linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });
        if(mAuth.getCurrentUser()!=null){
            Intent login=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(login);
            finish();
        }else{

        }
    }
    private void setupFirebase(){

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();

                if(user!=null){
                    //Log.d(TAG, "onAuthStateChanged: signedIn()"+user.getUid());
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
