/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.DAO;

public class Comunicados {
    private int id_comunicados;
    private String texto_comunicado;
    private String data_comunicado;
    private int id_usuario;
    private String nome_usuario;

    public int getId_comunicados() {
        return id_comunicados;
    }

    public void setId_comunicados(int id_comunicados) {
        this.id_comunicados = id_comunicados;
    }

    public String getTexto_comunicado() {
        return texto_comunicado;
    }

    public void setTexto_comunicado(String texto_comunicado) {
        this.texto_comunicado = texto_comunicado;
    }

    public String getData_comunicado() {
        return data_comunicado;
    }

    public void setData_comunicado(String data_comunicado) {
        this.data_comunicado = data_comunicado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    
}
