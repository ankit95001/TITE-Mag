package com.example.titemag;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class Upload_Articles extends AppCompatActivity {
    Button upload_btn;
    EditText pdf_name, student_name, enrollment;
    ImageView img;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplaod_articles);

        upload_btn = findViewById(R.id.saveArticles_Button);
        pdf_name = findViewById(R.id.upload_articles_article_name);
        student_name = findViewById(R.id.upload_articles_name);
        enrollment = findViewById(R.id.upload_articles_enrollment);
        img = findViewById(R.id.upload_image_articles);

        removeHint(pdf_name,"Theme of Article");
        removeHint(student_name,"Enter your Name");
        removeHint(enrollment,"Enter your Enrollment Number");


        //DataBase
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Articles");

        img.setOnClickListener(view -> {
            String[] mimeTypes =
                    {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                            "text/plain",
                            "application/pdf"};

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(Intent.createChooser(intent,"ChooseFile"), 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            img.setImageResource(R.drawable.baseline_text_snippet_24);
            Toast.makeText(this, "File Selected!", Toast.LENGTH_SHORT).show();
            upload_btn.setOnClickListener(view -> {

                if(!pdf_name.getText().toString().equals("")&& !student_name.getText().toString().equals("")&&!enrollment.getText().toString().equals("")){
                    UploadFiles(data.getData());
                }
                else{
                    Toast.makeText(Upload_Articles.this, "Update the Required field", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(Upload_Articles.this, "No item Selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void UploadFiles(Uri data){
        AlertDialog.Builder builder= new AlertDialog.Builder(Upload_Articles.this);
        builder.setCancelable(false);
        builder.setView(R.layout.process_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        StorageReference reference=storageReference.child("Articles/"+pdf_name.getText().toString());
        reference.putFile(data).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while(!uriTask.isComplete());
            Uri url = uriTask.getResult();

            DataClass dataClass = new DataClass(pdf_name.getText().toString(),student_name.getText().toString(),enrollment.getText().toString(),url.toString());
            databaseReference.child(Objects.requireNonNull(databaseReference.push().getKey())).setValue(dataClass);
            Toast.makeText(Upload_Articles.this, "File Uploaded", Toast.LENGTH_SHORT).show();
            finish();
            dialog.dismiss();
        }).addOnFailureListener(e -> Toast.makeText(Upload_Articles.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show());
    }
    @SuppressLint("ClickableViewAccessibility")
    private void removeHint(EditText editText,String Hint){
        editText.setOnTouchListener((v, event) -> {
            editText.setHint("");
            return false;
        });

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                editText.setHint(Hint);
            }
        });
    }
}
