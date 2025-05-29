<template>
  <div class="container mt-4">
    <h1 class="mb-4">Cadastro de Anúncio</h1>

    <form @submit.prevent="gravarAnuncio" class="card p-4 shadow-sm">
      <!-- Campo oculto se precisar do ID -->
      <!-- <input type="hidden" v-model="id" /> -->

      <!-- Título -->
      <div class="mb-3">
        <label for="titulo" class="form-label">Título do Anúncio</label>
        <input type="text" class="form-control" id="titulo" v-model="titulo"  required>
      </div>

      <!-- Descrição -->
      <div class="mb-3">
        <label for="descricao" class="form-label">Descrição</label>
        <textarea class="form-control" id="descricao" v-model="descricao" rows="4" placeholder="Descreva o produto" required></textarea>
      </div>

      <!-- Categoria -->
      <div class="mb-3">
        <label for="categoria" class="form-label">Categoria</label>
        <select class="form-select" id="categoria" v-model="categoriaId" required>
          <option selected disabled>Selecione uma categoria</option>
          <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
            {{ categoria.nome }}
          </option>
        </select>
      </div>

      <!-- Data -->
      <div class="mb-3">
        <label for="data" class="form-label">Data do Anúncio</label>
        <input type="date" class="form-control" id="data" v-model="data" required>
      </div>

      <!-- Valor -->
      <div class="mb-3">
        <label for="valor" class="form-label">Valor</label>
        <div class="input-group">
          <span class="input-group-text">R$</span>
          <input type="number" class="form-control" id="valor" v-model="preco" required>
        </div>
      </div>

      <!-- Fotos -->
      <div class="mb-3">
        <label for="fotos" class="form-label">Escolha até 3 Fotos</label>
        <input type="file" class="form-control" id="fotos" accept="image/*" multiple @change="adicionarArquivos">
        <div class="form-text">Você pode adicionar até 3 imagens.</div>
      </div>

      <!-- Botões -->
      <div class="d-flex justify-content-end">
        <button type="submit" class="btn btn-primary me-2">
          {{ id ? 'Alterar' : 'Cadastrar' }}
        </button>
        <router-link to="/anuncios" class="btn btn-secondary">Cancelar</router-link>
      </div>
    </form>
  </div>
</template>


<script>
import axios from 'axios';
import { toast } from 'vue3-toastify';

export default {
  name: 'FormAnuncio',
  data() {
    return {
      titulo: "",
      descricao: "",
      preco: "",
      data: null,
      categoriaId: 0,
      usuario: null,
      perguntas: [],
      fotos: [],
      categorias: []
    };
  },

  methods: {
    buscarCategorias() {
      const url = "http://localhost:8080/apis/categoria";
      axios.get(url, {
        headers: {
          Authorization: JSON.parse(localStorage.getItem("usuario")).token
        }
      })
        .then(resposta => {
          console.log(resposta);
          this.categorias = resposta.data;
        })
        .catch(erro => {
          console.log(erro);
          toast.error("Erro ao carregar categorias!");
        });
    },
    gravarAnuncio() {
      const url = "http://localhost:8080/apis/anuncio";
      const formData = new FormData();

      const anuncio = {
        titulo: this.titulo,
        data: this.data,
        descricao: this.descricao,
        preco: this.preco,
        categoria: {
          id: this.categoriaId
        },
        usuario: this.usuario,
        perguntas: []
      };

      formData.append("anuncio", new Blob([JSON.stringify(anuncio)], { type: "application/json" }));
      this.fotos.forEach((foto) => {
        formData.append("fotos", foto);
      });

      axios.post(url, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: JSON.parse(localStorage.getItem("usuario")).token
        }
      })
        .then(resposta => {
          console.log(resposta.data);
          this.$router.push("/anuncios");
          alert.success("anúncio cadastrado com sucesso!");
        })
        .catch(erro => {
          console.error(erro);
          toast.error("Erro ao cadastrar anúncio!");
        });
    },
    adicionarArquivos(event) {
      this.fotos = Array.from(event.target.files).slice(0, 3);
    }
  },
  mounted() {
    this.buscarCategorias()
    this.usuario = JSON.parse(localStorage.getItem("usuario"));
    if (!this.usuario)
      this.$router.push("/");
  }

};
</script>
