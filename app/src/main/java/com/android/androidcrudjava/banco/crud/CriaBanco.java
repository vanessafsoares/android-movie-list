package com.android.androidcrudjava.banco.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.androidcrudjava.modal.crud.Filme;

import java.util.ArrayList;

public class CriaBanco extends SQLiteOpenHelper {
    //privado e constante
    private SQLiteDatabase db;

    private static final String NOME_BANCO = "sistemafilme";
    private static final String TABELA = "FILMES";
    private static final String ID = "ID";
    private static final String TITULO = "TITULO";
    private static final String DIRETOR = "DIRETOR";
    private static final String ANO = "ANO";
    private static final String GENERO = "GENERO";
    private static final int VERSAO = 1;

    public CriaBanco(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    //Criando a tabela do banco
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABELA+"("
                     +ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                     +TITULO + " TEXT NOT NULL,"
                     +GENERO + " TEXT NOT NULL,"
                     + ANO +" TEXT NOT NULL,"
                     + DIRETOR + " TEXT NOT NULL)";

        db.execSQL(sql);

    }

    @Override
    //dropando a tabela se ela existir
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS";
        db.execSQL(dropTable + TABELA);
        onCreate(db);
    }

    //Joga os dados no banco
    public void salvarFilme(Filme filme) {
        ContentValues values = new ContentValues();


        values.put("titulo", filme.getTitulo());
        values.put("genero", filme.getGenero());
        values.put("ano", filme.getAno());
        values.put("diretor", filme.getDiretor());


         getWritableDatabase().insert("filmes", null, values);



    }

    //atualizar filmes
    public void alterarFilme(Filme filme){
        ContentValues values = new ContentValues();

        values.put("titulo", filme.getTitulo());
        values.put("genero", filme.getGenero());
        values.put("ano", filme.getAno());
        values.put("diretor", filme.getDiretor());

        System.out.println("VALOREEEEEEES: "+values);
        String[] args = {filme.getId().toString()};


        getWritableDatabase().update("filmes", values, "id=?", args);


    }
    //deletar filmes
    public void deletarFilme(Filme filme){
        String [] args = {filme.getId().toString()};

        getWritableDatabase().delete("filmes", "id=?", args);
    }

    //Retorna a lista dos filmes
    public ArrayList<Filme> getLista(){

        String[] colums = {"id", "titulo", "genero", "ano","diretor"};
        Cursor cursor = getWritableDatabase().query("filmes", colums,null,null,null,null,null,null);
        ArrayList<Filme> filmes = new ArrayList<Filme>();

        while (cursor.moveToNext()){
            Filme filme = new Filme();
            filme.setId(cursor.getLong(0));
            filme.setTitulo(cursor.getString(1));
            filme.setGenero(cursor.getString(2));
            filme.setAno(cursor.getString(3));
            filme.setDiretor(cursor.getString(4));

            filmes.add(filme);
        }

        return filmes;
    }
}
