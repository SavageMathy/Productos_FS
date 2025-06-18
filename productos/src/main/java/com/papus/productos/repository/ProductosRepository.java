package com.papus.productos.repository;

import com.papus.productos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<Productos, Long> {

}