package br.com.amorim.minhasfinancas.api.resource;

import br.com.amorim.minhasfinancas.api.dto.UsuarioDTO;
import br.com.amorim.minhasfinancas.exception.ErroAutenticacao;
import br.com.amorim.minhasfinancas.exception.RegraNegocioException;
import br.com.amorim.minhasfinancas.model.entity.Usuario;
import br.com.amorim.minhasfinancas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {


    private final UsuarioService service;

    public UsuarioResource(UsuarioService service){
        this.service = service;
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar( @RequestBody UsuarioDTO dto) {
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        }catch (ErroAutenticacao e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @PostMapping
    public ResponseEntity salvar(@RequestBody @NotNull UsuarioDTO dto){

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        try{
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }catch (RegraNegocioException e){
                return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
