package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.PrimeraEntrega.Usuario;

import java.util.Optional;
import java.util.List;

public interface UsuarioService {
  Usuario registrarUsuario(Usuario usuario);
  Optional<Usuario> buscarPorEmail(String email);
  Optional<Usuario> buscarPorId(Long id); // 🔥 nuevo
  List<Usuario> listarUsuarios();
  Usuario actualizarPerfil(Long id, Usuario datos);
}



