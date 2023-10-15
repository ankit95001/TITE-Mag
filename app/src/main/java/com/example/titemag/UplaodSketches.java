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

public class UplaodSketches extends AppCompatActivity {
    ImageView Sketch;
    Button saveSketch;
    EditText Sketch_name, Student_name, Enrollment;
    String imgURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplaod_sketches);

        Sketch =findViewById(R.id.upload_image_sketches);
        Sketch_name =findViewById(R.id.upload_sketches_sketch_name);
        Student_name =findViewById(R.id.upload_sketches_name);
        Enrollment =findViewById(R.id.upload_sketches_enrollment);
        saveSketch =findViewById(R.id.saveSketches_Button);

        removeHint(Sketch_name,"Enter theme of Sketch");
        removeHint(Student_name,"Enter your Name");
        removeHint(Enrollment,"Enter your Enrollment Number");

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        Intent data = result.getData();
                        assert data != null;
                        uri=data.getData();
                        Sketch.setImageURI(uri);
                    }
                    else{
                        Toast.makeText(UplaodSketches.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                });
        Sketch.setOnClickListener(view -> {
            Intent iphoto=new Intent(Intent.ACTION_PICK);
            iphoto.setType("image/*");
            activityResultLauncher.launch(iphoto);
        });
        saveSketch.setOnClickListener(view -> {
            if(!Sketch_name.getText().toString().equals("")&& !Student_name.getText().toString().equals("")&&!Enrollment.getText().toString().equals("")){
                saveData();
            }
            else{
                Toast.makeText(UplaodSketches.this, "Update the Required field", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void saveData(){
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("Sketches")
                .child(Objects.requireNonNull(uri.getLastPathSegment()));
        AlertDialog.Builder builder= new AlertDialog.Builder(UplaodSketches.this);
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
        String Sketch_Name= Sketch_name.getText().toString();
        String student_name = Student_name.getText().toString();
        String enrollment = Enrollment.getText().toString();
        DataClass dataClass=new DataClass(Sketch_Name,student_name,enrollment,imgURL);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Sketches");
        databaseReference.child(Objects.requireNonNull(databaseReference.push().getKey()))
                .setValue(dataClass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(UplaodSketches.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(UplaodSketches.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show());
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