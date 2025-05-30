<template>
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header text-white">
                <h4 class="mb-0">Perguntas e Respostas</h4>
            </div>
            <ul v-if="perguntas.length===0" class="list-group list-group-flush">
                <li class="list-group-item bg-body-tertiary">
                    <p class="mt-2">Nenhuma pergunta foi feita.</p>
                </li>
            </ul>
            <ul v-else class="list-group list-group-flush">
                <li v-for="pergunta in perguntas" class="list-group-item bg-body-tertiary">
                    <div class="fw-bold text-dark">Pergunta:</div>
                    <div class="mb-2">{{ pergunta.texto }}</div>
                    <div v-if="pergunta.resposta" class="ms-3">
                        <span class="fw-semibold text-success">Resposta do vendedor:</span><br>
                        <p>{{ pergunta.resposta }}</p>
                    </div>
                    <div v-else>
                        <div v-if="this.usuario && vendedor === this.usuario.id">
                            <router-link class="text-decoration-none"
                                         :to="`/responder/${idAnuncio}/${pergunta.id}`">
                                <button class="btn btn-secondary m-2" type="button">Responder</button>
                            </router-link>
                        </div>
                        <div>
                            <span class="text-secondary text-truncate">O vendedor ainda não respondeu a pergunta.</span><br>
                        </div>
                    </div>
                </li>
            </ul>
            <ul v-if="this.usuario && this.usuario.id !== vendedor" class="list-group list-group-flush">
                <li class="list-group-item bg-body-tertiary">
                    <router-link class="text-decoration-none" :to="`/perguntar/${idAnuncio}`">
                        <button class="btn btn-warning mb-2" type="button">Faça uma pergunta!</button>
                    </router-link>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: "Perguntas",
    props: {
        perguntas: Array,
        idAnuncio: Number,
        vendedor: Number
    },
    data() {
        return {
            usuario: null
        }
    },
    mounted() {
        this.usuario = JSON.parse(localStorage.getItem("usuario"));
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background-color: #0c1c1e;
  font-family: 'Orbitron', sans-serif;
  color: #e0f7fa;
}

/* Container principal */
.container {
  background: rgba(20, 30, 36, 0.95);
  border-radius: 16px;
  padding: 2rem;
  box-shadow: 0 0 20px rgba(0, 255, 195, 0.15);
  animation: fadeInUp 0.6s ease-out;
}

/* Card */
.card {
  background-color: rgba(30, 45, 55, 0.9);
  border: 1px solid #00ffc3;
  border-radius: 16px;
  box-shadow: 0 0 15px #00ffc33f;
}

/* Cabeçalho */
.card-header {
  background-color: #00ffc3;
  color: #0c1c1e;
  font-weight: 600;
  font-family: 'Orbitron', sans-serif;
  text-shadow: 0 0 8px #0c1c1e6a;
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;
}

/* Lista */
.list-group-item {
  background-color: #101d21 !important;
  color: #e0f7fa;
  border-bottom: 1px solid #00ffc344;
}

/* Texto da pergunta */
.fw-bold.text-dark {
  color: #00ffc3 !important;
  font-weight: 600;
}

/* Resposta */
.text-success {
  color: #22ff95 !important;
  text-shadow: 0 0 6px #22ff95a2;
}

/* Texto informativo */
.text-secondary {
  color: #b0bec5 !important;
}

/* Botões */
.btn-warning {
  background-color: #ffca28;
  color: #0c1c1e;
  font-weight: bold;
  border: none;
  box-shadow: 0 0 8px #ffca2866;
}

.btn-warning:hover {
  background-color: #ffd54f;
  box-shadow: 0 0 12px #ffd54f;
}

.btn-secondary {
  background-color: #37474f;
  color: #fff;
  border: none;
  box-shadow: 0 0 6px #00ffc3;
}

.btn-secondary:hover {
  background-color: #455a64;
  box-shadow: 0 0 10px #00ffc3;
}

/* Animação */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>
