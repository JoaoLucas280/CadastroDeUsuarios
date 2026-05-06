package com.cadastro.CadastroDeUsuarios.Carros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public List<CarModel> listarTodos() {
        return carRepository.findAll();
    }

    @Transactional
    public CarModel criarCarro(CarModel carModel) {
        return carRepository.save(carModel);
    }

    @Transactional
    public CarModel atualizarCarro(CarModel carModel) {
        return carRepository.save(carModel);
    }

    @Transactional
    public CarModel buscarPorId(Long id) {
        return carRepository.findById(id).get();
    }

    @Transactional
    public void deletarPorId(Long id) {
        carRepository.deleteById(id);
    }

}
