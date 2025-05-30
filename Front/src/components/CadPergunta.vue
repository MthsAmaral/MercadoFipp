<template>
    <div class="container mt-4 p-3 w-50">
        <h2>Faça uma pergunta ao vendedor</h2>
        <div class="card bg-body-tertiary mt-4">
            <div class="card-header text-white">
                <strong>Pergunte ao vendedor!</strong>
            </div>
            <div class="card-body">
                <form @submit.prevent="this.gravar()">
                    <div class="mb-3">
                    <textarea class="form-control" id="pergunta" v-model="texto" rows="7"
                              placeholder="Este item ainda está disponível?" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-warning mx-2">Enviar pergunta</button>
                    <router-link class="text-decoration-none" :to="`/anuncio/${this.id}`">
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
    name: "CadPergunta",
    data() {
        return {
            id: this.$route.params.id,
            usuario: null,
            anuncio: null,
            texto: ""
        }
    },
    methods: {
        gravar() {
            const url = `http://localhost:8080/apis/anuncio/add-pergunta/${this.id}`;
            const data = {
                pergunta: this.texto
            }

            if (this.texto.length > 0)
                axios.post(url, data, {
                    headers: {
                        Authorization: JSON.parse(localStorage.getItem("usuario")).token
                    }
                })
                    .then(resposta => {
                        console.log(resposta);
                        this.$router.push(`/anuncio/${this.id}`);
                        //toast.success("Pergunta realizada com sucesso");
                    })
                    .catch(erro => {
                        console.log(erro);
                        toast.error("Erro ao enviar pergunta!");
                        this.resetarFormulario();
                    });
        },
        recuperarAnuncio() {
            const url = `http://localhost:8080/apis/anuncio/${this.id}`;
            axios.get(url)
                .then(resposta => {
                    console.log(resposta);
                    this.anuncio = resposta.data;
                    if (this.usuario && this.usuario.id === this.anuncio.usuario.id){
                        this.$router.push(`/anuncio/${this.id}`);
                        toast.warn("você não pode peguntar no seu próprio anuncio");
                    }
                        
                })
                .catch(erro => {
                    console.log(erro);
                })
        },
        resetarFormulario() {
            this.texto = "";
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

body {
  background: radial-gradient(circle at top, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  margin: 0;
}

.container {
  background-color: rgba(20, 30, 36, 0.8);
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 0 20px rgba(0, 255, 195, 0.2);
}

h2 {
  color: #ffffff;
  text-shadow: 0 0 6px #00ffc3;
  font-weight: 600;
  text-align: center;
  margin-bottom: 1.5rem;
}

.card {
  background-color: rgba(15, 25, 28, 0.9);
  border: 1px solid #00ffc3;
  box-shadow: 0 0 12px #00ffc3a2;
  border-radius: 12px;
}

.card-header {
  background-color: #00ffc3;
  color: #0c1c1e;
  font-weight: bold;
  font-size: 1.2rem;
  border-radius: 12px 12px 0 0;
}

.card-body {
  padding: 1.5rem;
}

textarea {
  background-color: rgba(255, 255, 255, 0.05);
  color: #cde4e5;
  border: 1px solid #00ffc3;
  resize: none;
}

textarea::placeholder {
  color: #a0cfcf;
}

.btn-warning {
  background-color: #ffc107;
  border: none;
  font-weight: 600;
  box-shadow: 0 0 6px #ffc107b7;
  transition: 0.3s;
}

.btn-warning:hover {
  background-color: #ffdb6f;
  color: #0c1c1e;
  box-shadow: 0 0 10px #ffc107;
}

.btn-secondary {
  background-color: #6c757d;
  border: none;
  font-weight: 600;
  transition: 0.3s;
}

.btn-secondary:hover {
  background-color: #8e99a3;
  color: #0c1c1e;
}
</style>
