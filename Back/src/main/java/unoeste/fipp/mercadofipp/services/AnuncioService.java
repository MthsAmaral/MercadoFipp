package unoeste.fipp.mercadofipp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;

import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;


    public List<Anuncio> getAll(){
        return anuncioRepository.findAll();
    }

    public Anuncio add(Anuncio anuncio) {
        Anuncio novoAnuncio = anuncioRepository.save(anuncio);

        List<Foto> fotos = anuncio.getFotos();
        if (fotos != null) {
            for (Foto foto : fotos) {
                anuncioRepository.addFoto(foto.getFoto(), novoAnuncio.getId());
            }
        }

        return novoAnuncio;
    }

    public boolean addPergunta(Long id_anuncio, String texto){
        try {
            anuncioRepository.addPergunta(texto,id_anuncio);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean addResposta(Long id_pergunta, String resp){
        try{
            anuncioRepository.updResposta(resp,id_pergunta);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
