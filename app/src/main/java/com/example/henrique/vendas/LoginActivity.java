package com.example.henrique.vendas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.objectbox.Box;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Box<Usuario> usuarioBox;
    SharedPreferences preferences = ListaAnunciosActivity.preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
        usuarioBox = ((App)getApplication()).getBoxStore().boxFor(Usuario.class);
    }

    private void setupViews() {
        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);
    }

    public void entrarClick(View view) {
        //TODO: doing
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        List<Usuario> result = usuarioBox.query()
                .equal(Usuario_.email, email)
                .equal(Usuario_.senha, senha)
                .build()
                .find();

        if (result.size() > 0){
            logar(result.get(0));
        }else {
            editSenha.getText().clear();
            Toast.makeText(this, "Email e/ou senha incorreto(s)!", Toast.LENGTH_SHORT).show();
        }

    }

    public void cadastrarClick(View view) {

        startActivity(new Intent(this, CadastroActivity.class));
        /*String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        usuarioBox.put(usuario);
        Toast.makeText(this, "Parabéns. vc ja está logado", Toast.LENGTH_SHORT).show();
        logar(usuario);*/
    }

    private void logar(Usuario usuario) {
        this.preferences = getSharedPreferences("olxcover.file", MODE_PRIVATE);
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong("usuarioId", usuario.getId());
        editor.apply();

        startActivity(new Intent(this, ListaAnunciosActivity.class));
    }
}
