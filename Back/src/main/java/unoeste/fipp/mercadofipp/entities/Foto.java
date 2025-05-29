package unoeste.fipp.mercadofipp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="foto_anuncio")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fot_id")
    private Long id;

    @Column(name = "fot_file")
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name="anu_id")
    private Anuncio anuncio;


    //construtores
    public Foto() {
        this(0L,null,null);
    }

    public Foto(Long id, byte[] foto, Anuncio anuncio) {
        this.id = id;
        this.foto = foto;
        this.anuncio = anuncio;
    }


    //gets e sets (ANUNCIO NÃO TEM)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
