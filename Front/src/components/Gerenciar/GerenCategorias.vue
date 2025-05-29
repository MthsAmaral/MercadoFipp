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


<style></style>