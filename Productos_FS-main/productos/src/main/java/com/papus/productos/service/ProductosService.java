package com.papus.productos.service;

import com.papus.productos.model.Productos;
import com.papus.productos.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class ProductosService {
    @Autowired
    private ProductosRepository ProductosRepository;

    //Guardar o Actualizar
    public Productos save(Productos productos){
        return ProductosRepository.save(productos);
    }

    //Listar todos los productos
    public  List<Productos> findAll(){
        return ProductosRepository.findAll();
    }

    //Buscar por ID
    public Optional<Productos> findById(long id){
        return ProductosRepository.findById(id);
    }

    //Eliminar por ID
    public void deleteById(long id){
        ProductosRepository.deleteById(id);
    }
}