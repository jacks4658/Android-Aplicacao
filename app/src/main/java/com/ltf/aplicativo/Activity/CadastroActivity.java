package com.ltf.aplicativo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.ltf.aplicativo.Config.ConfiguracaoFirebase;
import com.ltf.aplicativo.R;
import com.ltf.aplicativo.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);


    }

    public void cadastrarUsuario(Usuario usuario){




            autenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();
            autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()

            ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                   if(task.isSuccessful()){


                       Toast.makeText(CadastroActivity.this,"Cadastro Efetuado",Toast.LENGTH_SHORT).show();
                       finish();

                   }else{
                       String exececao="";

                       try{

                           throw task.getException();
                       }catch (FirebaseAuthWeakPasswordException e){

                           exececao="Digite uma Senha Mais forte!";
                       }catch (FirebaseAuthInvalidCredentialsException e){

                           exececao="Digite email Valido!";
                       }catch (FirebaseAuthUserCollisionException e){

                           exececao="Conta ja cadastrada!";
                       }catch (Exception e){

                           exececao="erro ao cadastrar!"+e.getMessage();
                           e.printStackTrace();
                       }

                       Toast.makeText(CadastroActivity.this,exececao,Toast.LENGTH_SHORT).show();

                   }



                }
            });
        }



    public void validarUsuario(View view) {

        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if (!textoNome.isEmpty()) {
            if (!textoEmail.isEmpty()) {
                if (!textoSenha.isEmpty()) {

                Usuario usuario=new Usuario();
                usuario.setNome(textoNome);
                usuario.setEmail(textoEmail);
                usuario.setSenha(textoSenha);

                cadastrarUsuario(usuario);

                } else {

                    Toast.makeText(CadastroActivity.this,
                            "Preencha Senha", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(CadastroActivity.this,
                        "Prencha o Email", Toast.LENGTH_SHORT).show();

            }


        } else {

            Toast.makeText(CadastroActivity.this,
                    "Prencha o Nome", Toast.LENGTH_SHORT).show();


        }


    }


}
