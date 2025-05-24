//falta chamar os outros componentes e rotas 
import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'


import FormCategoria from './components/FormCategoria.vue'
import FormUsuario from './components/FormUsuario.vue'
import App from './App.vue'
import Login from './components/Login.vue'
//import para todos os componentes


const router = createRouter({
        history: createWebHistory(),
        routes: [
            { path: '/form-categoria', component: FormCategoria, props: true},
            { path: '/form-usuario', component: FormUsuario, props: true },
            { path: '/form-login', component: Login, props:true}
        ]
});

const app = createApp(App)
app.use(router)
app.mount('#app')
