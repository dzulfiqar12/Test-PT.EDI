package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertUserDataDTO {
     @NotNull(message = "Id tidak boleh kosong")
    private Integer userId;
     @NotBlank(message = "Nama lengkap tidak boleh kosong")
    private String namaLengkap;
     @NotBlank(message = "Username tidak boleh kosong")
    private String username;
     @NotBlank(message = "Password tidak boleh kosong")
    private String password;
     @NotNull(message = "Status tidak boleh  kosong")
    private Character status;
}
