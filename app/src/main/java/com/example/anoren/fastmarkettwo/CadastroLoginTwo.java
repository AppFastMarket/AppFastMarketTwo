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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroLoginTwo extends AppCompatActivity {

    //OBJETO FIREBASE
    private static FirebaseAuth auth;

    //----------------------------------------------------------------------------------------------
    private Button btGravar;
    private EditText informaEmail, informaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login_two);
        this.setTitle("Cadastro Usuário");

        ativarComponentes(); acaoBotoes();
    }//---------------------------------------------------------------------------------------------
    private void ativarComponentes(){
        btGravar = (Button)findViewById(R.id.btnGravarCadastroID);
        informaEmail = (EditText)findViewById(R.id.edtxtInfoEmailCadastroID);
        informaSenha = (EditText)findViewById(R.id.edtxtInfoSenhaCadastroID);
    }//---------------------------------------------------------------------------------------------
    private void acaoBotoes(){

        btGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = informaEmail.getText().toString().trim();
                String senha = informaSenha.getText().toString().trim();

                criarUsuario(email, senha);
            }
        });
    }//---------------------------------------------------------------------------------------------
    private void criarUsuario(String email, String senha){

        //Obejto Auth utilizando autenticação de email e senha do Firebase
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener
                        (CadastroLoginTwo.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    exibiMensagem("Cadastro efetuado com sucesso!!!");

                                }else{
                                    exibiMensagem("Erro de cadastro!!!");
                                }
                            }
                        });
    }//---------------------------------------------------------------------------------------------
    //MÉTODO PARA EXIBIR AS MENSAGENS
    private void exibiMensagem(String mensagem){
        Toast.makeText(CadastroLoginTwo.this, mensagem, Toast.LENGTH_LONG).show();
    }//---------------------------------------------------------------------------------------------
    //MÉTODO ON START
    @Override
    protected void onStart() {
        super.onStart();

        auth = ConectabdTwo.getFirebaseAuth(); //Adiciona no objeto Auth o que receber de conexão
    }//---------------------------------------------------------------------------------------------


}
