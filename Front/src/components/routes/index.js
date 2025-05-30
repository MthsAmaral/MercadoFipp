import { createRouter, createWebHistory } from 'vue-router';

import Home from '@/components/Home.vue';

import FormCategoria from '@/components/Formularios/FormCategoria.vue';
import FormUsuario from '@/components/Formularios/FormUsuario.vue';
import FormAnuncio from '@/components/Formularios/FormAnuncio.vue';

import GerenCategorias from '@/components/Gerenciar/GerenCategorias.vue';
import GerenUsuarios from '@/components/Gerenciar/GerenUsuarios.vue';
import GerenAnuncios from '@/components/Gerenciar/GerenAnuncios.vue';


import Anuncios from '@/components/Anuncios.vue';
import DetalheAnuncio from '@/components/DetalheAnuncio.vue';
import CadPergunta from '@/components/CadPergunta.vue';
import CadResposta from '@/components/CadResposta.vue';


import Login from '@/components/Login.vue';
import CriarConta from '@/components/CriarConta.vue';


import { toast } from 'vue3-toastify';



const routes = [
    {
        path: "/",
        component: Home,
        meta: {
            title: "Início"
        }
    },
    {
        path: "/login",
        component: Login,
        meta: {
            title: "Entrar"
        }
    },
    {
        path: "/criar-conta",
        component: CriarConta,
        meta: {
            title: "Cadastre-se no Mercado FIPP"
        }
    },
    {
        path: "/anuncio/:id",
        component: DetalheAnuncio,
        meta: {
            title: "Anúncio",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/anuncios",
        component: Anuncios,
        meta: {
            title: "Todos os anúncios",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/perguntar/:id",
        component: CadPergunta,
        meta: {
            title: "Faça sua pergunta",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/responder/:idA/:idP",
        component: CadResposta,
        meta: {
            title: "Perguntas do seu anúncio!",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/formulario/anuncio",
        component: FormAnuncio,
        meta: {
            title: "Cadastro de anúncio",
            requiresAuth: true,
            requiresAdmin: false
        }
    },
    {
        path: "/adm/anuncios",
        component: GerenAnuncios,
        meta: {
            title: "Gerenciar Anúncios",
            requiresAuth: true,
            requiresAdmin: true
        }
    },
    {
        path: "/adm/categorias",
        component: GerenCategorias,
        meta: {
            title: "Gerenciar Categorias",
            requiresAuth: true,
            requiresAdmin: true
        }
    },
    {
        path: '/formulario/categoria',
        component: FormCategoria,
        meta:{
            requiresAuth: true,
            requiresAdmin: true
        }
    },
    {
        path: "/adm/usuarios",
        component: GerenUsuarios,
        meta: {
            title: "Gerenciar Usuários",
            requiresAuth: true,
            requiresAdmin: true
        }
    },
    {
        path: "/formulario/usuario",
        component: FormUsuario,
        meta: {
            requiresAuth: true,
            requiresAdmin: true
        }
    }
];

const router = createRouter({
    history: createWebHistory(), // Para conseguir voltar no navegador
    routes
});

// Aplicar títulos nas rota.
router.beforeEach((to, from, next) => {
    const titulo = "Mercado FIPP";
    document.title = to.meta.title || titulo;
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    if (to.meta.requiresAuth && !usuario)
        return next("/login");
    if (to.meta.requiresAdmin && (!usuario || usuario.nivel !== "1")){
        return toast.error("Você não tem permissão!");
    }
        
    next();
});

export default router;