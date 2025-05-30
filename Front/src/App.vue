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
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000;
  background-color: #0d1117; 
  box-shadow: 0 4px 6px rgba(0,0,0,0.3);
  animation: slideDown 0.8s ease-out forwards;
}

.navbar .container-fluid {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.navbar-nav {
  display: flex;
  align-items: center;
}

.nav-link, .navbar-brand, .dropdown-item, .btn {
  transition: all 0.3s ease;
}

.nav-link:hover, .navbar-brand:hover, .dropdown-item:hover {
  color: #00ffd5;
  text-shadow: 0 0 8px #00ffd5;
}

.btn:hover {
  transform: scale(1.05);
  box-shadow: 0 0 10px #00ffd5;
}

.d-flex {
  display: flex;
  align-items: center;
}

@keyframes slideDown {
  0% {
    transform: translateY(-100%);
    opacity: 0;
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}

</style>