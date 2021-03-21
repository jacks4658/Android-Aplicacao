package com.ltf.aplicativo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.ltf.aplicativo.Config.ConfiguracaoFirebase;
import com.ltf.aplicativo.R;

public class Main2Activity extends AppCompatActivity {
private FirebaseAuth auttenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (getApplicationContext(), NewsActivity.class));
            }
        });


        auttenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();


        Toolbar toolbar=findViewById(R.id.toolbarMinha);
        toolbar.setTitle("Tela Principal");
        setSupportActionBar(toolbar);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater Inflater=getMenuInflater();
        Inflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuSair:
                deslogar();
                finish();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void deslogar(){

        try{

            auttenticacao.signOut();

        }catch (Exception e){
            e.printStackTrace();



        }


    }

}



