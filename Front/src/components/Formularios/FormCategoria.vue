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


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* Tipografia e base 
* {
  font-family: 'VT323', 'Share Tech Mono', monospace;
  box-sizing: border-box;
}

body {
  font-family: 'VT323', 'Share Tech Mono', monospace;
  background-color: #0a0a0a;
  color: #00ff00;
}*/

/* Botão "Nova Categoria" 
.nova-categoria-btn {
  background-color: #00ff00;
  color: #000;
  font-family: 'VT323', 'Share Tech Mono', monospace;
  font-size: 1.2em;
  padding: 10px 25px;
  border: 2px solid #00ff00;
  border-radius: 5px;
  cursor: pointer;
  box-shadow: 0 0 10px #00ff00, inset 0 0 5px #00ff00;
  transition: all 0.3s ease;
  text-shadow: none;
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 10px 0;
}*/

/* Hover 
.nova-categoria-btn:hover {
  background-color: #000;
  color: #00ff00;
  box-shadow: 0 0 20px #00ff00, inset 0 0 10px #00ff00;
}*/

/* Container onde o botão está 
.botao-container {
  display: flex;
  justify-content: flex-end;
  padding: 10px;
}*/

/* Ajuste da tabela estilo hacker 
#customers {
  font-family: 'VT323', monospace;
  border-collapse: collapse;
  width: 100%;
  margin-top: 10px;
}

#customers th,
#customers td {
  border: 1px solid #00ff00;
  padding: 12px;
  color: #00ff00;
  text-align: left;
}

#customers th {
  background-color: #001a00;
  text-shadow: 0 0 5px #00ff00;
}

#customers tr:nth-child(even) {
  background-color: #001100;
}

#customers tr:hover {
  background-color: #002200;
}*/

/* Container geral do formulário 
.form-container {
  border-radius: 8px;
  background-color: rgba(0, 0, 0, 0.85);
  padding: 20px;
  box-shadow: 0 0 10px #00ff00;
  margin-top: 20px;
}*/

/* Inputs estilo terminal 
input[type="text"],
select {
  background-color: #000;
  border: 1px solid #00ff00;
  color: #00ff00;
  padding: 10px;
  border-radius: 4px;
  font-family: inherit;
  font-size: 1.1em;
  box-shadow: inset 0 0 5px #00ff00;
  outline: none;
  width: 100%;
}

input[type="text"]:focus {
  box-shadow: 0 0 10px #00ff00;
}*/

/* Botão submit do formulário 
input[type="submit"] {
  background-color: #00ff00;
  color: #000;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  letter-spacing: 1px;
  transition: background-color 0.3s, box-shadow 0.3s;
}

input[type="submit"]:hover {
  background-color: #33ff33;
  box-shadow: 0 0 10px #00ff00;
}*/
</style>