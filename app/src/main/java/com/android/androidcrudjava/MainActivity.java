package com.android.androidcrudjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.androidcrudjava.banco.crud.CriaBanco;
import com.android.androidcrudjava.modal.crud.Filme;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    CriaBanco dbHelper;
    ArrayList<Filme> listview_filmes;
    Filme filme;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Seta o botão de cadastrar e o tipo dele
        FloatingActionButton btnCadastrar = (FloatingActionButton) findViewById(R.id.btnCadastrar2);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Seta a MainActivity indo para a CadastroFilmes
                Intent intent = new Intent(MainActivity.this, CadastroFilmes.class);
                startActivity(intent);
            }
        });

        //lista os filmes na activity_main
        lista = (ListView) findViewById(R.id.listview_filme);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                //Pega o objeto da posição em que estiver na tela
                Filme filmeEscolhido = (Filme) adapter.getItemAtPosition(position);
                //Seta a MainActivity indo para a AtualizarFilmes
                Intent i = new Intent(MainActivity.this, AtualizarFilmes.class);
                i.putExtra("filme", filmeEscolhido);
                startActivity(i);

            }
        });
        //Dá um click longo no objeto posicionado na tela dentro da lista
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                filme = (Filme) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }
    //cria a lista dentro do método carregarFilme
    protected void onResume() {
        super.onResume();
        carregarFilme();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Filme");

        //passa p parâmetro do filme no (item) e deleta
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //Cria o conexão com o banco
                dbHelper = new CriaBanco(MainActivity.this);
                //Deleta o item selecionado
                dbHelper.deletarFilme(filme);
                //fecha a conexão
                dbHelper.close();
                //carrega novamente a lista
                carregarFilme();
                return true;
            }
        });
    }

    //lista os filmes cadastrados
    private void carregarFilme(){

        //Cria o conexão com o banco
        dbHelper = new CriaBanco(MainActivity.this);
        //Pega a lista de filmes do banco
        listview_filmes = dbHelper.getLista();
        //fecha a conexão
        dbHelper.close();

        //Verifica se a lista é diferente de null e cria na tela
        if(listview_filmes!=null){

            adapter = new ArrayAdapter<Filme>(MainActivity.this, android.R.layout.simple_list_item_1, listview_filmes);
            lista.setAdapter(adapter);
        }


    }

}

