<template>
  <div class="container mt-4">
    <h2 class="mb-4 text-center">Todos Anúncios</h2>

    <form class="d-flex mx-auto" role="search" style="max-width: 400px; width: 100%;">
      <input class="form-control me-2 search-form" type="search" placeholder="Buscar por nome do produto" aria-label="Search">
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
    }
  }
  ,
  mounted() {
    this.carregarAnuncios();
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  min-height: 100vh;
  margin: 0;
}

.container {
  max-width: 1200px;
  margin: auto;
}

h2 {
  color: #ffffff;
  text-shadow: 0 0 8px #00ffc3;
  font-weight: 600;
}

.search-form {
  max-width: 500px;
  width: 100%;
  margin: 30px auto;
  padding: 10px;
  border-radius: 10px;
  background-color: rgba(0, 255, 195, 0.05);
  box-shadow: 0 0 12px #00ffc3a2;
  color: #cde4e5;
}

form input {
  background-color: rgba(255, 255, 255, 0.08);
  color: #cde4e5;
  border: 1px solid #00ffc3;
  border-radius: 8px;
  padding: 8px;
}

form input::placeholder {
  color: #a0cfcf;
}

form .btn-outline-light {
  border: 1px solid #00ffc3;
  color: #00ffc3;
  border-radius: 8px;
  padding: 8px 16px;
  transition: 0.3s;
}

form .btn-outline-light:hover {
  background-color: #00ffc3;
  color: #0c1c1e;
}

.card {
  background-color: rgba(10, 20, 24, 0.9);
  border: 1px solid #00ffc3;
  border-radius: 12px;
  box-shadow: 0 0 12px #00ffc3a2;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 0 16px #00ffc3;
}

.card-title {
  color: #ffffff;
  font-weight: 600;
}

.card-text {
  color: #a0f7ec;
  font-size: 1.1rem;
}

a.text-dark {
  color: inherit;
}

a.text-dark:hover {
  color: #00ffc3;
  text-shadow: 0 0 4px #00ffc3;
}
</style>
