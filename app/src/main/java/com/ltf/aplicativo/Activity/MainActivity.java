package com.ltf.aplicativo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ltf.aplicativo.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
private WebView web;
    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event ) {
        if(keyCode==KeyEvent.KEYCODE_B && web.canGoBack ()){

            web.goBack ();
            return true;
        }

        return super.onKeyDown ( keyCode, event );
    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WebView webView = findViewById ( R.id.Siteviwel );
        webView.loadUrl ( "https://expo-ae086.web.app/" );

        webView.getSettings ( ).setJavaScriptEnabled ( true );


        final ProgressBar progressBar = findViewById ( R.id.progressBar );
        progressBar.setVisibility ( View.INVISIBLE );
        webView.setWebViewClient ( new WebViewClient ( ) {
            @Override
            public void onPageStarted ( WebView view, String url, Bitmap favicon ) {
                super.onPageStarted ( view, url, favicon );
                progressBar.setVisibility ( view.VISIBLE );
            }

            @Override
            public void onPageFinished ( WebView view, String url ) {
                super.onPageFinished ( view, url );
                progressBar.setVisibility ( View.INVISIBLE );
            }
        } );

    }
}