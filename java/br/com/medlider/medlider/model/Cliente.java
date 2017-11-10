package br.com.medlider.medlider.model;

import java.io.Serializable;

/**
 * Created by Filipe Firmino on 30/10/2017.
 */

public class Cliente implements Serializable{

    private Long id;
    private String nome;
    private String CPF;
    private String RG;
    private String telefone;
    private String endereco;
    private String sexo;
    private String idade;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return getId() +" - " +getNome()+"\n\nTelefone: "+getTelefone()+"\nCPF: "+getCPF();
    }
}
