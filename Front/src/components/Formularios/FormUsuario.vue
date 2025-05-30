<template>
  <div class="container mt-5">
    <h2 class="mb-4 text-center">Cadastro de Usuário</h2>

    <form @submit.prevent="this.gravar" class="card p-4 shadow-sm">

      <!-- Campo Id oculto -->
      <input type="hidden" id="idusr" v-model="id" />

      <div class="mb-3">
        <label for="name" class="form-label">Nome</label>
        <!--tamanho maximo 20-->
        <input type="text" maxlength="20" id="name" v-model="nome" class="form-control" placeholder="Nome do Usuário..."
          required />
      </div>

      <div class="mb-3">
        <label for="nivel" class="form-label">Nível</label>
        <select id="nivel" v-model="nivel" class="form-select" required>
          <option disabled value="">Selecione o nível do privilégio</option>
          <option value="1">Administrador</option>
          <option value="2">Vendedor/Comprador</option>
        </select>
      </div>

      <div class="mb-3">
        <label for="senha" class="form-label">Senha</label>
        <div class="input-group">
          <input :type="mostrarSenha ? 'text' : 'password'" class="form-control" id="senha" maxlength="10"
            v-model="senha" required>
          <button class="btn btn-outline-secondary" type="button" @click="mostrarSenha = !mostrarSenha">
            {{ mostrarSenha ? 'Ocultar' : 'Mostrar' }}
          </button>
        </div>
      </div>

      <div class="d-grid">
        <button type="submit" class="btn btn-primary">
          <i class="bi" :class="id ? 'bi-pencil-square' : 'bi-person-plus'"></i>
          {{ id ? ' Alterar' : ' Cadastrar' }}
        </button>
      </div>
    </form>
  </div>
</template>


<script>
import axios from 'axios'
import { toast } from 'vue3-toastify';

export default {
  name: 'FormUsuario',
  data() {
    return {
      id: 0,
      nome: '',
      nivel: '',
      senha: '',
      modoEdicao: false,
      mostrarSenha: false
    }
  },
  methods: {
    gravar() {
      const url = 'http://localhost:8080/apis/usuario';
      const data = {
        id: this.id,
        nome: this.nome,
        nivel: this.nivel,
        senha: this.senha
      };
      if (!this.modoEdicao) {
        axios.post(url, data, {
          headers: {
            Authorization: JSON.parse(localStorage.getItem("usuario")).token
          }
        })
          .then(() => {
            this.$router.push('/login');
            toast.success('Usuário gravado com sucesso');
          })
          .catch(error => {
            toast.error('Erro ao cadastrar usuário:', error);
          });
      }
      else//atualizar
      {
        axios.put(url, data, {
          headers: {
            Authorization: JSON.parse(localStorage.getItem("usuario")).token
          }
        })
          .then(() => {
            this.modoEdicao = false;
            this.$router.push('/adm/usuarios');
            toast.success('Usuário alterado com sucesso!');
          })
      }

    },
    limparForm() {
      this.id = 0
      this.nome = ''
      this.nivel = ''
      this.senha = ''
    }
  },
  mounted() {
    this.limparForm();
    const usuarioSalvo = localStorage.getItem('usuarioParaEditar')
    if (usuarioSalvo) {
      const usuario = JSON.parse(usuarioSalvo);
      this.id = usuario.id;
      this.nome = usuario.nome;
      this.nivel = usuario.nivel;
      this.senha = usuario.senha;
      localStorage.removeItem('usuarioParaEditar');
      this.modoEdicao = true;
    }
    else
      this.modoEdicao = false;
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