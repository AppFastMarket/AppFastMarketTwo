package com.example.anoren.fastmarkettwo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetaLoginTwo extends AppCompatActivity {

    //OBJETO FIREBASE
    private static FirebaseAuth auth;
    //----------------------------------------------------------------------------------------------
    private Button btResetar;
    private EditText informaEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseta_login_two);
        this.setTitle("Resetar Senha");

        ativarComponentes(); acaoBotao();

    }//---------------------------------------------------------------------------------------------
    private void ativarComponentes() {
        btResetar = (Button) findViewById(R.id.btnResetarResetaLoginID);
        informaEmail = (EditText) findViewById(R.id.edtxtInfoEmailResetaSenhaID);
    }//---------------------------------------------------------------------------------------------
    private void acaoBotao() {
        btResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = informaEmail.getText().toString().trim();

                resetarSenha(email);
            }
        });
    }//---------------------------------------------------------------------------------------------
    private void resetarSenha(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener
                (ResetaLoginTwo.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            exibiMensagem("E-mail enviado para resetar sua senha!!!");
                            finish();
                        }else{
                            exibiMensagem("E-mail Inválido!!!");
                        }
                    }
                });
    }//---------------------------------------------------------------------------------------------
    //MÉTODO PARA EXIBIR AS MENSAGENS
    private void exibiMensagem(String mensagem) {
        Toast.makeText(ResetaLoginTwo.this, mensagem, Toast.LENGTH_LONG).show();
    }//---------------------------------------------------------------------------------------------
    //MÉTODO ON START
    @Override
    protected void onStart() {
        super.onStart();

        auth = ConectabdTwo.getFirebaseAuth(); //Adiciona no objeto Auth o que receber de conexão
    }//---------------------------------------------------------------------------------------------
}
