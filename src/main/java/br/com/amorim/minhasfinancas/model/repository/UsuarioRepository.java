package br.com.amorim.minhasfinancas.model.repository;

import br.com.amorim.minhasfinancas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


      boolean existsByEmail(String email);
}