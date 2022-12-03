package com.marcalsantarem.pdvspringjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcalsantarem.pdvspringjpa.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
