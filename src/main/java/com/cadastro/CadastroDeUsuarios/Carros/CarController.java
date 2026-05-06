package com.cadastro.CadastroDeUsuarios.Carros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarController {
    
    @Autowired
    private CarService carService;

    @GetMapping
    public List<CarModel> listarTodos() {
        return carService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public CarModel buscarPorId(@PathVariable Long id) {
        return carService.buscarPorId(id);
    }
    
    @PostMapping
    public CarModel criarCarro(@RequestBody CarModel car) {
        return carService.criarCarro(car);
    }
    
    @PutMapping("/{id}")
    public CarModel atualizarCarro(@PathVariable Long id, @RequestBody CarModel car) {
        car.setId(id);
        return carService.atualizarCarro(car);
    }
    
    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable Long id) {
        carService.deletarPorId(id);
    }
}

