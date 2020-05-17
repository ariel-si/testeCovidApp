package com.drommond.testecovidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.drommond.testecovidapp.R;
import com.drommond.testecovidapp.configs.ConfiguracaoFirebase;
import com.drommond.testecovidapp.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText campoUsuario, campoSenha;
    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = ConfiguracaoFirebase.getAuth();

        campoUsuario = findViewById(R.id.editLoginUsuario);
        campoSenha = findViewById(R.id.editLoginSenha);

    }

    public void logarUsuario(Usuario usuario){

        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    telaPrincipal();
                }else {

                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch ( FirebaseAuthInvalidUserException e){
                        excecao = "Usuario não esta cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Senha ou email incorretos";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário"+e.getMessage();
                        e.printStackTrace();
                    }

                    toast("ao Logar usuario");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = auth.getCurrentUser();
        if(usuarioAtual != null){
            telaPrincipal();
        }else{

        }
    }

    private void telaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivityToolBar.class);
        startActivity(intent);

        campoUsuario.setText("");
        campoSenha.setText("");
    }

    public void validarUsuario(View view){

        String email = campoUsuario.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!email.isEmpty()){
            if (!senha.isEmpty()){
                Usuario user = new Usuario();

                user.setEmail(email);
                user.setSenha(senha);

                logarUsuario(user);

            }else{
                toast("Digite o campo Senha");
            }
        }else{
            toast("Digite o campo E-mail");
        }

    }

    public void btnRegister(View view){
        Intent intent = new Intent(LoginActivity.this, RegiterActivity.class);
        startActivity(intent);
    }

    public void toast(String msg){
        Toast.makeText(this, "Erro: "+msg, Toast.LENGTH_LONG).show();
    }
}
