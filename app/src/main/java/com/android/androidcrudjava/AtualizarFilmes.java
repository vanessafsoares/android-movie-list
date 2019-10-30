package com.android.androidcrudjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.androidcrudjava.banco.crud.CriaBanco;
import com.android.androidcrudjava.modal.crud.Filme;

public class AtualizarFilmes extends AppCompatActivity {

    Filme filmes = new Filme();
    Filme editarFilme = new Filme();
    CriaBanco dbHelper;

    Button btnAtualizar;
    EditText txtTitulo,txtGenero,txtAno,txtDiretor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);

        //Pega as propriedades da tela dos layouts
        dbHelper = new CriaBanco(AtualizarFilmes.this);

        editarFilme = (Filme) getIntent().getExtras().getSerializable("filme");

        //Usa o objeto criado nos xmls de layouts
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        txtTitulo = (EditText) findViewById(R.id.txt2titulo);
        txtTitulo.setText(editarFilme.getTitulo());

        txtGenero = (EditText) findViewById(R.id.txt2Genero);
        txtGenero.setText(editarFilme.getGenero());

        txtAno = (EditText) findViewById(R.id.txt2Ano);
        txtAno.setText(editarFilme.getAno());

        txtDiretor = (EditText) findViewById(R.id.txt2Diretor);
        txtDiretor.setText(editarFilme.getDiretor());

        btnAtualizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                filmes.setId(editarFilme.getId());
                filmes.setTitulo(txtTitulo.getText().toString());
                filmes.setGenero(txtGenero.getText().toString());
                filmes.setAno(txtAno.getText().toString());
                filmes.setDiretor(txtDiretor.getText().toString());

                dbHelper.alterarFilme(filmes);
                dbHelper.close();

                Toast.makeText(getApplicationContext(), "Filme Alterado: "+ txtTitulo.getText().toString(),Toast.LENGTH_LONG).show();
                finish();



            }
        });
    }
}
