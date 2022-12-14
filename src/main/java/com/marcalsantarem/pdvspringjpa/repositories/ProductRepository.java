package com.marcalsantarem.pdvspringjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcalsantarem.pdvspringjpa.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
