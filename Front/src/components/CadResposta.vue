<template>
    <div class="container mt-4 p-3 w-50">
        <h2>Responda à pergunta</h2>
        <div class="card bg-body-tertiary mt-4">
            <div class="card-header text-white">
                <strong>Pergunta do comprador</strong>
            </div>
            <div class="card-body">
                <p class="mb-3"><strong class="text-dark">Comprador:</strong> {{ texto }}</p>
                <form @submit.prevent="this.gravar()">
                    <div class="mb-3">
                        <label for="resposta" class="form-label">Sua resposta:</label>
                        <textarea class="form-control" id="resposta" rows="7" v-model="resposta"
                                  placeholder="Digite sua resposta para o comprador..." required></textarea>
                    </div>
                    <button type="submit" class="btn btn-warning mx-2">Enviar Resposta</button>
                    <router-link class="text-decoration-none" :to="`/anuncio/${this.idAnuncio}`">
                        <button class="btn btn-secondary mx-2">
                            Voltar
                        </button>
                    </router-link>
                </form>
            </div>
        </div>
    </div>
</template>

<script>

import axios from "axios";
import {toast} from "vue3-toastify";

export default {
    name: "CadResposta",
    data() {
        return {
            idAnuncio: this.$route.params.idA,
            idPergunta: this.$route.params.idP,
            texto: "",
            usuario: null,
            anuncio: null,
            resposta: ""
        }
    },
    methods: {
        gravar() {
            const url = `http://localhost:8080/apis/anuncio/add-resposta/${this.idPergunta}`
            const data = {
                resposta: this.resposta
            }

            if (this.resposta.length > 0)
                axios.post(url, data, {
                    headers: {
                        Authorization: JSON.parse(localStorage.getItem("usuario")).token
                    }
                })
                    .then(resposta => {
                        console.log(resposta);
                        localStorage.setItem("respostaRealizada", "true");
                        this.$router.push(`/anuncio/${this.idAnuncio}`);
                    })
                    .catch(erro => {
                        console.log(erro);
                        toast.error("Erro ao enviar resposta!", {
                            autoClose: 2000
                        });
                        this.resetarFormulario();
                    });
        },
        recuperarAnuncio() {
            const url = `http://localhost:8080/apis/anuncio/${this.idAnuncio}`;
            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.anuncio = resposta.data;
                    if (this.usuario && this.usuario.id !== this.anuncio.usuario.id)
                        this.$router.push("/nao-autorizado");
                    else
                        for (let pergunta of this.anuncio.perguntas)
                            if (pergunta.id === Number(this.idPergunta))
                                this.texto = pergunta.texto;
                })
                .catch(erro => {
                    console.log(erro);
                })
        },
        resetarFormulario() {
            this.resposta = "";
        }
    },
    mounted() {
        this.usuario = JSON.parse(localStorage.getItem("usuario"));
        this.recuperarAnuncio();
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

/* Fundo geral e fonte futurista */
body {
  background: radial-gradient(circle at top, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  margin: 0;
}

/* Container principal com responsividade */
.container {
  background-color: rgba(20, 30, 36, 0.8);
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 0 20px rgba(0, 255, 195, 0.2);
  max-width: 700px;
  width: 90%;
  margin: 2rem auto;
}

/* Animação suave de entrada */
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

.card-fade {
  animation: fadeInUp 0.6s ease-out;
}

/* Título */
h2 {
  color: #ffffff;
  text-shadow: 0 0 6px #00ffc3;
  font-weight: 600;
  text-align: center;
  margin-bottom: 1.5rem;
}

/* Cartões de pergunta/resposta */
.card {
  background-color: rgba(15, 25, 28, 0.9);
  border: 1px solid #00ffc3;
  box-shadow: 0 0 12px #00ffc3a2;
  border-radius: 12px;
}

/* Cabeçalho do cartão */
.card-header {
  background-color: #00ffc3;
  color: #0c1c1e;
  font-weight: bold;
  font-size: 1.2rem;
  border-radius: 12px 12px 0 0;
  padding: 0.75rem 1.25rem;
}

/* Corpo do cartão */
.card-body {
  padding: 1.5rem;
}

/* Textarea estilizado */
textarea {
  background-color: rgba(255, 255, 255, 0.05);
  color: #cde4e5;
  border: 1px solid #00ffc3;
  resize: none;
  width: 100%;
  padding: 0.5rem;
  border-radius: 8px;
}

textarea::placeholder {
  color: #a0cfcf;
}

/* Botões - Enviar */
.btn-warning {
  background-color: #ffc107;
  border: none;
  font-weight: 600;
  box-shadow: 0 0 6px #ffc107b7;
  transition: 0.3s;
  color: #0c1c1e;
}

.btn-warning:hover {
  background-color: #ffdb6f;
  color: #0c1c1e;
  box-shadow: 0 0 10px #ffc107;
}

/* Botões - Voltar */
.btn-secondary {
  background-color: #6c757d;
  border: none;
  font-weight: 600;
  transition: 0.3s;
  color: #fff;
}

.btn-secondary:hover {
  background-color: #8e99a3;
  color: #0c1c1e;
}
</style>