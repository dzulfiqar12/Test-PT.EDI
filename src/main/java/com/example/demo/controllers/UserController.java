package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MapperHelper;
import com.example.demo.dto.GetUserDataDTO;
import com.example.demo.dto.InsertUserDataDTO;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = true) String id) {
        try {
            List<GetUserDataDTO> response = service.getDataUser(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi Runtime Error: " + e.getMessage());

        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody InsertUserDataDTO dto, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                var insertedEntity = service.setDataUser(dto);
                return ResponseEntity.status(201).body(insertedEntity);
            }
            return ResponseEntity.status(422).body(MapperHelper.getErrors(bindingResult.getAllErrors()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi Runtime Error: " + e.getMessage());

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Integer id) {
        try {
            service.delDataUser(id);
            return ResponseEntity.ok("Data dengan id " + id + " berhasil di delete");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi Runtime Error: " + e.getMessage());

        }

    }

}
