package com.example.henrique.vendas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.objectbox.Box;

public class CadastroActivity extends AppCompatActivity {

    private EditText emailUsu;
    private EditText senhaUsu;
    private EditText nomeUsu;
    private EditText telUsu;
    private EditText endeUsu;

    private String email;
    private String senha;
    private String nome;
    private String tele;
    private String ende;

    private Usuario usuario;
    private Box<Usuario> usuarioBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setupViews();

        usuarioBox = ((App)getApplication()).getBoxStore().boxFor(Usuario.class);
        usuario = new Usuario();
    }

    private void setupViews() {
        emailUsu = findViewById(R.id.email_cadastro);
        senhaUsu = findViewById(R.id.senha_cadastro);
        nomeUsu = findViewById(R.id.nome_cadastro);
        telUsu = findViewById(R.id.tel_cadastro);
        endeUsu = findViewById(R.id.ende_cadastro);
    }

    public void confirmaCadastro(View View){
        this.email = emailUsu.getText().toString();
        this.senha = senhaUsu.getText().toString();
        this.nome = nomeUsu.getText().toString();
        this.tele = telUsu.getText().toString();
        this.ende = endeUsu.getText().toString();
        if(!validarDados()){
            Toast.makeText(this, "Dados incompletos ou email já cadastrado", Toast.LENGTH_SHORT).show();
        }
        else{
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setTel(tele);
            usuario.setEnde(ende);

            usuarioBox.put(usuario);
            Toast.makeText(this, "Usuário Cadastrado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean validarDados(){
        List<Usuario> existente = usuarioBox.find(Usuario_.email,email);
        if(existente.isEmpty()) {
            if (this.email.equals("") || this.senha.equals("") || this.nome.equals("") || this.tele.equals("") || this.ende.equals("")) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }
}
