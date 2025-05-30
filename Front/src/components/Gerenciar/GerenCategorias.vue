<template>
  <div class="container mt-4">
    <h1 class="mb-4">Gerenciar Categorias</h1>

    <table class="table table-striped table-hover shadow-sm">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th style="cursor: pointer" @click="ordenarNome()">Nome &#x25B2;</th>
          <th colspan="2">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cat in categorias" :key="cat.id">
          <td>{{ cat.id }}</td>
          <td>{{ cat.nome }}</td>
          <td>
            <button class="btn btn-sm btn-warning" @click="alterar(cat.id)">
              <i class="bi bi-pencil-fill"></i> Alterar
            </button>
          </td>
          <td>
            <button class="btn btn-sm btn-danger" @click="apagar(cat.id, cat.nome)">
              <i class="bi bi-trash-fill"></i> Apagar
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="text-end mt-4">
      <button class="btn btn-success" @click="cadastrar">
        <i class="bi bi-plus-circle-fill"></i> Nova Categoria
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { toast } from 'vue3-toastify';

export default {
  name: 'GerenCategorias',
  data() {
    return {
      categorias: [],
      ordenado: true
    }
  },
  methods: {
    carregarDados() {
      axios.get("http://localhost:8080/apis/categoria",{
                headers: {
                    Authorization: JSON.parse(localStorage.getItem("usuario")).token
                }
            })
        .then(response => this.categorias = response.data)
        .catch(erro => toast.error(erro))
    },
    cadastrar() {
      this.$router.push('/formulario/categoria')  // redireciona para o formulário
    },
    apagar(id, nome) {
      if (window.confirm('Deseja excluir a categoria ' + nome + '?')) {
        axios.delete('http://localhost:8080/apis/categoria/' + id,{
                headers: {
                    Authorization: JSON.parse(localStorage.getItem("usuario")).token
                }
            })
          .then(() => {
            this.carregarDados()
            toast.success('Categoria excluída com sucesso!');
          })
          .catch(erro => toast.error(erro))
      }
    },
    alterar(id) {
      axios.get("http://localhost:8080/apis/categoria/" + id,{
                headers: {
                    Authorization: JSON.parse(localStorage.getItem("usuario")).token
                }
            })
        .then(response => {
          localStorage.setItem('categoriaParaEditar', JSON.stringify(response.data))
          this.$router.push('/formulario/categoria')  // redireciona para o formulário
        })
        .catch(erro => toast.error(erro))
    },
    ordenarNome() {
      if (this.ordem) {
        this.categorias.sort((a, b) => a.nome.localeCompare(b.nome));
      } else {
        this.categorias.sort((a, b) => b.nome.localeCompare(a.nome));
      }
      this.ordem = !this.ordem; // inverte a ordem
    }


  },
  mounted() {
    this.carregarDados()
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #ffffff;
  margin: 0;
  padding: 20px;
  min-height: 100vh;
}

.container {
  background: rgba(10, 20, 24, 0.95);
  padding: 30px;
  border-radius: 16px;
  box-shadow: 0 0 16px #00ffc3;
}

h1 {
  text-align: center;
  color: #ffffff;
  text-shadow: 0 0 8px #00ffc3;
  font-weight: 700;
  margin-bottom: 20px;
}

.table {
  border-radius: 12px;
  overflow: hidden;
  background-color: #0c1c1e;
  color: #ffffff;
  box-shadow: 0 0 12px #00ffc3a2;
}

.table thead {
  background-color: #00ffc3;
  color: #0c1c1e;
  text-shadow: 0 0 4px #00ffc3;
}

.table th,
.table td {
  vertical-align: middle;
  text-align: center;
  border-color: #00ffc3;
  font-weight: 600;
  color: #ffffff !important;
}

.table-hover tbody tr:hover {
  background-color: rgba(0, 255, 195, 0.1);
}

.btn-success {
  background-color: #00ffc3;
  border: none;
  color: #0c1c1e;
  font-weight: bold;
  box-shadow: 0 0 8px #00ffc3;
  transition: background-color 0.3s ease;
}

.btn-success:hover {
  background-color: #00c0a0;
}

.btn-danger {
  background-color: #ff4c4c;
  border: none;
  color: white;
  box-shadow: 0 0 6px #ff4c4c;
  transition: background-color 0.3s ease;
}

.btn-danger:hover {
  background-color: #d63434;
}

.btn-warning {
  background-color: #ffc107;
  border: none;
  color: #0c1c1e;
  font-weight: bold;
  box-shadow: 0 0 6px #ffc107;
  transition: background-color 0.3s ease;
}

.btn-warning:hover {
  background-color: #e0a800;
}

.text-end button {
  font-family: 'Orbitron', sans-serif;
  font-weight: 600;
}
</style>

