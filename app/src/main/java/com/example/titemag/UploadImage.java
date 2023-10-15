package com.example.titemag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class UploadImage extends AppCompatActivity  {
    ImageView Painting;
    Button savePainting;
    EditText Painting_name, Student_name, Enrollment;
    String imgURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        Painting =findViewById(R.id.uplaodImage);
        Painting_name =findViewById(R.id.uploadName);
        Student_name =findViewById(R.id.uploadDesc);
        Enrollment =findViewById(R.id.uploadang);
        savePainting =findViewById(R.id.saveButton);

        removeHint(Painting_name,"Theme of Painting");
        removeHint(Student_name,"Enter your Name");
        removeHint(Enrollment,"Enter your Enrollment Number");

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        uri=data.getData();
                        Painting.setImageURI(uri);
                    }
                    else{
                        Toast.makeText(UploadImage.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                });
        Painting.setOnClickListener(view -> {
            Intent iphoto=new Intent(Intent.ACTION_PICK);
            iphoto.setType("image/*");
            activityResultLauncher.launch(iphoto);
        });
        savePainting.setOnClickListener(view -> {
            if(!Painting_name.getText().toString().equals("")&& !Student_name.getText().toString().equals("")&&!Enrollment.getText().toString().equals("")){
                saveData();
            }
            else{
                Toast.makeText(UploadImage.this, "Update the Required field", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void saveData(){
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("Paintings")
                .child(Objects.requireNonNull(uri.getLastPathSegment()));
        AlertDialog.Builder builder= new AlertDialog.Builder(UploadImage.this);
        builder.setCancelable(false);
        builder.setView(R.layout.process_layout);
        AlertDialog dialog = builder.create();
        dialog.show();


        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            while(!uriTask.isComplete());
            Uri urlImage =uriTask.getResult();
            imgURL=urlImage.toString();
            uploadData();
            dialog.dismiss();


        }).addOnFailureListener(e -> dialog.dismiss());
    }
    public void uploadData(){
        String Topic= Painting_name.getText().toString();
        String desc = Student_name.getText().toString();
        String language = Enrollment.getText().toString();
        DataClass dataClass=new DataClass(Topic,desc,language,imgURL);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Paintings");
        databaseReference.child(Objects.requireNonNull(databaseReference.push().getKey()))
                .setValue(dataClass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(UploadImage.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(UploadImage.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show());

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
