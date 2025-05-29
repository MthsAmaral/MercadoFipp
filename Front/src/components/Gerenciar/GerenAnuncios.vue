<template>
  <div class="container mt-4">
    <h1 class="mb-4">Gerenciar Anúncios</h1>

    <table class="table table-striped table-hover shadow-sm">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Foto</th>
          <th>Título</th>
          <th>Preço</th>
          <th>Data</th>
          <th>Categoria</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="anuncio in anuncios" :key="anuncio.id">
          <td>{{ anuncio.id }}</td>
          <td>
            <img
              v-if="anuncio.fotos && anuncio.fotos.length"
              :src="`http://localhost:8080/imagens/${anuncio.fotos[0]}`"
              alt="Foto do anúncio"
              class="img-thumbnail"
              style="width: 80px; height: 80px; object-fit: cover;"
            />
            <span v-else class="text-muted">Sem foto</span>
          </td>
          <td>{{ anuncio.titulo }}</td>
          <td>R$ {{ parseFloat(anuncio.preco).toFixed(2) }}</td>
          <td>{{ anuncio.data }}</td>
          <td>{{ anuncio.categoria?.nome || 'Sem categoria' }}</td>
          <td>
            <button class="btn btn-sm btn-danger" @click="apagar(anuncio.id, anuncio.titulo)">
              <i class="bi bi-trash-fill"></i> Apagar
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
import { toast } from 'vue3-toastify';

export default {
  name: 'GerenAnuncios',
  data() {
    return {
      anuncios: []
    };
  },
  methods: {
    carregarDados() {
      axios.get('http://localhost:8080/apis/anuncio',{
                headers: {
                    Authorization: JSON.parse(localStorage.getItem("usuario")).token
                }
            })
        .then(response => this.anuncios = response.data)
        .catch(error => toast.error('Erro ao carregar anúncios!'));
    },
    apagar(id, titulo) {
      if (confirm(`Deseja excluir o anúncio "${titulo}"?`)) {
        axios.delete(`http://localhost:8080/apis/anuncio/${id}`,{
                headers: {
                    Authorization: JSON.parse(localStorage.getItem("usuario")).token
                }
            })
          .then(() => {
            this.carregarDados();
            toast.success('Anúncio excluído com sucesso!');
          })
          .catch(error => toast.error('Erro ao excluir anúncio!'));
      }
    }
  },
  mounted() {
    this.carregarDados();
  }
};
</script>

<style scoped>
.table th, .table td {
  vertical-align: middle;
}
</style>
