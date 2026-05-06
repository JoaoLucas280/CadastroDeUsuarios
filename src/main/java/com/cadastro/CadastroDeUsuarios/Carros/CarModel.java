package com.cadastro.CadastroDeUsuarios.Carros;

import com.cadastro.CadastroDeUsuarios.Usuário.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_carros")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonBackReference
    private UserModel usuarios;
}
