package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GetUserDataDTO;
import com.example.demo.dto.InsertUserDataDTO;
import com.example.demo.entities.User;
import com.example.demo.repos.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Status A berarti user aktif, jika N berarti tidak aktif

    // get user
    // service untuk mendapatkan semua user dan berdasarkan id nya
    public List<GetUserDataDTO> getDataUser(String userId) {
        List<GetUserDataDTO> result = new ArrayList<>();
        if (userId.equalsIgnoreCase("all")) {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                // hanya mengambil user yang memiliki status aktif
                if (user.getStatus() == 'A') {
                    result.add(new GetUserDataDTO(
                            user.getUserId(),
                            user.getNamaLengkap(),
                            user.getUsername()));
                }

            }
        } else {
            var userEntity = userRepository.findById(Integer.parseInt(userId)).get();
            // hanya mengambil user yang memiliki status aktif
            if (userEntity.getStatus() == 'A') {
                result.add(
                        new GetUserDataDTO(userEntity.getUserId(), userEntity.getNamaLengkap(),
                                userEntity.getUsername()));
            }

        }
        return result;

    }

    // service untuk melakukan insert sekaligus update
    // jika userid sudah pernah ada maka akan update jika belum ada maka akan insert
    public User setDataUser(InsertUserDataDTO dto) {
        // hashing password dengan menggunakan algoritma bcrypt
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        // mapping dari dto ke entity
        var entity = new User(
                dto.getUserId(),
                dto.getNamaLengkap(),
                dto.getUsername(),
                hashedPassword,
                dto.getStatus());
        return userRepository.save(entity);
    }

    // soft delete user
    // merubah status user yang tadi aktif menjadi tidak aktif
    public User delDataUser(Integer userId) {
        var deleteEntity = userRepository.findById(userId).get();

        deleteEntity.setStatus('N');
        return userRepository.save(deleteEntity);
    }
}
