package com.marcalsantarem.pdvspringjpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcalsantarem.pdvspringjpa.entities.dto.ResponseDTO;
import com.marcalsantarem.pdvspringjpa.entities.dto.SaleDTO;
import com.marcalsantarem.pdvspringjpa.services.SaleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/sale")
@RequiredArgsConstructor
public class SaleController {

	private final SaleService saleService;
	
	@GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(saleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id){
        try {
            return new ResponseEntity<>(saleService.getById(id), HttpStatus.OK);
        } catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody SaleDTO saleDTO){
        try {
            saleService.save(saleDTO);
            return new ResponseEntity<>(new ResponseDTO("Venda realizada com sucesso!"), HttpStatus.CREATED);
        } catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
