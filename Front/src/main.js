//falta chamar os outros componentes e rotas 
import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'

import Home from './components/Home.vue'

import FormCategoria from './components/Formularios/FormCategoria.vue'
import FormUsuario from './components/Formularios/FormUsuario.vue'
import FormAnuncio from './components/Formularios/FormAnuncio.vue'

import GerenCategorias from './components/Gerenciar/GerenCategoria.vue'
import GerenUsuarios from './components/Gerenciar/GerenUsuario.vue'
import GerenAnuncios from './components/Gerenciar/GerenAnuncio.vue'

import App from './App.vue'
import Login from './components/Login.vue'
//import para todos os componentes


const router = createRouter({
        history: createWebHistory(),
        routes: [
            { path: '/home', component: Home, props: true}, //tera a lista de anuncios

            { path: '/form-categoria', component: FormCategoria, props: true},
            { path: '/form-usuario', component: FormUsuario, props: true },
            { path: '/form-anuncio', component: FormAnuncio, props: true },

            { path: '/form-login', component: Login, props:true},

            { path: '/geren-categorias', component: GerenCategorias, props: true},
            { path: '/geren-usuarios', component: GerenUsuarios, props: true },
            { path: '/geren-anuncios', component: GerenAnuncios, props: true }
            
        ]
});

const app = createApp(App)
app.use(router)
app.mount('#app')
