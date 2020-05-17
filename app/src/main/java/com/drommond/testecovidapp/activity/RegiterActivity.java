package com.drommond.testecovidapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.drommond.testecovidapp.R;
import com.drommond.testecovidapp.configs.ConfiguracaoFirebase;
import com.drommond.testecovidapp.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegiterActivity extends AppCompatActivity {

    EditText campoNome, campoEmail, campoSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);

        campoNome = findViewById(R.id.editTextNome);
        campoEmail = findViewById(R.id.editTextEmail);
        campoSenha = findViewById(R.id.editTextSenha);

    }
    public void btnEnviarCadastro(View view){

        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!nome.isEmpty()){
            if (!email.isEmpty()){
                if (!senha.isEmpty()){
                    Usuario user = new Usuario();
                    user.setNome(nome);
                    user.setEmail(email);
                    user.setSenha(senha);
                    salvarUsuarioFirebase(user);

                }else {
                    Toast.makeText(this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Preencha o campo E-mail", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o campo nome", Toast.LENGTH_SHORT).show();
        }

    }

    private void salvarUsuarioFirebase(Usuario usuario) {

        auth = ConfiguracaoFirebase.getAuth();
        auth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(RegiterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e ){
                        excecao = "Digite um email valido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Essa conta ja existe";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuario: "+ e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(RegiterActivity.this, excecao, Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
