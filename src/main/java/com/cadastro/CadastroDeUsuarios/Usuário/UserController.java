package com.cadastro.CadastroDeUsuarios.Usuário;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Boas Vindas";
    }
}
