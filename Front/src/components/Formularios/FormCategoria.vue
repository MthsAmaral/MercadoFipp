<template>
  <div>
    <h1>Cadastro de Categoria</h1>
    <form @submit.prevent="gravar">
      <label for="idcat">Id</label>
      <input type="text" id="idcat" v-model="id" disabled />
      <label for="name">Nome</label>
      <input type="text" id="name" v-model="nome" />
      <input type="submit" :value="id ? 'Alterar' : 'Cadastrar'" />
    </form>
    <button @click="novaCategoria">Nova Categoria</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'FormCategoria',
  props: {
    categoria: Object
  },
  data() {
    return {
      id: 0,
      nome: ''
    }
  },
  watch: {
    categoria: {
      immediate: true,
      handler(nova) {
        if (nova) {
          this.id = nova.id
          this.nome = nova.nome
        }
      }
    }
  },
  methods: {
    gravar() {
      const url = 'http://localhost:8080/apis/categoria'
      const data = { id: this.id, nome: this.nome }

      axios.post(url, data)
        .then(() => {
          this.$emit('salvo') // avisa o pai que salvou
          alert('Categoria salva com sucesso!')
          this.novaCategoria()
        })
        .catch(error => {
          alert('Erro ao salvar: ' + error)
        })
    },
    novaCategoria() {
      this.id = 0
      this.nome = ''
    }
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