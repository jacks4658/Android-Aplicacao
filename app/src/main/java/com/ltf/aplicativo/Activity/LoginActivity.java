package com.ltf.aplicativo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.ltf.aplicativo.Config.ConfiguracaoFirebase;
import com.ltf.aplicativo.R;
import com.ltf.aplicativo.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText campoEmail, campoSenha;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        campoEmail = findViewById(R.id.editLogimEmail);
        campoSenha = findViewById(R.id.editLoginSenha);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();


    }

    public void logarUsuario(Usuario usuario) {


        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()

        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    abrirTelaPrincipal();


                } else {

                    String exececao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {

                        exececao = "Conta nao cadastrada!";

                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        exececao = "Email e senha nao corresponde!";

                    } catch (Exception e) {

                        exececao = "erro ao Cadastrar!" + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, "Erro de autenticação!", Toast.LENGTH_SHORT).show();


                }

            }
        });

    }

    public void validarautenticacacaoUsuario(View view) {


        String textoEmail = campoEmail.getText().toString();
        String texSenha = campoSenha.getText().toString();


        if (!textoEmail.isEmpty()) {
            if (!texSenha.isEmpty()) {


                Usuario usuario = new Usuario();
                usuario.setEmail(textoEmail);
                usuario.setSenha(texSenha);


                logarUsuario(usuario);

            } else {
                Toast.makeText(LoginActivity.this,
                        "Digite a Senha", Toast.LENGTH_SHORT).show();

            }


        } else {

            Toast.makeText(LoginActivity.this,
                    "Digite o Email!", Toast.LENGTH_SHORT).show();


        }


    }


    // @Override
    // protected void onStart() {
      // super.onStart();


      // FirebaseUser usuarioLogado=autenticacao.getCurrentUser();
     //  if(usuarioLogado!=null);
   //  abrirTelaPrincipal();
  // }

    public void abrirTelaCadastro(View view) {

        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);

        startActivity(intent);

    }


    public void abrirTelaPrincipal() {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
    }
}
