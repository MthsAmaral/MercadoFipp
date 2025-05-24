<template>
  <table>
    <thead>
      <tr>
        <th>Id</th>
        <th @click="ordenarNome()">Nome</th>
        <th colspan="2">Ações</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="cat in categorias" :key="cat.id">
        <td>{{ cat.id }}</td>
        <td>{{ cat.nome }}</td>
        <td><button @click="editar(cat.id)">Alterar</button></td>
        <td><button @click="apagar(cat.id, cat.nome)">Apagar</button></td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from 'axios'

export default {
  name: 'GerenCategorias',
  data() {
    return {
      categorias: []
    }
  },
  methods: {
    carregarDados() {
      axios.get('http://localhost:8080/apis/categoria')
        .then(res => this.categorias = res.data)
        .catch(err => alert(err))
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
          this.$emit('editarCategoria', res.data)
        })
        .catch(err => alert(err))
    },
    ordenarNome() {
      this.categorias.sort((a, b) => a.nome.localeCompare(b.nome))
    }
  },
  mounted() {
    this.carregarDados()
  }
}
</script>


<style></style>