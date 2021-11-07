package com.challenge.java.mapper;

import com.challenge.java.dto.UserRegisterDTO;
import com.challenge.java.model.Usuario;
import org.mapstruct.Mapper;

/**
 * @author Alexis
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuario(UserRegisterDTO dto);
}
