package com.example.titemag;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText edtEmail,edtPassword,edtName,edtEnrollment;
    TextView txt;
    Button btn_register;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        edtEmail=findViewById(R.id.register_email);
        edtPassword=findViewById(R.id.register_password);
        edtName=findViewById(R.id.register_name);
        edtEnrollment=findViewById(R.id.register_enrollment);
        btn_register=findViewById(R.id.register_button);
        txt=findViewById(R.id.register_login_button);


        txt.setOnClickListener(view -> {
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        btn_register.setOnClickListener(view -> {
            AlertDialog.Builder builder= new AlertDialog.Builder(RegisterActivity.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            String email,password,name,enrollment;
            email=String.valueOf(edtEmail.getText());
            password=String.valueOf(edtPassword.getText());
            name=String.valueOf(edtName.getText());
            enrollment=String.valueOf(edtEnrollment.getText());

            if(TextUtils.isEmpty(name) && TextUtils.isEmpty(enrollment)){
                Toast.makeText(RegisterActivity.this, "Please Enter Valid Details!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                return;
            }

            if(TextUtils.isEmpty(email)){
                Toast.makeText(RegisterActivity.this, "Please Enter Valid Details!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(RegisterActivity.this, "Please Enter Valid Details!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}