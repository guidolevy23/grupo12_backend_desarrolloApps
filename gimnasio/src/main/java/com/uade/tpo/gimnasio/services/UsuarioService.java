package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.Usuario;

public interface UsuarioService {
  /** Obtiene el perfil del usuario autenticado. */
  Usuario getPerfil(Long userId);

  /** Actualiza nombre/foto del perfil. */
  Usuario updatePerfil(Long userId, String nombre, String fotoUrl);
}
