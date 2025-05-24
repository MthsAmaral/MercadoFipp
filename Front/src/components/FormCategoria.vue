<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <div v-if="formOn">
      <form @submit.prevent="this.gravar()">
        <label for="idcat">Id</label>
        <input type="text" id="idcat" v-model="id" placeholder="ID da Categoria.." disabled>
        <label for="name">Nome</label>
        <input type="text" id="name" v-model="nome" placeholder="Nome da Categoria..">
        <input type="submit" value="Cadastrar">
      </form>
    </div>
           <div class="botao-container">
      <button class="nova-categoria-btn" @click="novaCategoria">Nova Categoria</button>
    </div>
    <div>
      <table id="customers">
        <thead>
          <tr>
            <th>Id</th>
            <th @click="ordenarNome()">Nome</th>
            <th colspan="2">Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cat in this.categorias">
            <td>{{cat.id}}</td>
            <td>{{cat.nome}}</td>
            <td><button @click="this.alterar(cat.id)">Alterar</button></td>
            <td><button @click="this.apagar(cat.id, cat.nome)">Apagar</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import axios from 'axios'


export default {
  name: 'FormCategoria',
  props: {
    msg: String
  },
  data(){
    return {
      id:0,
      nome:"",
      formOn:false, 
      categorias:[]
    }
  },
  methods:{
    mostrarForm(flag)
    {
      this.formOn=flag;
    },
    gravar(){
      const url = 'http://localhost:8080/apis/categoria';
      const data = { 
        id: this.id,
        nome: this.nome
      };
      axios.post(url, data)
      .then(response => {
        this.carregarDados();
      })
      .catch(error => {
        alert('Erro:', error);
      });
      this.mostrarForm(false);
    },
    apagar(id,nome){
      if(window.confirm('Deseja realmente excluir a categoria '+nome+' ?')){
        //apaga se não tiver anuncio na categoria
        axios.delete("http://localhost:8080/apis/categoria/"+id)
        .then(result=>{
          this.carregarDados()
          this.$toast.open({message: 'Categoria excluida com sucesso!'})
        })
        .catch(error=>{alert(error)})
      }else
        {
          //
        }
    },
    alterar(id){
      this.formOn=true;
      axios.get("http://localhost:8080/apis/categoria/"+id)
      .then(result=>{
        const categoria = result.data;
        this.id=categoria.id;
        this.nome = categoria.nome; 
      })
      .catch(error=>{alert(error)})

      //alert('Alterando '+id);
    },
    carregarDados(){
      axios.get("http://localhost:8080/apis/categoria")
      .then(result=>{this.categorias=result.data})
      .catch(error=>{alert(error)})
    },
    ordenarNome(){
      this.categorias.sort((a,b)=> a.nome.localeCompare(b.nome));
    }
  },
  mounted(){
    this.carregarDados();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* Tipografia e base */
* {
  font-family: 'VT323', 'Share Tech Mono', monospace;
  box-sizing: border-box;
}

body {
  font-family: 'VT323', 'Share Tech Mono', monospace;
  background-color: #0a0a0a;
  color: #00ff00;
}

/* Botão "Nova Categoria" */
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
}

/* Hover */
.nova-categoria-btn:hover {
  background-color: #000;
  color: #00ff00;
  box-shadow: 0 0 20px #00ff00, inset 0 0 10px #00ff00;
}

/* Container onde o botão está */
.botao-container {
  display: flex;
  justify-content: flex-end;
  padding: 10px;
}

/* Ajuste da tabela estilo hacker */
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
}

/* Container geral do formulário */
.form-container {
  border-radius: 8px;
  background-color: rgba(0, 0, 0, 0.85);
  padding: 20px;
  box-shadow: 0 0 10px #00ff00;
  margin-top: 20px;
}

/* Inputs estilo terminal */
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
}

/* Botão submit do formulário */
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
}
</style>