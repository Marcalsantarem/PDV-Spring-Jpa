package com.marcalsantarem.pdvspringjpa.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcalsantarem.pdvspringjpa.entities.Product;
import com.marcalsantarem.pdvspringjpa.entities.dto.ResponseDTO;
import com.marcalsantarem.pdvspringjpa.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductRepository productRepository;

	@GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@Valid @RequestBody Product product){
        try {
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@Valid @RequestBody Product product){
        try {
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("Produto removido com sucesso!"), HttpStatus.OK);
        } catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }	
	
}
