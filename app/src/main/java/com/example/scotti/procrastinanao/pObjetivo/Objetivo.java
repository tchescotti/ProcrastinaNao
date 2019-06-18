package com.example.scotti.procrastinanao.pObjetivo;

public class Objetivo {

    public String nome;
    public String dia;
    public String etiqueta;
    public String descricao;
    public String id;

    public Objetivo(){}

    public Objetivo(String nome, String dia, String etiqueta, String descricao, String id){
        this.nome = nome;
        this.dia = dia;
        this.etiqueta = etiqueta;
        this.descricao = descricao;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
