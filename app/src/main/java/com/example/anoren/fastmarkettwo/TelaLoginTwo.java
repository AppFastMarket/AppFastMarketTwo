package com.example.anoren.fastmarkettwo;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLoginTwo extends AppCompatActivity {

    //OBJETO FIREBASE
    private static FirebaseAuth auth;
    //----------------------------------------------------------------------------------------------
    private Button btLogar, btNovoLogin;
    private EditText informaEmail, informaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login_two);
        this.setTitle("FastMarket");

        ativarComponentes(); acaoBotoes();

    }//---------------------------------------------------------------------------------------------
    private void ativarComponentes(){
        btLogar = (Button)findViewById(R.id.btnLogarLoginID);
        btNovoLogin = (Button)findViewById(R.id.btnNovoLoginLoginID);
        informaEmail = (EditText)findViewById(R.id.edtxtInfoEmailLoginID);
        informaSenha = (EditText)findViewById(R.id.edtxtInfoSenhaLoginID);
    }//---------------------------------------------------------------------------------------------
    private void acaoBotoes(){
        /*********************BOTÃO LOGAR**********************/
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = informaEmail.getText().toString().trim();
                String senha = informaSenha.getText().toString().trim();

                efetuarLogin(email, senha);

            }
        });
        /**********************BOTÃO LOGIN*********************/
        btNovoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenteLogin = new Intent(getApplicationContext(), CadastroLoginTwo.class);
                startActivity(intenteLogin);
            }
        });
    }//---------------------------------------------------------------------------------------------
    private void efetuarLogin(String email, String senha){
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener
                (TelaLoginTwo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intente = new Intent(getApplicationContext(), EscolhaMercado.class);
                            startActivity(intente);

                        }else{
                            exibiMensagem("E-mail ou Senha inválidos!!!");
                        }
                    }
                });

    }//---------------------------------------------------------------------------------------------
    //MÉTODO PARA EXIBIR AS MENSAGENS
    private void exibiMensagem(String mensagem) {
        Toast.makeText(TelaLoginTwo.this, mensagem, Toast.LENGTH_SHORT).show();
    }//---------------------------------------------------------------------------------------------
    //MÉTODO ON START
    @Override
    protected void onStart() {
        super.onStart();

        auth = ConectabdTwo.getFirebaseAuth(); //Adiciona no objeto Auth o que receber de conexão
    }//---------------------------------------------------------------------------------------------
    //Usando ActionBar na tela de login - Vai para tela de resetar a senha
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarlogintwo, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuAlteraSenhaID:
                Intent Login = new Intent(getApplicationContext(), ResetaLoginTwo.class);
                startActivity(Login);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}
