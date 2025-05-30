<template>
  <div class="container mt-4">
    <h2 class="mb-4 text-center">Anuncios recentes</h2>
    <div class="row">
      <div 
        v-for="anuncio in anuncios" 
        :key="anuncio.id" 
        class="col-md-4 mb-4"
      >
        <router-link 
          :to="`/anuncio/${anuncio.id}`" 
          class="text-decoration-none text-dark"
        >
          <div class="card h-100">
            <img :src="getImagem(anuncio)" class="card-img-top" alt="Imagem do anúncio"
              style="height: 200px; object-fit: contain; background-color: #f8f9fa;">

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
  name: 'Home',
  data() {
    return {
      anuncios: []
    };
  },
  methods: {
    carregarAnuncios() {
        axios.get("http://localhost:8080/apis/anuncio")
        .then(response =>{
            //console.log(response.data);
            // Inverte a lista e pega os 5 primeiros (últimos cadastrados)
            let listaInvertida = response.data.reverse();
            this.anuncios = listaInvertida.slice(0, 5);
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
      //return 'https://via.placeholder.com/400x200?text=Sem+Imagem';
    }
  },
  mounted() {
    this.carregarAnuncios();
  }
}
</script>

<style >
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600;700&display=swap');

.container {
    background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
    padding: 2rem;
    border-radius: 12px;
    box-shadow: 0 0 20px rgba(0, 255, 195, 0.2);
}

h2 {
    color: #00ffc3;
    text-shadow: 0 0 8px #00ffc3;
    font-family: 'Orbitron', sans-serif;
    font-weight: 700;
}

.card {
    background: rgba(15, 32, 39, 0.85);
    border: 1px solid #00ffc3;
    border-radius: 12px;
    box-shadow: 0 0 15px #00ffc36e;
    backdrop-filter: blur(6px);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    overflow: hidden;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 0 25px #00ffc3;
}

.card-img-top {
    height: 200px;
    width: 100%;
    object-fit: cover;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
}

.card-title {
    color: #ffffff;
    font-family: 'Orbitron', sans-serif;
    font-weight: 600;
    text-shadow: 0 0 5px #00ffc3;
}

.card-text {
    color: #cde4e5;
    font-family: 'Orbitron', sans-serif;
}

.router-link {
    text-decoration: none;
    color: #cde4e5;
    transition: color 0.3s ease;
}

.router-link:hover {
    color: #00ffc3;
}

@media (max-width: 768px) {
    .card-img-top {
        height: 150px;
    }
}
</style>