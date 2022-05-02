package com.kruger.inventario.service;

import com.kruger.inventario.model.Dto.DtoInfo;
import com.kruger.inventario.model.Usuario;
import com.kruger.inventario.repository.IUsuarioRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    DtoInfo dtoInfo = new DtoInfo();

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findUsuarioByCedula(String cedula) {
        Usuario usuario = Usuario.builder().build();
        try {
            usuario = usuarioRepository.findByCedula(cedula);
        }catch (Exception e){
            dtoInfo.setMensaje("Error al buscar el usuario");
        }

        if(usuario== null) usuario = Usuario.builder().idUsuario(0).build();
        return usuario;
    }

    @Override
    public DtoInfo crearUsuarioEmpleado(Usuario usuario) {
            var currentUsuario = findUsuarioByCedula(usuario.getCedula());

            if(currentUsuario.getIdUsuario()>0){
                dtoInfo.setMensaje("El usuario ya existe");
                return dtoInfo;
            }else {
                usuarioRepository.save(usuario);
                dtoInfo.setExito(true);
                dtoInfo.setMensaje("Usuario creado");
                return dtoInfo;
            }
    }

    @Override
    public DtoInfo updateEmpleado(Usuario usuario) {
        var currentUsuario = findUsuarioByCedula(usuario.getCedula());
        if(currentUsuario.getIdUsuario()>0){
            currentUsuario.setNombres(usuario.getNombres());
            currentUsuario.setApellidos(usuario.getApellidos());
            currentUsuario.setTelefono(usuario.getTelefono());
            currentUsuario.setCelular(usuario.getCelular());
            currentUsuario.setDireccion(usuario.getDireccion());
            currentUsuario.setCedula(usuario.getCedula());
            currentUsuario.setEmail(usuario.getEmail());
            currentUsuario.setUsername(usuario.getUsername());
            currentUsuario.setPassword(usuario.getPassword());
            currentUsuario.setEstado(usuario.isEstado());

            usuarioRepository.save(currentUsuario);

            dtoInfo.setExito(true);
            dtoInfo.setMensaje("Usuario actualizado");
        }else {
            dtoInfo.setMensaje("El usuario no existe");
        }
        return dtoInfo;
    }

    @Override
    public DtoInfo deleteEmpleado(Usuario usuario) {
        var currentUsuario = findUsuarioByCedula(usuario.getCedula());
        if(currentUsuario.getIdUsuario()>0){
            currentUsuario.setEstado(false);
            usuarioRepository.save(currentUsuario);
            dtoInfo.setExito(true);
            dtoInfo.setMensaje("Usuario eliminado");
        }else {
            dtoInfo.setMensaje("El usuario no existe");
        }
        return dtoInfo;
    }
}
