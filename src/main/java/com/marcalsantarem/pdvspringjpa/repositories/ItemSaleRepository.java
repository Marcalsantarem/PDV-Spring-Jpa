package com.marcalsantarem.pdvspringjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcalsantarem.pdvspringjpa.entities.ItemSale;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSale, Long>{

}
