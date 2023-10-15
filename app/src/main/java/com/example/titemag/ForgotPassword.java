package com.example.titemag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    TextInputEditText email;
    TextView back;
    String emailId;
    Button sendEmail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email=findViewById(R.id.forgot_email);
        back=findViewById(R.id.forgot_text_view);
        sendEmail=findViewById(R.id.email_send_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mAuth=FirebaseAuth.getInstance();
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private void validateData() {
        emailId=String.valueOf(email.getText());
        if(emailId.isEmpty()){
            email.setError("Required");
        }
        else{
            AlertDialog.Builder builder= new AlertDialog.Builder(ForgotPassword.this);
            builder.setCancelable(false);
            builder.setView(R.layout.pleasewait_layout);
            AlertDialog dialog = builder.create();
            dialog.show();
            forgetPassword(dialog);
        }
    }

    private void forgetPassword(AlertDialog dialog) {
        mAuth.sendPasswordResetEmail(emailId)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(ForgotPassword.this, "Password reset link has been sent to your Email!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                            finish();
                        }
                        else{
                            dialog.dismiss();
                            Toast.makeText(ForgotPassword.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}