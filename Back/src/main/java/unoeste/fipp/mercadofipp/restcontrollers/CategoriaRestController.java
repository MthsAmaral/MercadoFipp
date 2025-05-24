package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.CategoriaService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("apis/categoria")
public class CategoriaRestController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Categoria> categoriaList = categoriaService.getCategoria();
        if(categoriaList != null && !categoriaList.isEmpty())
            return ResponseEntity.ok(categoriaList);
        else
            return ResponseEntity.badRequest().body(new Erro("categorias não encontradas!"));
    }
    //getID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(name = "id")Long id){
        Categoria categoria = categoriaService.getById(id);
        if (categoria != null)
        {
            return ResponseEntity.ok(categoria);
        }
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar nova categoria!"));
    }
    //adicionar
    @PostMapping
    public ResponseEntity<Object> addCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoria = categoriaService.save(categoria);
        if (novaCategoria != null)
            return ResponseEntity.ok(novaCategoria);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar nova categoria!"));
    }
    //alterar
    @PutMapping
    public ResponseEntity<Object> updCategoria(@RequestBody Categoria categoria){
        Categoria novaCategoia = categoriaService.save(categoria);
        if (novaCategoia!= null)
            return ResponseEntity.ok(novaCategoia);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar categoria!"));
    }
    //apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id){
        if (categoriaService.delete(id))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao excluir categoria!"));
    }






}





















