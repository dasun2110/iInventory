package ithub.iinventory;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private TextView mDisplayName;
    private TextView mEmail;
    private TextView mPassword;

    private Button mCreateButton;

    private DatabaseReference mDatabase;

    private android.support.v7.widget.Toolbar nToolBar;



    private ProgressDialog mRegProg;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegProg = new ProgressDialog(this  );


        mAuth = FirebaseAuth.getInstance();

        nToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(nToolBar);

        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mDisplayName =(TextView)findViewById(R.id.itemID);
        mEmail=(TextView)findViewById(R.id.newItemName);
        mPassword=(TextView)findViewById(R.id.newItemQty);
        mCreateButton = (Button)findViewById(R.id.regCreateButton);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String displayName = mDisplayName.getText().toString();
                String email =mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(!TextUtils.isEmpty(displayName)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){
                    mRegProg.setTitle("Register User");
                    mRegProg.setMessage("Please wait while we create your account");
                    mRegProg.setCanceledOnTouchOutside(false);
                    mRegProg.show();
                    registerUser(displayName,email,password);


                }


            }
        });


    }

    private void registerUser(final String displayName, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uID = currentUser.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uID);
                    HashMap<String , String> userMap = new HashMap<>();
                    userMap.put("name",displayName);
                    userMap.put("status","Hello.. User");
                    userMap.put("image","default");
                    userMap.put("thumb_image","default");

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mRegProg.dismiss();

                                Intent mainIn = new Intent(RegisterActivity.this,MainActivity.class );

                                startActivity(mainIn);
                                finish();
                            }
                        }
                    });






                }else{
                    mRegProg.hide();
                    Toast.makeText(RegisterActivity.this, "Try Again Later !!! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
