package com.example.demo;

import org.springframework.validation.ObjectError;

import com.example.demo.dto.ErrorDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//Untuk rest
public class MapperHelper {
    private static <T> Object getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            var value = field.get(object);
            return value;
        } catch (Exception exception) {
        }
        return null;
    }

    public static <T> String getStringField(T object, String fieldName) {
        try {
            return getFieldValue(object, fieldName).toString();
        } catch (Exception exception) {
            return null;
        }
    }

    public static List<ErrorDTO> getErrors(List<ObjectError> errors) {
        var dto = new ArrayList<ErrorDTO>();
        for (var error : errors) {
            var fieldName = getStringField(error.getArguments()[0], "defaultMessage");
            fieldName = (fieldName.equals("")) ? "object" : fieldName;
            dto.add(new ErrorDTO(fieldName, error.getDefaultMessage()));
        }
        return dto;
    }
}
