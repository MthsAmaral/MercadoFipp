<template>
  <div class="container mt-4">
    <h1 class="mb-4">Gerenciar Anúncios</h1>

    <table class="table table-striped table-hover shadow-sm">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
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
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  margin: 0;
  padding: 20px;
  min-height: 100vh;
}

.container {
  max-width: 1000px;
  margin: auto;
  color: #ffffff;
}

h1 {
  text-align: center;
  color: #ffffff;
  text-shadow: 0 0 8px #00ffc3;
  font-weight: 700;
}

.custom-table {
  border-radius: 12px;
  overflow: hidden;
  border: 1px solid #00ffc3;
  box-shadow: 0 0 12px #00ffc3a2;
  background-color: rgba(10, 20, 24, 0.9);
  color: #ffffff;
}

.custom-table thead {
  background-color: rgba(0, 255, 195, 0.2);
  color: #ffffff;
  text-shadow: 0 0 6px #00ffc3;
}

.custom-table th,
.custom-table td {
  vertical-align: middle;
  text-align: center;
  border-color: #00ffc3;
  font-weight: 600;
  color: #ffffff;
}

.custom-table tbody tr:hover {
  background-color: rgba(0, 255, 195, 0.08);
}

.delete-btn {
  background-color: #ff4c4c;
  border: none;
  color: white;
  box-shadow: 0 0 6px #ff4c4c;
  transition: background-color 0.3s ease;
}

.delete-btn:hover {
  background-color: #d63434;
}
</style>

