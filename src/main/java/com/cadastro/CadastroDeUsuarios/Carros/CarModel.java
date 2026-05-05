package com.cadastro.CadastroDeUsuarios.Carros;

import com.cadastro.CadastroDeUsuarios.Usuário.UserModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_carros")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String ano;

    @ManyToOne
    @JoinColumn(name = "carros_id")
    private List<UserModel> usuarios;
}
