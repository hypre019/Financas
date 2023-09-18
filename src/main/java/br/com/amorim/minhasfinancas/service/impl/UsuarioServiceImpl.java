package br.com.amorim.minhasfinancas.service.impl;

import br.com.amorim.minhasfinancas.exception.ErroAutenticacao;
import br.com.amorim.minhasfinancas.exception.RegraNegocioException;
import br.com.amorim.minhasfinancas.model.entity.Usuario;
import br.com.amorim.minhasfinancas.model.repository.UsuarioRepository;
import br.com.amorim.minhasfinancas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        Optional<Usuario> usuario = repository.findByEmail(email);

        if(!usuario.isPresent())
            throw new ErroAutenticacao("Usuario Não Encontrado.");
        if(usuario.get().getSenha().equals(senha))
            throw new ErroAutenticacao("Senha inválida");


        return usuario.get();
    }

    @Transactional
    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);

    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if(existe)
            throw new RegraNegocioException("Já existe um usuario cadastrado com este email.");


    }
}
