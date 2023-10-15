package com.example.titemag;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;

public class DocumentViewer extends AppCompatActivity {
    WebView web;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_viewer);
        web= findViewById(R.id.webView);

        web.getSettings().setJavaScriptEnabled(true);


        String fileUrl=getIntent().getStringExtra("fileUrl");

        AlertDialog.Builder builder= new AlertDialog.Builder(DocumentViewer.this);
        builder.setCancelable(false);
        builder.setView(R.layout.saving_process_layout);
        AlertDialog dialog = builder.create();


        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });
        String Ur="";
        try {
            Ur= URLEncoder.encode(fileUrl,"UTF-8");
        }catch (Exception e){
            Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
        }
        Log.d("Ankit","message : "+Ur);
        web.loadUrl("http://docs.google.com/gview?embedded=true&url="+Ur);

    }

    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_view_articles);
    }
}