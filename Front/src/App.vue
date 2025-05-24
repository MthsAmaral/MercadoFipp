<template>

  <nav class="navbar navbar-expand-lg" id="menu">
    <div class="container-fluid">

      <!-- Título -->
      <h1 class="navbar-brand mb-0">Mercado Fipp</h1>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false"
              aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse w-100" id="navbarSupportedContent">
        <!-- Lado esquerdo: links -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex align-items-center">

          <li class="nav-item ms-2">
            <router-link class="nav-link custom-btn" to="/home">Home</router-link>
          </li>

          <li class="nav-item ms-2">
            <router-link class="nav-link custom-btn" to="/form-anuncio">Anuncio</router-link>
          </li>

          <li class="nav-item ms-2">
            <router-link class="nav-link custom-btn" to="/form-pergunta">Perguntas</router-link>
          </li>

          <li class="nav-item dropdown ms-2">
            <a class="nav-link dropdown-toggle custom-btn" role="button" data-bs-toggle="dropdown"
               aria-expanded="false">
              Gerenciar
            </a>
            <ul class="dropdown-menu">
              <li><router-link class="dropdown-item" to="/geren-usuarios">Usuários</router-link></li>
              <li><router-link class="dropdown-item" to="/geren-categorias">Categorias</router-link></li>
              <li><router-link class="dropdown-item" to="/geren-anuncios">Anuncios</router-link></li>
            </ul>
          </li>

        </ul>

        <!-- Centro: barra de busca -->
        <div class="d-flex justify-content-center w-100">
          <form class="d-flex" role="search" style="max-width: 400px; width: 100%;">
            <input class="form-control me-2" type="search" placeholder="Buscar por nome do produto" aria-label="Search">
            <button class="btn btn-custom" type="submit">Buscar</button>
          </form>
        </div>

        <!-- Direita: Login -->
        <div class="ms-auto">
          <router-link class="nav-link custom-btn" to="/form-login">Login</router-link>
        </div>

      </div>
    </div>
  </nav>

  <router-view />
</template>

<script>
export default {
  name: 'App'
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
