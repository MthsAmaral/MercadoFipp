<template>
  <div class="container my-4 p-4 rounded bg-light shadow">
    <div v-if="anuncio !== null">
      <div class="row g-4">
        <!-- Informações -->
        <div class="col-md-6 p-4 rounded bg-white shadow">
          <h2 class="mb-3 text-primary fw-bold">{{ anuncio.titulo }}</h2>
          <h2 class="text-success mb-3">R$ {{ parseFloat(anuncio.preco).toFixed(2) }}</h2>
          <p class="text-secondary"><strong>Descrição:</strong></p>
          <p class="mb-1 text-dark">{{ anuncio.descricao }}.</p>
          <p class="mb-1 text-dark"><strong>Categoria:</strong> {{ anuncio.categoria.nome }}</p>
          <p class="text-muted"><strong>Data da postagem:</strong> {{ formatarData(anuncio.data) }}</p>

          <hr />
          <div class="d-flex flex-column align-items-center">
            <button class="btn btn-success w-75 mb-2">Comprar</button>
            <button class="btn btn-outline-primary w-75">Adicionar ao carrinho</button>
          </div>
          <p class="mt-3 text-secondary">
            Vendido por {{ anuncio.usuario.nome }}
          </p>
        </div>

        <!-- Carrossel -->
        <div v-if="anuncio.fotos.length === 0"
          class="col-md-6 d-flex align-items-center justify-content-center bg-white rounded shadow">
          <img class="img-fluid rounded" alt="Imagem Indisponível" />
        </div>
        <div v-else class="col-md-6 bg-white rounded shadow p-3">
          <div id="carouselExampleDark" class="carousel carousel-dark slide">
            <div class="carousel-indicators">
              <button v-for="(foto, index) in anuncio.fotos" :key="foto.id" :data-bs-target="'#carouselExampleDark'"
                :data-bs-slide-to="index" :class="{ active: index === 0 }"
                :aria-label="'Slide ' + (index + 1)"></button>
            </div>
            <div class="carousel-inner rounded">
              <div v-for="(foto, index) in anuncio.fotos" :key="foto.id"
                :class="{ 'carousel-item': true, active: index === 0 }" data-bs-interval="10000">
                <img :src="foto.img64" class="d-block w-100 rounded" alt="Imagem" />
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark"
              data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Anterior</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark"
              data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Próximo</span>
            </button>
          </div>
        </div>
      </div>

      <!-- Perguntas -->
      <div class="mt-5 p-3 bg-white rounded shadow">
        <Perguntas :vendedor="anuncio.usuario.id" :perguntas="anuncio.perguntas" :idAnuncio="anuncio.id" />
      </div>
    </div>

    <!-- Erro -->
    <div v-else class="alert alert-danger mt-3" role="alert">
      Erro ao carregar anúncio!
    </div>

    <!-- Botão Voltar -->
    <div class="mt-4">
      <router-link class="text-decoration-none" :to="logado ? '/anuncios' : '/'">
        <button class="btn btn-secondary" type="button">Voltar</button>
      </router-link>
    </div>
  </div>
</template>



<script>
import { toast } from 'vue3-toastify';
import axios from "axios";
import Perguntas from './Perguntas.vue'

export default {
  name: "DetalheAnuncio",
  components: {
    Perguntas
  },
  data() {
    return {
      id: this.$route.params.id,
      anuncio: null,
      logado: true
    };
  },
  methods: {
    carregarAnuncio() {
      const url = `http://localhost:8080/apis/anuncio/${this.id}`;
      axios
        .get(url)
        .then((resposta) => {
          this.anuncio = resposta.data;
          //console.log(resposta.data);
          if (this.anuncio) document.title += ` - ${this.anuncio.titulo}`;
        })
        .catch((erro) => {
          console.log(erro);
          toast.error("Erro ao carregar anúncio!");
        });
    },
    formatarData(dataISO) {
      if (!dataISO) return '';
      const data = new Date(dataISO);
      return data.toLocaleDateString('pt-BR');
    }
  },
  mounted() {
    this.carregarAnuncio();
    if (localStorage.getItem("perguntaRealizada") === "true") {
      toast.success("Sua pergunta foi enviada! Aguarde a resposta do vendedor!");
      localStorage.removeItem("perguntaRealizada");
    } else if (localStorage.getItem("respostaRealizada") === "true") {
      toast.success("Sua resposta foi enviada! Aguarde o contato do comprador!");
      localStorage.removeItem("respostaRealizada");
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background: radial-gradient(circle at top, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  margin: 0;
}

/* Container principal */
.container {
  background-color: rgba(20, 30, 36, 0.85);
  border-radius: 16px;
  box-shadow: 0 0 25px rgba(0, 255, 195, 0.15);
  animation: fadeInUp 0.6s ease-out;
}

/* Animação */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(15px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* Título e preço */
h2 {
  font-weight: 600;
  text-shadow: 0 0 6px #00ffc3;
}

h2.text-primary {
  color: #00ffc3 !important;
}

h2.text-success {
  color: #22ff95 !important;
  text-shadow: 0 0 4px #22ff95b0;
}

/* Texto */
p {
  font-size: 0.95rem;
}

/* Informações */
.bg-white {
  background-color: rgba(255, 255, 255, 0.05) !important;
  border: 1px solid #00ffc3;
  border-radius: 12px;
  box-shadow: 0 0 12px #00ffc362;
  color: #cde4e5;
}

/* Botões */
button.btn-success {
  background-color: #22ff95;
  color: #0c1c1e;
  font-weight: bold;
  border: none;
  transition: 0.3s;
  box-shadow: 0 0 8px #22ff95a4;
}

button.btn-success:hover {
  background-color: #5affb3;
  box-shadow: 0 0 12px #22ff95;
}

button.btn-outline-primary {
  color: #00ffc3;
  border: 1px solid #00ffc3;
  font-weight: bold;
  transition: 0.3s;
}

button.btn-outline-primary:hover {
  background-color: #00ffc3;
  color: #0c1c1e;
  box-shadow: 0 0 12px #00ffc3;
}

button.btn-secondary {
  background-color: #6c757d;
  color: #fff;
  font-weight: 600;
  border: none;
  transition: 0.3s;
}

button.btn-secondary:hover {
  background-color: #8e99a3;
  color: #0c1c1e;
}

/* Carrossel */
.carousel-inner img {
  border-radius: 10px;
  border: 2px solid #00ffc3;
  box-shadow: 0 0 12px #00ffc380;
}

/* Indicadores */
.carousel-indicators [data-bs-target] {
  background-color: #00ffc3;
}

.carousel-control-prev-icon,
.carousel-control-next-icon {
  filter: brightness(0) saturate(100%) invert(86%) sepia(32%) saturate(2872%) hue-rotate(115deg) brightness(102%) contrast(101%);
}

/* Alertas */
.alert-danger {
  background-color: rgba(255, 0, 0, 0.1);
  color: #ff6a6a;
  border: 1px solid #ff4f4f;
  font-weight: 600;
}
</style>
