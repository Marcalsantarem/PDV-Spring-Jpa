package com.marcalsantarem.pdvspringjpa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marcalsantarem.pdvspringjpa.entities.User;
import com.marcalsantarem.pdvspringjpa.entities.dto.UserDTO;
import com.marcalsantarem.pdvspringjpa.exceptions.NoItemException;
import com.marcalsantarem.pdvspringjpa.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(user ->
                new UserDTO(user.getId(), user.getName(), user.isEnabled())).collect(Collectors.toList());
    }

    public UserDTO save(UserDTO user){
        User userToSave = new User();
        userToSave.setEnabled(user.isEnabled());
        userToSave.setName(user.getName());
        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.isEnabled());
    }

    public UserDTO findById(long id){
        Optional<User> optional = userRepository.findById(id);

        if(!optional.isPresent()){
            throw new NoItemException("Usuário não encontrado!");
        }
        User user = optional.get();
        return new UserDTO(user.getId(), user.getName(), user.isEnabled());
    }

    public UserDTO update(UserDTO user){
        User userToSave = new User();
        userToSave.setEnabled(user.isEnabled());
        userToSave.setName(user.getName());
        userToSave.setId(user.getId());

        Optional<User> userToEdit = userRepository.findById(userToSave.getId());

        if(!userToEdit.isPresent()){
            throw new NoItemException("Usuário não encontrado!");
        }

        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.isEnabled());
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }
}
