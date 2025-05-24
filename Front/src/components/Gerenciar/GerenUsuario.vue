<template>
    <div class="container mt-4">
        <h1 class="mb-4">Gerenciar Usuários</h1>
        <table class="table table-striped table-hover shadow-sm">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th style="cursor: pointer" @click="ordenarNome()">Nome &#x25B2;</th>
                    <th>Nivel</th>
                    <th>Senha</th>
                    <th colspan="2">Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="usr in usuarios" :key="usr.id">
                    <td>{{ usr.id }}</td>
                    <td>{{ usr.nome }}</td>
                    <td>{{ usr.nivel }}</td>
                    <td>{{ usr.senha }}</td>
                    <td>
                        <button class="btn btn-sm btn-warning" @click="alterar(usr.id)">
                            <i class="bi bi-pencil-fill"></i> Alterar
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-sm btn-danger" @click="this.apagar(usr.id)">
                            <i class="bi bi-trash-fill"></i> Apagar
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="text-end mt-4">
            <button class="btn btn-success" @click="cadastrar">
                <i class="bi bi-plus-circle-fill"></i> Novo Usuario
            </button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'GerenUsuarios',
    data() {
        return {
            usuarios: [],
            ordenado: true
        }
    },
    methods: {
        carregarDados() {
            axios.get('http://localhost:8080/apis/usuario')
                .then(response => this.usuarios = response.data)
                .catch(erro => alert(erro))
        },
        cadastrar() {
            this.$router.push('/form-usuario')  // redireciona para o formulário
        },
        apagar(id, nome) {
            if (window.confirm('Deseja excluir o usuario ' + nome + '?')) {
                axios.delete('http://localhost:8080/apis/usuario/' + id)
                    .then(() => {
                        this.carregarDados()
                        alert('Usuario excluído com sucesso!')
                    })
                    .catch(error => alert(error))
            }
        },
        alterar(id) {
            axios.get('http://localhost:8080/apis/usuario/' + id)
                .then(response => {
                    localStorage.setItem('usuarioParaEditar', JSON.stringify(response.data))
                    this.$router.push('/form-usuario')  // redireciona para o formulário
                })
                .catch(erro => alert(erro))
        },
        ordenarNome() {
            if (this.ordem) {
                this.usuarios.sort((a, b) => a.nome.localeCompare(b.nome));
            } else {
                this.usuarios.sort((a, b) => b.nome.localeCompare(a.nome));
            }
            this.ordem = !this.ordem; // inverte a ordem
        }
    },
    mounted(){
        this.carregarDados()
    }
}
</script>

<style></style>