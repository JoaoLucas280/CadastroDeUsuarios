package com.cadastro.CadastroDeUsuarios;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cadastro")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sexo;
    private String hobbie;

    public UserModel() {}

    public UserModel(String nome, String sexo, String hobbie) {
        this.nome = nome;
        this.sexo = sexo;
        this.hobbie = hobbie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }
}
