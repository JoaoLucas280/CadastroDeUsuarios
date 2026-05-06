package com.cadastro.CadastroDeUsuarios.Usuário;

import com.cadastro.CadastroDeUsuarios.Carros.CarModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_cadastro")
@Data //Cria os getters and setters automaticamente
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sexo;
    private String hobbie;
    @OneToMany(mappedBy = "usuarios")
    private List<CarModel> carros;


}
