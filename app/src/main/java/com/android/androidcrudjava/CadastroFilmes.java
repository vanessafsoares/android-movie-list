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

public class CadastroFilmes extends AppCompatActivity {

    Filme filmes = new Filme();
    Filme editarFilme;
    CriaBanco dbHelper;

    Button btnCadastrar;
    EditText txtTitulo,txtGenero,txtAno,txtDiretor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_layout);

        // Cria a instância com o banco de dados
        dbHelper = new CriaBanco(CadastroFilmes.this);

        // Pega o objeto que foi serializado em outra activity
        Intent intent = getIntent();
        editarFilme = (Filme) intent.getSerializableExtra("filme");

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar2);
        txtTitulo = (EditText) findViewById(R.id.txt1titulo);
        txtGenero = (EditText) findViewById(R.id.txt1Genero);
        txtAno = (EditText) findViewById(R.id.txt1Ano);
        txtDiretor = (EditText) findViewById(R.id.txt1Diretor);

        // Pega os caracteres e converte para string
        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                filmes.setTitulo(txtTitulo.getText().toString());
                filmes.setGenero(txtGenero.getText().toString());
                filmes.setAno(txtAno.getText().toString());
                filmes.setDiretor(txtDiretor.getText().toString());

                //Joga o objeto filmes no banco
                dbHelper.salvarFilme(filmes);
                Toast.makeText(getApplicationContext(), "Filme Cadastrado: "+ txtTitulo.getText().toString(),Toast.LENGTH_LONG).show();
                dbHelper.close();
                finish();

            }
        });
}
}
