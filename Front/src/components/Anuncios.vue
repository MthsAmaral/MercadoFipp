<template>
  <div class="container mt-4">
    <h2 class="mb-4 text-center">Todos Anuncios</h2>

    <form class="d-flex mx-auto" role="search" style="max-width: 400px; width: 100%;">
      <input class="form-control me-2" type="search" placeholder="Buscar por nome do produto" aria-label="Search">
      <button class="btn btn-outline-light" type="submit">Buscar</button>
    </form>

    <div class="row">
      <div v-for="anuncio in anuncios" :key="anuncio.id" class="col-md-4 mb-4">
        <router-link :to="`/anuncio/${anuncio.id}`" class="text-decoration-none text-dark">
          <div class="card h-100">

            <img :src="getImagem(anuncio)" class="card-img-top" alt="Imagem do anúncio"
              style="height: 200px; object-fit: cover;">

            <div class="card-body">
              <h5 class="card-title">{{ anuncio.titulo }}</h5>
              <p class="card-text">R$ {{ parseFloat(anuncio.preco).toFixed(2) }}</p>
            </div>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { toast } from 'vue3-toastify';

export default {
  name: 'Anuncios',
  data() {
    return {
      anuncios: []
    };
  },
  methods: {
    carregarAnuncios() {
      axios.get("http://localhost:8080/apis/anuncio")
        .then(response => {
          this.anuncios = response.data.reverse();
          //console.log(this.anuncios);
        })
        .catch(erro => {
          toast.error("Erro ao buscar anúncios");
          console.error("Erro ao buscar anúncios:", erro);
        });
    },
    getImagem(anuncio) {
      if (anuncio.fotos && anuncio.fotos.length > 0) {
        // Monta a imagem base64 completa
        return `data:image/${anuncio.fotos[0].extensao};base64,${anuncio.fotos[0].arquivo}`;
      }
      // Imagem padrão caso não tenha
      return 'https://via.placeholder.com/400x200?text=Sem+Imagem';
    }
  }
  ,
  mounted() {
    this.carregarAnuncios();
  }
}
</script>

<style scoped>
.card-title {
  font-size: 1.2rem;
  font-weight: bold;
}
</style>