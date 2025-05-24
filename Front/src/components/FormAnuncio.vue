<!--Testar fiz mais ou menos semelhante aos de categoria e usuario-->
<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <div v-if="formOn">
      <form @submit.prevent="gravar">
        <label for="idanu">ID</label>
        <input type="text" id="idanu" v-model="id" disabled placeholder="ID do Anúncio">

        <label for="titleanu">Título</label>
        <input type="text" id="titleanu" v-model="titulo" placeholder="Título do anúncio...">

        <label for="descanu">Descrição</label>
        <input type="text" id="descanu" v-model="descricao" placeholder="Descrição do produto...">

        <label for="precoanu">Preço</label>
        <input type="number" id="precoanu" v-model="preco" placeholder="Valor do produto...">

        <label for="idfot">Foto</label>
        <input type="file" id="idfot" @change="onFileChange" accept="image/*">

        <input type="submit" value="Cadastrar">
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FormAnuncio',
  props: {
    msg: String
  },
  data() {
    return {
      id: 0,
      titulo: "",
      descricao: "",
      preco: null,
      foto: null,
      formOn: false,
      anuncio: []
    };
  },
  methods: {
    mostrarForm(flag) {
      this.formOn = flag;
    },
    onFileChange(event) {
      const file = event.target.files[0];
      this.foto = file;
    },
    gravar() {
      const formData = new FormData();
      if (this.id) formData.append('id', this.id);
      formData.append('titulo', this.titulo);
      formData.append('descricao', this.descricao);
      formData.append('preco', this.preco);

      if (this.foto) {
        formData.append('foto', this.foto);
      }

      axios.post('http://localhost:8080/apis/anuncio', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        alert('Anúncio salvo com sucesso!');
        this.carregarDados();
        this.mostrarForm(false);
      })
      .catch(error => {
        alert('Erro ao salvar o anúncio: ' + error);
      });
    },
    carregarDados() {
      axios.get("http://localhost:8080/apis/anuncio")
        .then(result => {
          this.anuncio = result.data;
        })
        .catch(error => alert(error));
    }
  },
  mounted() {
    this.carregarDados();
  }
};
</script>

<style>

</style>