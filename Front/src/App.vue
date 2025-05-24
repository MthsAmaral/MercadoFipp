<template>
  <div id="menu">
    <div><router-link to="/form-categoria">Categoria</router-link></div>
    <div><router-link to="/form-usuario">Usuário</router-link></div>
    
  </div>
  <router-view>
    <!--<div><router-link to="/form-Login">Login</router-link></div>-->
  </router-view>
</template>

<script>
import FormCategoria from './components/FormCategoria.vue';
import FormUsuario from './components/FormUsuario.vue';
//import Login from './components/Login';

export default {
  name: 'App',
  components: {
    FormCategoria, FormUsuario
  }
}
</script>

<style>
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
}
</style>
