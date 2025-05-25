package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;
import unoeste.fipp.mercadofipp.security.JWTTokenProvider;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    //inserir
    public List<Usuario> getUsuario(){
        return usuarioRepository.findAll();
    }
    //getID
    public Usuario getById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    //alterar
    public Usuario save(Usuario usuario){
        try{
            Usuario novoUsuario = usuarioRepository.save(usuario);
            return novoUsuario;
        }catch(Exception e){
            return null;
        }
    }
    //apagar
    public boolean delete(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        try{
            usuarioRepository.delete(usuario);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //buscar por nome
    public Usuario getByNome(String nome){
        return usuarioRepository.findByNome(nome);
    }

    public String autenticar(String nome, String senha) {
        Usuario consultado = getByNameSenha(nome, senha);
        if (consultado != null)
            return JWTTokenProvider.getToken(consultado.getNome(), consultado.getId(), consultado.getNome(), "" + consultado.getNivel());
        return null;
    }

    private Usuario getByNameSenha(String nome, String senha) {
        Usuario usuario = usuarioRepository.findByNome(nome);
        if (usuario != null)
            if (usuario.getSenha().equals(senha))
                return usuario;
        return null;
    }


}
