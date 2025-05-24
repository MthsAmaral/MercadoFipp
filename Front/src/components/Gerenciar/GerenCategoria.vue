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
            <button class="btn btn-sm btn-warning" @click="editar(cat.id)">
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
      <button class="btn btn-success" @click="nova">
        <i class="bi bi-plus-circle-fill"></i> Nova Categoria
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

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
      axios.get('http://localhost:8080/apis/categoria')
        .then(res => this.categorias = res.data)
        .catch(err => alert(err))
    },
    nova() {
      this.$router.push('/form-categoria')  // redireciona para o formulário
    },
    apagar(id, nome) {
      if (window.confirm('Deseja excluir a categoria ' + nome + '?')) {
        axios.delete('http://localhost:8080/apis/categoria/' + id)
          .then(() => {
            this.carregarDados()
            alert('Categoria excluída com sucesso!')
          })
          .catch(error => alert(error))
      }
    },
    editar(id) {
      axios.get('http://localhost:8080/apis/categoria/' + id)
        .then(res => {
          localStorage.setItem('categoriaParaEditar', JSON.stringify(res.data))
          this.$router.push('/form-categoria')  // redireciona para o formulário
        })
        .catch(err => alert(err))
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


<style></style>