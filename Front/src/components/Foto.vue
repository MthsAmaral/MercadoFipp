<template>
   <!--Formulario para enviar a foto para o componte do anuncio , passando o id do anuncio para enviar a foto
   gravar e apagar, colocar no maximo 3 fotos por anuncio--> 
  <div class="hello">
    <h1>{{ msg }}</h1>

    <div v-if="formOn">
      <form @submit.prevent="gravarFoto">
        <label for="idfot">ID do Anúncio</label>
        <input type="number" id="idfot" v-model="idanun" placeholder="ID do anúncio" required> <!--testar para ver se da certo-->

        <label for="foto">Selecionar Foto</label>
        <input type="file" id="foto" @change="onFileChange" accept="image/*" required>

        <div v-if="previewUrl">
          <p>Previa:</p>
          <img :src="previewUrl" alt="preview" style="max-width: 200px;">
        </div>

        <input type="submit" value="Cadastrar">
      </form>
    </div>

    <div class="botao-container">
      <button class="nova-foto-btn" @click="novaFoto">Nova Foto</button>
    </div>

    <div>
      <table id="customers">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome do Arquivo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fot in fotos" :key="fot.id">
            <td>{{ fot.id }}</td>
            <td>{{ fot.nome }}</td>
            <td>
              <button @click="apagar(fot.id, fot.nome)">Apagar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'FormFoto',
  props: {
    msg: String
  },
  data() {
    return {
      idAnuncio: 0,
      foto: null,
      previewUrl: null,
      formOn: false,
      fotos: []
    };
  },
  methods: {
    novaFoto() {
      this.formOn = true;
      this.previewUrl = null;
      this.foto = null;
    },
    onFileChange(event) {
      const file = event.target.files[0];
      this.foto = file;

      if (file) {
        const reader = new FileReader();
        reader.onload = e => {
          this.previewUrl = e.target.result;
        };
        reader.readAsDataURL(file);
      } else {
        this.previewUrl = null;
      }
    },
    gravarFoto() {
      const fotosDoAnuncio = this.fotos.filter(f => f.idanun === this.idanun); //validar no maximo 3 fotos
      if (fotosDoAnuncio.length >= 3) {
        alert("Você só pode adicionar até 3 fotos por anúncio.");
        return;
      }

      const formData = new FormData();
      formData.append('id', this.idanun); 
      formData.append('foto', this.foto);

      axios.post("http://localhost:8080/apis/foto", formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(() => {
        alert("Foto enviada com sucesso!");
        this.formOn = false;
        this.carregarFotos();
      })
      .catch(error => {
        alert("Erro ao enviar foto: " + error);
      });
    },
    apagar(id, nome) {
      if (confirm(`Deseja apagar a foto ${nome}?`)) {
        axios.delete(`http://localhost:8080/apis/foto/${id}`)
          .then(() => {
            this.carregarFotos();
          })
          .catch(error => alert(error));
      }
    },
    carregarFotos() {
      axios.get("http://localhost:8080/apis/foto")
        .then(result => {
          this.fotos = result.data;
        })
        .catch(error => alert(error));
    }
  },
  mounted() {
    this.carregarFotos();
  }
};

</script>

<style>

</style>