package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.repositories.CategoriaRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    //retorna todas
    public List<Categoria> getAll(){
          return categoriaRepository.findAll();
    }
    //getID
    public Categoria getById(Long id){
        return categoriaRepository.findById(id).orElse(null);
    }
    //alterar e gravar
    public Categoria save(Categoria categoria){
        try{
            return categoriaRepository.save(categoria);
        }catch(Exception e) {
            return null;
        }
    }
    // apagar
    public boolean delete(Long id){
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        try {
            categoriaRepository.delete(categoria);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
