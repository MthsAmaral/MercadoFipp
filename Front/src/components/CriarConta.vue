<template>
    <div class="container">
        <div class="card p-4">
            <h1 class="mb-3">Criar Conta</h1>
            <form @submit.prevent="this.validar()" class="p-3">

                <div class="mb-3">
                    <label for="usuario" class="form-label">Nome Usuário</label>
                    <input type="text" class="form-control" id="usuario" maxlength="20" v-model="nome" required>
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <div class="input-group">
                        <input :type="mostrarSenha ? 'text' : 'password'" class="form-control" id="senha" maxlength="10" v-model="senha" required>
                        <button class="btn btn-secondary" type="button" @click="mostrarSenha = !mostrarSenha">
                            {{ mostrarSenha ? 'Ocultar' : 'Mostrar' }}
                        </button>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="confirmarSenha" class="form-label">Confirmar Senha</label>
                    <div class="input-group">
                        <input :type="mostrarConfirmarSenha ? 'text' : 'password'" class="form-control" maxlength="10" id="confirmarSenha" v-model="confirmarSenha" required>
                        <button class="btn btn-secondary" type="button" @click="mostrarConfirmarSenha = !mostrarConfirmarSenha">
                            {{ mostrarConfirmarSenha ? 'Ocultar' : 'Mostrar' }}
                        </button>
                    </div>
                </div>

                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary mx-2">Criar Conta</button>
                    <router-link class="text-decoration-none" to="/home">
                        <button class="btn btn-secondary mx-2" type="button">Voltar</button>
                    </router-link>
                </div>

            </form>
        </div>
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
                const url = 'http://localhost:8080/apis/usuario/nome/' + encodeURIComponent(this.nome);

                axios.get(url)
                    .then(response => {
                        console.log(response);
                        if (!response) {
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
                nivel: 2
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

<style>

body {
  background: radial-gradient(circle at top, #1a2a30 0%, #0c1c1e 100%);
  font-family: 'Orbitron', sans-serif;
  color: #cde4e5;
  margin: 0;
}

/* Container principal com largura maior */
#form-criacao-conta {
  background-color: rgba(20, 30, 36, 0.85);
  border-radius: 12px;
  padding: 3rem 4rem;
  box-shadow: 0 0 20px rgba(0, 255, 195, 0.2);
  width: 100%;
  max-width: 1000px; /* aumentada */
  margin: 3rem auto;
  animation: fadeInUp 0.6s ease-out;
}

/* Aumenta a largura do formulário interno */
.form-wrapper {
  width: 90%; /* antes era 50% */
  margin: 0 auto;
}

/* Animação de entrada */
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(15px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

h1 {
  color: #ffffff;
  text-shadow: 0 0 6px #00ffc3;
  font-weight: 600;
  text-align: center;
  margin-bottom: 2rem;
}

label {
  font-weight: 600px;
  color: #cde4e5;
}

input.form-control {
  background-color: rgba(255, 255, 255, 0.05);
  color: #cde4e5;
  border: 1px solid #00ffc3;
  border-radius: 8px;
  font-size: 1.1rem;
  padding: 0.7rem;
  width: 100%;
}

input.form-control:focus {
  border-color: #00ffc3;
  box-shadow: 0 0 6px #00ffc3;
  outline: none;
  color: #cde4e5;
}

input::placeholder {
  color: #a0cfcf;
}

.btn-outline-secondary {
  background-color: transparent;
  color: #00ffc3;
  border: 1px solid #00ffc3;
  font-weight: 600px;
  transition: 0.3s;
  padding: 0.6rem 1rem;
}

.btn-outline-secondary:hover {
  background-color: #00ffc3;
  color: #0c1c1e;
}

.btn-warning {
  background-color: #ffc107;
  border: none;
  font-weight: 600;
  box-shadow: 0 0 6px #ffc107b7;
  transition: 0.3s;
  color: #0c1c1e;
  padding: 0.7rem;
  font-size: 1.1rem;
}

.btn-warning:hover {
  background-color: #ffdb6f;
  color: #0c1c1e;
  box-shadow: 0 0 10px #ffc107;
}

.btn-secondary {
  background-color: #6c757d;
  border: none;
  font-weight: 600;
  transition: 0.3s;
  color: #fff;
  padding: 0.7rem;
  font-size: 1.1rem;
}

.btn-secondary:hover {
  background-color: #8e99a3;
  color: #0c1c1e;
}
</style>