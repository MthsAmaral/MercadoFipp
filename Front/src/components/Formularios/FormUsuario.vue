<template>
  <div class="container mt-5">
    <h2 class="mb-4 text-center">Cadastro de Usuário</h2>

    <form @submit.prevent="gravar" class="card p-4 shadow-sm">

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
      mostrarSenha: false,
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
            toast.success('Usuário gravado com sucesso');
            this.$router.push('/adm/usuarios');
          })
          .catch(error => {
            toast.error('Erro ao salvar:', error);
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
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@400;600&display=swap');

body {
  background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  margin: 0;
  padding: 20px;
  min-height: 100vh;
}

.container {
  max-width: 500px;
  margin: auto;
}

.card {
  background: rgba(15, 32, 39, 0.85);
  border: 1px solid #00ffc3;
  border-radius: 12px;
  box-shadow: 0 0 15px #00ffc36e;
  backdrop-filter: blur(6px);
}

h2 {
  color: #ffffff;
  font-weight: 700;
  text-shadow: 0 0 8px #00ffc3;
}

.form-label {
  color: #cde4e5;
  font-weight: 500;
}

input.form-control,
select.form-select {
  background-color: #0d1b1e;
  border: 1px solid #00ffc3;
  color: #cde4e5;
  box-shadow: inset 0 0 4px #00ffc3;
  transition: all 0.3s ease;
}

input:focus,
select:focus {
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

.toggle-btn {
  background-color: transparent;
  color: #00ffc3;
  border-color: #00ffc3;
  box-shadow: 0 0 6px #00ffc3;
}

.toggle-btn:hover {
  background-color: #00ffc3;
  color: #0c1c1e;
}
</style>