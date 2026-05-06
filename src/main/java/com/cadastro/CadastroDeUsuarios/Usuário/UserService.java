package com.cadastro.CadastroDeUsuarios.Usuário;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public List<UserModel> listarTodos() {
        return userRepository.findAll();
    }

    @Transactional
    public UserModel criarUsuario(UserModel user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserModel atualizarUsuario(UserModel user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserModel buscarPorId(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public void deletarPorId(Long id) {
        userRepository.deleteById(id);
    }
    
}
