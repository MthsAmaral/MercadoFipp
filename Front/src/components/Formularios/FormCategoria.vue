<template>
  <div class="container mt-4">
    <h1 class="mb-4">Cadastro de Categoria</h1>
    <form @submit.prevent="this.gravar" class="card p-4 shadow-sm">

      <!-- Campo Id oculto -->
      <input type="hidden" v-model="id" />

      <div class="mb-3">
        <label for="name" class="form-label">Nome da Categoria</label>
        <input type="text" id="name" v-model="nome" class="form-control" required />
      </div>

      <button type="submit" class="btn btn-primary">
        {{ id ? 'Alterar' : 'Cadastrar' }}
      </button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
import { toast } from 'vue3-toastify';

export default {
  name: 'FormCategoria',
  data() {
    return {
      id: 0,
      nome: '',
      modoEdicao: false
    }
  },
  methods: {
    gravar() {
      const url = 'http://localhost:8080/apis/categoria';
      const data = {
        id: this.id,
        nome: this.nome
      };
      if (!this.modoEdicao) {

        axios.post(url, data, {
          headers: {
            Authorization: JSON.parse(localStorage.getItem("usuario")).token
          }
        })
          .then(() => {
            this.$router.push('/adm/categorias');//redireciona para pagina gerenciar
            toast.success('Categoria gravada com sucesso!');
          })
          .catch(error => {
            toast.error('Erro ao salvar: ' + error);
          })
      } else {//atualizar
        axios.put(url, data, {
          headers: {
            Authorization: JSON.parse(localStorage.getItem("usuario")).token
          }
        })
          .then(() => {
            this.modoEdicao = false
            this.$router.push('/adm/categorias');
            toast.success('Categoria alterada com sucesso!');
          })
          .catch(error => {
            toast.error('Erro ao alterar: ' + error);
          })
      }

    },
    limparForm() {
      this.id = 0
      this.nome = ''
    }
  },
  mounted() {
    this.limparForm()
    const categoriaSalva = localStorage.getItem('categoriaParaEditar')
    if (categoriaSalva) {
      const categoria = JSON.parse(categoriaSalva);
      this.id = categoria.id;
      this.nome = categoria.nome;
      localStorage.removeItem('categoriaParaEditar');
      this.modoEdicao = true;
    }
    else
      this.modoEdicao = false;
  }
}
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  margin: 0;
  padding: 20px;
  min-height: 100vh;
}

.container {
  max-width: 500px;
  margin: auto;
  color: #cde4e5;
}

.card {
  background: rgba(15, 32, 39, 0.85);
  border: 1px solid #00ffc3;
  border-radius: 12px;
  box-shadow: 0 0 15px #00ffc36e;
  backdrop-filter: blur(6px);
}

h1 {
  text-align: center;
  font-weight: 700;
  color: #ffffff;
  text-shadow: 0 0 8px #00ffc3;
  font-size: 1.6rem;
}

.form-label {
  color: #cde4e5;
  font-weight: 500;
}

input.form-control {
  background-color: #0d1b1e;
  border: 1px solid #00ffc3;
  color: #cde4e5;
  box-shadow: inset 0 0 4px #00ffc3;
  transition: all 0.3s ease;
}

input.form-control:focus {
  background-color: #101f22;
  box-shadow: 0 0 6px #00ffc3;
  outline: none;
}

.btn-primary {
  background-color: #00ffc3;
  border: none;
  color: #0c1c1e;
  font-weight: bold;
  box-shadow: 0 0 10px #00ffc3;
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #00d1a3;
}
</style>