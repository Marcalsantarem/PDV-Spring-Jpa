package com.marcalsantarem.pdvspringjpa.controllers;

import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;
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

import com.marcalsantarem.pdvspringjpa.entities.dto.ResponseDTO;
import com.marcalsantarem.pdvspringjpa.entities.dto.UserDTO;
import com.marcalsantarem.pdvspringjpa.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	@GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity post(@Valid @RequestBody UserDTO user){
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity put(@Valid @RequestBody UserDTO user){
      try{
          return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
      } catch (Exception error){
          return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        try
        {
            userService.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("Usuário removido com sucesso!"), HttpStatus.OK);
        } catch (EmptyResultDataAccessException error){
            return new ResponseEntity<>(new ResponseDTO("Não foi possível localizar o usuário!"), HttpStatus.BAD_REQUEST);
        }
        catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
