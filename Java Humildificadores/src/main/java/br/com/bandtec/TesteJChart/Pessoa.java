/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bandtec.TesteJChart;

/**
 *
 * @author Aluno
 */
public class Pessoa {
    
    protected String nome;
    protected Integer cpu_media_temperatura;

    public String getNome() {
        return nome;
    }

    public Integer getCpu_media_temperatura() {
        return cpu_media_temperatura;
    }

    public Pessoa(String nome, Integer cpu_media_temperatura) {
        this.nome = nome;
        this.cpu_media_temperatura = cpu_media_temperatura;
    }

    
}
