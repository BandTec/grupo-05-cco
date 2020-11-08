/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.jose.api_banco_local;

/**
 *
 * @author Aluno
 */
public class DadosBancoAzure {
    private Integer id;
    private String nome;
    private String rua;
    private String cidade;
    private String estado;
    private String ddn;
    private String email;
    private String senha;
    private String preferenciaTemp;
    private String preferenciaUmid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPreferenciaTemp() {
        return preferenciaTemp;
    }

    public void setPreferenciaTemp(String preferenciaTemp) {
        this.preferenciaTemp = preferenciaTemp;
    }

    public String getPreferenciaUmid() {
        return preferenciaUmid;
    }

    public void setPreferenciaUmid(String preferenciaUmid) {
        this.preferenciaUmid = preferenciaUmid;
    }
}
