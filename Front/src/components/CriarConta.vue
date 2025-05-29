<template>
    <div class="container mt-5 w-50">
        <h1 class="mb-3">Criar Conta</h1>
        <form @submit.prevent="this.validar()" class="p-3">

            <div class="mb-3">
                <label for="usuario" class="form-label">Nome Usuário</label>
                <input type="text" class="form-control" id="usuario" maxlength="20" v-model="nome" required>
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

            <div class="mb-3">
                <label for="confirmarSenha" class="form-label">Confirmar Senha</label>
                <div class="input-group">
                    <input :type="mostrarConfirmarSenha ? 'text' : 'password'" class="form-control" maxlength="10" id="confirmarSenha"
                        v-model="confirmarSenha" required>
                    <button class="btn btn-outline-secondary" type="button"
                        @click="mostrarConfirmarSenha = !mostrarConfirmarSenha">
                        {{ mostrarConfirmarSenha ? 'Ocultar' : 'Mostrar' }}
                    </button>
                </div>
            </div>

            <button type="submit" class="btn btn-warning mt-2 mx-3">Criar Conta</button>
            <router-link class="text-decoration-none" to="/home">
                <button class="btn btn-secondary mt-2 mx-3" type="button">Voltar</button>
            </router-link>
        </form>
    </div>
</template>

<script>
import axios from "axios";
import { toast } from 'vue3-toastify';

export default {
    name: "CriarConta",
    data() {
        return {
            nome: "",
            senha: "",
            confirmarSenha: "",
            mostrarSenha: false,
            mostrarConfirmarSenha: false
        }
    },
    methods: {
        resetarForm() {
            this.nome = "";
            this.senha = "";
            this.confirmarSenha = "";
        },
        validar() {
            if (this.senha === this.confirmarSenha) {
                const url = 'http://localhost:8080/apis/usuario/nome/' + this.nome;

                axios.get(url)
                    .then(response => {
                        console.log(response);
                        if (response) {
                            this.resetarForm();
                            toast.warn("Nome de usuário já cadastrado!");
                        }
                        else {
                            this.cadastrar();
                        }
                    })
                    .catch(erro => {
                        console.log(erro);
                    });
            } else
                toast.warn("repita a mesma senha nos campos!");
        },
        cadastrar() {
            const url = "http://localhost:8080/apis/usuario"
            const data = {
                nome: this.nome,
                senha: this.senha,
                nivel: 1
            }
            axios.post(url, data)
                .then(resposta => {
                    console.log(resposta);
                    this.$router.push("/login");
                    toast.success("Usuário cadastrado");
                })
                .catch(erro => {
                    console.log(erro);
                    this.resetarFormulario();
                    toast.error("Erro ao cadastrar!");

                });
        }
    },
    mounted() {
        if (localStorage.getItem("usuario"))
            this.$router.push("/home");
    }
}
</script>

<style scoped></style>