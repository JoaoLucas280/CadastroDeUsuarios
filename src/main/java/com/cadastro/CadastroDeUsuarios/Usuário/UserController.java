package com.cadastro.CadastroDeUsuarios.Usuário;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> listarTodos() {
        return userService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public UserModel buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }
    
    @PostMapping
    public UserModel criarUsuario(@RequestBody UserModel user) {
        return userService.criarUsuario(user);
    }
    
    @PutMapping("/{id}")
    public UserModel atualizarUsuario(@PathVariable Long id, @RequestBody UserModel user) {
        user.setId(id);
        return userService.atualizarUsuario(user);
    }
    
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        userService.deletarPorId(id);
    }
}
