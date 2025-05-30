<template>
  <nav class="navbar navbar-expand-lg bg-dark navbar-dark" id="menu">
    <div class="container-fluid">

      <!-- Título -->
      <router-link class="navbar-brand mb-0" to="/">Mercado Fipp</router-link>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        
        <ul v-if="usuario" class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link" to="/anuncios">Listar Anúncios</router-link>
          </li>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Anuncio
            </a>
            <ul class="dropdown-menu">
              <li><router-link class="dropdown-item" to="/anuncios/usuario/:idUsr">Meus Anuncios</router-link></li>
              <li><router-link class="dropdown-item" to="/anuncios">Todos Anuncios</router-link></li>
            </ul>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/formulario/anuncio">Anunciar</router-link>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Gerenciar
            </a>
            <ul class="dropdown-menu">
              <li><router-link class="dropdown-item" to="/adm/usuarios">Usuários</router-link></li>
              <li><router-link class="dropdown-item" to="/adm/categorias">Categorias</router-link></li>
              <li><router-link class="dropdown-item" to="/adm/anuncios">Anúncios</router-link></li>
            </ul>
          </li>
        </ul>

        

        <!-- Direita: login e usuário -->
        <div class="d-flex align-items-center">
          <div v-if="usuario" class="text-white me-3">{{ usuario.nome }}</div>

          <router-link v-if="usuario" to="/home">
            <button class="btn btn-danger me-2" @click="sair()">Sair</button>
          </router-link>

          <router-link v-else to="/login">
            <button class="btn btn-outline-light me-2">Login</button>
          </router-link>

          <router-link v-if="!usuario" to="/criar-conta">
            <button class="btn btn-light">Registrar</button>
          </router-link>
        </div>

      </div>
    </div>
  </nav>

  <!--chamar componente home aqui-->
  <router-link to="/"></router-link>


  <router-view />
</template>

<script>
export default {
  name: 'App',
  data() {
    return{
      usuario: null
    }
    
  },
  methods: {
    sair() {
      localStorage.removeItem("usuario");
      this.usuario = null;
    }
  },
  mounted() {
    this.usuario = JSON.parse(localStorage.getItem("usuario"));
  },
  watch: {
    $route() {
      this.usuario = JSON.parse(localStorage.getItem("usuario"));
    }
  }
}
</script>

<style>
#menu {
  background-color: #faf628;
  padding: 10px 20px;
  color: #333;
  font-family: sans-serif;
}

/* Título */
h1 {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

/* Botões/link do menu com visual padrão */
.custom-btn {
  background: none;
  border: none;
  padding: 8px 12px;
  color: #333;
  cursor: pointer;
  transition: background 0.3s;
  font-weight: 500;
}

.custom-btn:hover,
.dropdown-item:hover {
  background-color: #dcdcdc;
  border-radius: 4px;
  text-decoration: none;
}

/* Botão de busca */
.btn-custom {
  background-color: #28a745;
  color: white;
  border: none;
}

.btn-custom:hover {
  background-color: #218838;
}

a {
  text-decoration: none;
}

.dropdown-menu {
  border-radius: 4px;
}


/*
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
}

#menu {
  display: flex;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.9);
  border-bottom: 2px solid #00ff00;
  padding: 10px 0;
  box-shadow: 0 0 15px #00ff00;
  position: sticky;
  top: 0;
  z-index: 10;
}

#menu > div {
  margin: 0 10px;
}

#menu a {
  display: block;
  padding: 10px 20px;
  background-color: rgba(0, 255, 0, 0.1);
  border: 1px solid #00ff00;
  color: #00ff00;
  text-decoration: none;
  font-size: 1.3em;
  transition: all 0.3s ease;
  box-shadow: inset 0 0 5px #00ff00;
  text-align: center;
  border-radius: 4px;
}

#menu a:hover,
#menu a.router-link-exact-active {
  background-color: #00ff00;
  color: #0a0a0a;
  box-shadow: 0 0 10px #00ff00;
}

#app {
  font-family: 'VT323', monospace;
  text-align: center;
  color: #00ff00;
  margin-top: 30px;
  padding: 20px;
}*/
</style>
