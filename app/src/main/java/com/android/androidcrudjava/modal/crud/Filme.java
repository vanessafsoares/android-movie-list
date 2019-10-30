package com.android.androidcrudjava.modal.crud;

import androidx.annotation.NonNull;

import java.io.Serializable;

    //Representa o objeto que vai ser serializado
public class Filme implements Serializable {

    private Long id;
    private String titulo;
    private String ano;
    private String genero;
    private String diretor;

    @NonNull
    @Override
    public String toString() {
        return id + " - " + titulo.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }


}
