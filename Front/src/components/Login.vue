<template>
    <div class="container mt-4">
        <h1 class="mb-4">Login Usuário</h1>
        <form @submit.prevent="this.logar()" class="card p-4 shadow-sm">

            <div class="mb-3">
                <label for="name" class="form-label">Usuário</label>
                <input type="text" maxlength="20" id="name" v-model="nome" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <div class="input-group">
                    <input :type="mostrarSenha ? 'text' : 'password'" class="form-control" id="senha" maxlength="10" v-model="senha"
                        required>
                    <button class="btn btn-outline-secondary" type="button" @click="mostrarSenha = !mostrarSenha">
                        {{ mostrarSenha ? 'Ocultar' : 'Mostrar' }}
                    </button>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">
                Entrar
            </button>
            <router-link class="text-decoration-none" to="/home">
                <button class="btn btn-secondary mt-2 mx-2" type="button">Voltar</button>
            </router-link>
        </form>
    </div>
</template>

<script>
import axios from "axios";
import { toast } from 'vue3-toastify';

export default {
    name: 'Login',
    data() {
        return {
            nome: "",
            senha: "",
            mostrarSenha: false,
        }
    },
    methods: {
         logar() {
            const url = "http://localhost:8080/apis/usuario/logar";
            const data = new FormData();
            data.append("nome", this.nome);
            data.append("senha", this.senha);
            axios.post(url, data)
                .then(resposta => {
                    console.log(resposta);
                    localStorage.setItem("usuario", JSON.stringify(resposta.data));
                    this.$router.push("/");
                    toast.success("Login realizado!");
                })
                .catch(erro => {
                    toast.error("Não foi possível logar!");
                    console.log(erro);
                });
        }
    },
    mounted() {
        if(localStorage.getItem("usuario"))
            this.$router.push("/");
    }
}

</script>

<style>

body {
    background: radial-gradient(ellipse at center, #1a2a30 0%, #0c1c1e 100%);
    color: #cde4e5;
    font-family: 'Orbitron', sans-serif;
    min-height: 100vh;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

.container {
    max-width: 500px;
}

.card {
    background: rgba(15, 32, 39, 0.85);
    border: 1px solid #00ffc3;
    border-radius: 12px;
    box-shadow: 0 0 15px #00ffc36e;
    backdrop-filter: blur(6px);
}

h1 {
    text-align: center;
    font-weight: 700;
    color: #ffffff;
    text-shadow: 0 0 8px #00ffc3;
    font-size: 1.8rem;
}

.form-label {
    color: #cde4e5;
    font-size: 0.95rem;
}

input.form-control {
    background-color: #0d1b1e;
    border: 1px solid #00ffc3;
    color: #cde4e5;
    box-shadow: inset 0 0 4px #00ffc3;
    transition: all 0.3s;
}

input.form-control:focus {
    background-color: #101f22;
    outline: none;
    box-shadow: 0 0 6px #00ffc3;
}

.input-group .btn {
    background-color: transparent;
    color: #00ffc3;
    border-color: #00ffc3;
    transition: all 0.3s ease;
}

.input-group .btn:hover {
    background-color: #00ffc3;
    color: #0c1c1e;
}

.btn-primary {
    background-color: #00ffc3;
    border: none;
    color: #0c1c1e;
    font-weight: bold;
    box-shadow: 0 0 10px #00ffc3;
}

.btn-primary:hover {
    background-color: #00d1a3;
}

.btn-secondary {
    background-color: #142b30;
    color: #00ffc3;
    border: 1px solid #00ffc3;
    transition: 0.3s ease;
}

.btn-secondary:hover {
    background-color: #00ffc3;
    color: #0c1c1e;
}
</style>