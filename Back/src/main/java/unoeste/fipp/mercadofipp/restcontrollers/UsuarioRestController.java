package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("apis/usuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Usuario> usuarioList = usuarioService.getUsuario();
        if(usuarioList != null && !usuarioList.isEmpty())
            return ResponseEntity.ok(usuarioList);
        else
            return ResponseEntity.badRequest().body(new Erro("usuarios não encontrados!"));
    }

    //getID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id){
        Usuario usuario = usuarioService.getById(id);
        if(usuario != null)
            return ResponseEntity.ok(usuario);
        else
            return ResponseEntity.badRequest().body(new Erro("usuario não encontrado!"));
    }
    //adicionar
    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario)
    {
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);

        else
            return ResponseEntity.badRequest().body(new Erro("erro ao cadastrar novo usuario!"));
    }
    //alterar
    @PutMapping
    public ResponseEntity<Object> updUsuario(@RequestBody Usuario usuario)
    {
        Usuario novoUsuario = usuarioService.save(usuario);
        if(novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);

        else
            return ResponseEntity.badRequest().body(new Erro("erro ao alterar usuario!"));
    }
    //apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delUsuario(@PathVariable Long id){
        if(usuarioService.delete(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("erro ao deletar usuario"));
    }
}
