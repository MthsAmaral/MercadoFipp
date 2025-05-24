<template>
    <div class="hello">
      <h1>{{ msg }}</h1>
      <div v-if="formOn">
        <form @submit.prevent="this.gravar()">
          <label for="idusr">Id</label>
          <input type="text" id="idusr" v-model="id" placeholder="ID do Usuario..">
          <label for="name">Nome</label>
          <input type="text" id="name" v-model="nome" placeholder="Nome do Usuário..">
          <label for="nivel">Nivel</label>
          <input type="text" id="nivel" v-model="nivel" placeholder="Nivel aqui..." maxlength="1">
          <label for="senha">Senha</label>
          <input type="password" id="senha" v-model="senha" placeholder="Senha desejada ...">
          <input type="submit" value="Cadastrar">
        </form>
      </div>
      <div class="botao-container">
        <button class="nova-categoria-btn" @click="novoUsuario">Novo Usuário</button>
      </div>
      <div>
        <table id="customers">
          <thead>
            <tr>
              <th>Id</th>
              <th>Nome</th>
              <th>Nivel</th>
              <th>Senha</th>
              <th colspan="2">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="usr in this.usuario">
              <td>{{usr.id}}</td>
              <td>{{usr.nome}}</td>
              <td>{{usr.nivel}}</td>
              <td>{{usr.senha}}</td>
              <td><button @click="this.alterar(usr.id)">Alterar</button></td>
              <td><button @click="this.apagar(usr.id)">Apagar</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'FormUsuario',
    props: {
      msg: String
    },
    data(){
      return {id:0, nome:"",nivel:"",senha:"", formOn:false, 
      usuario:[]}
    },
    methods:{
      mostrarForm(flag)
      {
        this.formOn=flag;
      },
      gravar(){
        const url = 'http://localhost:8080/apis/usuario';
        const data = { id: this.id, nome: this.nome, nivel: this.nivel, senha: this.senha};
        axios.post(url, data)
        .then(response => {
          this.carregarDados();
        })
        .catch(error => {
          alert('Erro:', error);
        });
        this.mostrarForm(false);
      },
      apagar(id){
        axios.delete("http://localhost:8080/apis/usuario/"+id)
        .then(result=>{this.carregarDados()})
        .catch(error=>{alert(error)})
      },
      alterar(id){
        formOn = true;
        axios.get("http://localhost:8080/apis/usuario/"+id)
        .then(result=>{
          const usuario=result.data;
          this.id = usuario.id;
          this.nome = usuario.nome;
          this.nivel = usuario.nivel;
          this.senha = usuario.senha;
        })
      },
      carregarDados(){
        axios.get("http://localhost:8080/apis/usuario")
        .then(result=>{this.usuario=result.data})
        .catch(error=>{alert(error)})
      }
    },
    mounted(){
      this.carregarDados();
    }
  }
  </script>
  
<style scoped>
/* Tipografia e base 
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'VT323', 'Share Tech Mono', monospace;
  background-color: #0a0a0a;
  color: #00ff00;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-x: hidden;
  background-image: url('https://media.giphy.com/media/AOSwwqVjNZlDO/giphy.gif');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

#app {
  width: 100%;
  padding: 20px;
  z-index: 1;
}

#menu {
  display: flex;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.7);
  border: 2px solid #00ff00;
  box-shadow: 0 0 15px #00ff00;
  padding: 10px;
  margin-bottom: 20px;
}

#menu > div {
  margin: 0 10px;
}

#menu a {
  display: inline-block;
  padding: 10px 20px;
  background-color: transparent;
  border: 2px solid #00ff00;
  color: #00ff00;
  text-decoration: none;
  font-size: 1.2em;
  transition: all 0.3s ease;
  border-radius: 5px;
}

#menu a.router-link-exact-active,
#menu a:hover {
  background-color: #00ff00;
  color: #000;
  box-shadow: 0 0 15px #00ff00;
}

.botao-container {
  display: flex;
  justify-content: flex-end;
  padding: 10px;
}

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
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 10px 0;
}

.nova-categoria-btn:hover {
  background-color: #000;
  color: #00ff00;
  box-shadow: 0 0 20px #00ff00, inset 0 0 10px #00ff00;
}

/* Container principal */
.container {
  background-color: rgba(0, 0, 0, 0.8);
  border: 2px solid #00ff00;
  box-shadow: 0 0 15px #00ff00;
  border-radius: 5px;
  padding: 20px;
  margin: 20px;
  width: 100%;
  max-width: 1000px;
}

/* Estilo da tabela 
#customers {
  font-family: 'VT323', 'Share Tech Mono', monospace;
  border-collapse: collapse;
  width: 100%;
  margin-top: 20px;
  color: #00ff00;
}

#customers td,
#customers th {
  border: 1px solid #00ff00;
  padding: 12px;
}

#customers tr:nth-child(even) {
  background-color: rgba(0, 255, 0, 0.05);
}

#customers tr:hover {
  background-color: rgba(0, 255, 0, 0.1);
}

#customers th {
  background-color: #001a00;
  color: #00ff00;
  text-align: left;
  text-shadow: 0 0 5px #00ff00;
}

/* Inputs e formulário 
input[type="text"],
input[type="email"],
input[type="password"],
select {
  background-color: rgba(0, 0, 0, 0.7);
  border: 1px solid #00ff00;
  color: #00ff00;
  padding: 10px;
  font-family: inherit;
  font-size: 1.1em;
  width: 100%;
  margin: 8px 0;
  border-radius: 5px;
  transition: box-shadow 0.3s ease, border-color 0.3s ease;
  outline: none;
}

input:focus,
select:focus {
  border-color: #00ff00;
  box-shadow: 0 0 10px #00ff00;
}

input::placeholder {
  color: #00cc00;
  opacity: 0.7;
}

input[type="submit"] {
  width: 100%;
  background-color: #00ff00;
  color: #000;
  padding: 12px 20px;
  margin: 10px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  letter-spacing: 1px;
  box-shadow: 0 0 10px #00ff00, inset 0 0 5px #00ff00;
  transition: all 0.3s ease;
}

input[type="submit"]:hover {
  background-color: #000;
  color: #00ff00;
  box-shadow: 0 0 15px #00ff00, inset 0 0 10px #00ff00;
}

/* Mensagens 
.message {
  text-align: center;
  font-size: 1.1em;
  font-weight: bold;
  text-shadow: 0 0 5px currentColor;
  margin-top: 10px;
}

.message.success {
  color: #00ff00;
}

.message.error {
  color: #ff4444;
}

.message.processing {
  color: #ffff00;
}*/
</style>