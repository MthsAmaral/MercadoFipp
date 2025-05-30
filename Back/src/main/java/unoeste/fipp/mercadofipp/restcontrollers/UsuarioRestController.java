package unoeste.fipp.mercadofipp.restcontrollers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> getUsuarioNome(@PathVariable(name = "nome") String nome){
        Usuario usuario = usuarioService.getByNome(nome);

        return ResponseEntity.ok(usuario);
        //else
            //return ResponseEntity.badRequest().body(new Erro("Não foi possível recuperar o Usuário com esse nome!!"));
    }

    @PostMapping("/logar")
    public ResponseEntity<Object> logar(@RequestParam String nome, @RequestParam String senha) {
        String token = usuarioService.autenticar(nome, senha);
        if (token != null) {
            Claims claims = JWTTokenProvider.getAllClaimsFromToken(token);
            Map<String, Object> json = new HashMap<>();
            json.put("id", claims.get("id"));
            json.put("nome", claims.get("nome"));
            json.put("token", token);
            json.put("nivel", claims.get("nivel"));
            return ResponseEntity.ok(json);
        }
        return ResponseEntity.badRequest().body(new Erro("Não foi possível logar no sistema!!"));
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
