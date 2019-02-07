package com.example.henrique.vendas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.objectbox.Box;

public class FormularioAnuncioActivity extends AppCompatActivity {

    //Data binding ou butterknife

    private EditText editTitulo;
    private EditText editPreco;
    private EditText editLocalizacao;

    private Anuncio anuncio;

    private Box<Anuncio> anuncioBox;
    private Box<Usuario> usuarioBox;
    private Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_anuncio);

        setupViews();

        Intent intent = getIntent();
        long anuncioId = intent.getLongExtra("anuncioId", -1);

        anuncioBox = ((App)getApplication()).getBoxStore().boxFor(Anuncio.class);
        usuarioBox = ((App)getApplication()).getBoxStore().boxFor(Usuario.class);
        usuarioLogado = obterUsuarioLogado();

        if (anuncioId == -1){
            anuncio = new Anuncio();
        }else {
            anuncio = anuncioBox.get(anuncioId);
            preencherFormulario(anuncio);
        }
    }

    private Usuario obterUsuarioLogado() {
        final SharedPreferences preferences = getSharedPreferences("olxcover.file", MODE_PRIVATE);
        final long id = preferences.getLong("usuarioId", -1);
        return usuarioBox.get(id);
    }

    private void preencherFormulario(Anuncio anuncio) {
        editTitulo.setText(anuncio.getTitulo());
        editPreco.setText(""+anuncio.getValor());
        editLocalizacao.setText(anuncio.getLocalizacao());
    }

    private void setupViews() {
        editTitulo = findViewById(R.id.edit_titulo);
        editPreco = findViewById(R.id.edit_preco);
        editLocalizacao = findViewById(R.id.edit_localizacao);
    }

    public void salvarAnuncio(View view) {

        String titulo = editTitulo.getText().toString();
        double preco = Double.parseDouble(editPreco.getText().toString());
        String localizacao = editLocalizacao.getText().toString();

        anuncio.setTitulo(titulo);
        anuncio.setValor(preco);
        anuncio.setLocalizacao(localizacao);
        anuncio.setIddono(usuarioLogado.getId());
        //anuncio.getDono().setTarget(usuarioLogado);

        anuncioBox.put(anuncio);

        Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
        finish();

    }
}
