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
            <img 
              :src="anuncio.foto" 
              class="card-img-top" 
              alt="Imagem do anúncio" 
              style="height: 200px; object-fit: cover;"
            >
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
    }
  },
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