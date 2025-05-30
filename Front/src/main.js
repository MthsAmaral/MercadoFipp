
import {createApp} from "vue";
import App from "./App.vue";
import router from "./components/routes/index.js";

import 'vue3-toastify/dist/index.css';
import Vue3Toastify from 'vue3-toastify';

const app = createApp(App);


app.use(Vue3Toastify, {
  autoClose: 3000,
  position: "top-right"
});

app.use(router);
app.mount('#app');






