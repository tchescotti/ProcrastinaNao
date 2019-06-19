package com.example.scotti.procrastinanao.pAtividade;

import android.graphics.drawable.Drawable;

public class Atividade {

    public String nome;
    public String tempo;
    public Drawable icon;

    public Atividade(){}

    public Atividade(String nome, String tempo, Drawable icon) {
        this.nome = nome;
        this.tempo = tempo;
        this.icon = icon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
