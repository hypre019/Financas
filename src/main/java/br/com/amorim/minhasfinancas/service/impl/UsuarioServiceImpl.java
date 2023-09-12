package br.com.amorim.minhasfinancas.service.impl;

import br.com.amorim.minhasfinancas.exception.RegraNegocioException;
import br.com.amorim.minhasfinancas.model.entity.Usuario;
import br.com.amorim.minhasfinancas.model.repository.UsuarioRepository;
import br.com.amorim.minhasfinancas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository){
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if(existe)
            throw new RegraNegocioException("Já existe um usuario cadastrado com este email.");


    }
}
