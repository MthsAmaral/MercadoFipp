<!--tem que terminar esta com erro corrigir a logica-->
<template>
    <div class="container mt-4">
        <h1 class="mb-4">Login Usuário</h1>
        <form @submit.prevent="this.logar()" class="card p-4 shadow-sm">

            <div class="mb-3">
                <label for="name" class="form-label">Usuário</label>
                <input type="text" maxlength="20" id="name" v-model="nome" class="form-control" required />
            </div>

            <div class="mb-3">
                <label for="name" class="form-label">Senha</label>
                <input type="password" maxlength="10" id="name" v-model="senha" class="form-control" required />
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

export default {
    name: 'Login',
    data() {
        return {
            nome: "",
            senha: ""
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
                    this.$router.push("/home");
                })
                .catch(erro => {
                    console.log(erro);
                });
        }
    },
    mounted() {
        if(localStorage.getItem("usuario"))
            this.$router.push("/home");
    }
}

</script>

<style>
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

    display: flex;

    justify-content: center;

    align-items: center;

    min-height: 100vh;

    overflow: hidden; 

    background-image: url('https://media.giphy.com/media/AOSwwqVjNZlDO/giphy.gif'); 

    background-size: cover;

    background-position: center;

    background-repeat: no-repeat;

    position: relative; 

}

#background-overlay {

    position: absolute;

    top: 0;

    left: 0;

    width: 100%;

    height: 100%;

    background-color: rgba(0, 0, 0, 0.7); 

    z-index: 0;

}

.terminal-window {

    width: 90%;

    max-width: 650px;

    background-color: rgba(10, 25, 10, 0.85); 

    border: 2px solid #00ff00;

    box-shadow: 0 0 25px rgba(0, 255, 0, 0.5), inset 0 0 15px rgba(0, 255, 0, 0.3);

    border-radius: 5px;

    overflow: hidden;

    position: relative; 

    z-index: 1;

    animation: fadeInTerminal 1s ease-out;

}

@keyframes fadeInTerminal {

    from { opacity: 0; transform: translateY(-20px); }

    to { opacity: 1; transform: translateY(0); }

}

.terminal-header {

    background-color: #003300; 

    padding: 8px 12px;

    display: flex;

    justify-content: space-between;

    align-items: center;

    border-bottom: 1px solid #00ff00;

    color: #ffffff;

    font-size: 1.1em;

    text-shadow: 0 0 5px #00ff00;

}

.terminal-title {

    letter-spacing: 2px;

}

.terminal-controls span {

    display: inline-block;

    width: 12px;

    height: 12px;

    margin-left: 5px;

    border-radius: 50%;

    cursor: pointer;

}

.control-btn.minimize { background-color: #ffbd2e; border: 1px solid #e1a11a;}

.control-btn.maximize { background-color: #27c93f; border: 1px solid #1aab2a;}

.control-btn.close { background-color: #ff5f57; border: 1px solid #e2453c;}

.terminal-body {

    padding: 20px;

    min-height: 300px; 

}

#hacker-typer-output {

    background-color: rgba(0, 0, 0, 0.5);

    padding: 10px;

    margin-bottom: 20px;

    border: 1px dashed #00ff00;

    height: 80px;

    overflow-y: auto; 

    font-size: 1.2em;

    line-height: 1.4;

    white-space: pre-wrap; 
    word-wrap: break-word; 

    text-shadow: 0 0 3px #00ff00;

}

.prompt {

    color: #00cc00; 

}


.cursor {

    display: inline-block;

    background-color: #00ff00;

    width: 10px;

    animation: blink 1s step-end infinite;

    margin-left: 3px;

}

@keyframes blink {

    from, to { background-color: transparent; }

    50% { background-color: #00ff00; }

}

.form-toggle {

    margin-bottom: 20px;

    text-align: center;

}

.form-toggle button {

    background: none;

    border: 1px solid #00ff00;

    color: #00ff00;

    padding: 8px 15px;

    margin: 0 5px;

    cursor: pointer;

    font-family: inherit;

    font-size: 1em;

    transition: all 0.3s ease;

    text-transform: uppercase;

}

.form-toggle button.active,

.form-toggle button:hover {

    background-color: #00ff00;

    color: #0a0a0a;

    box-shadow: 0 0 10px #00ff00;

}

form {

    display: none; 

    flex-direction: column;

    gap: 15px;

    animation: formFadeIn 0.5s ease;

}

form.active-form {

    display: flex; 

}

@keyframes formFadeIn {

     from { opacity: 0; }

     to { opacity: 1; }

}

form h2 {

    text-align: center;

    margin-bottom: 10px;

    color: #ffffff;

    text-shadow: 0 0 8px #00ff00;

    letter-spacing: 1px;

}

.input-group {

    display: flex;

    flex-direction: column;

    gap: 5px;

}

label {

    font-size: 1.1em;

    color: #00ff00;

    text-shadow: 0 0 3px #00ff00;

}

input[type="text"],

input[type="password"],

input[type="email"] {

    background-color: rgba(0, 0, 0, 0.7);

    border: 1px solid #00cc00;

    color: #00ff00;

    padding: 10px;

    font-family: inherit;

    font-size: 1.1em;

    outline: none;

    caret-color: #00ff00; 

    transition: border-color 0.3s, box-shadow 0.3s;

}

input[type="text"]::placeholder,

input[type="password"]::placeholder,

input[type="email"]::placeholder {

    color: #009900; 

    opacity: 0.7;

}

input[type="text"]:focus,

input[type="password"]:focus,

input[type="email"]:focus {

    border-color: #00ff00;

    box-shadow: 0 0 10px rgba(0, 255, 0, 0.5);

}

form button[type="submit"] {

    background-color: #00ff00;

    color: #0a0a0a;

    border: none;

    padding: 12px;

    font-family: inherit;

    font-size: 1.2em;

    cursor: pointer;

    transition: background-color 0.3s, box-shadow 0.3s;

    margin-top: 10px;

    text-transform: uppercase;

    font-weight: bold;

    letter-spacing: 1px;

    text-shadow: none;

}

form button[type="submit"]:hover {

    background-color: #33ff33; 

    box-shadow: 0 0 15px #00ff00;

}

.message {

    min-height: 20px; 

    margin-top: 10px;

    text-align: center;

    font-size: 1.1em;

    font-weight: bold;

    text-shadow: 0 0 5px currentColor; 

}

.message.success {

    color: #00ff00; 

}

.message.error {

    color: #ff4444;

}

.message.processing {

    color: #ffff00; 

}

@media (max-width: 600px) {

    .terminal-window {

        width: 95%;

        margin-top: 20px;

        margin-bottom: 20px;

    }

    .terminal-body {

        padding: 15px;

    }

    #hacker-typer-output {

        font-size: 1em;

        height: 60px;

    }

    form h2 {

        font-size: 1.2em;

    }

    label, input, form button[type="submit"] {

        font-size: 1em;

    }

}*/
</style>