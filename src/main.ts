import { createApp } from "vue";
import router from "@/router";
import pinia from "@/store";
import App from "@/App.vue";
import "@/styles/initialize.scss";

const app = createApp(App);
app.use(pinia);
app.use(router);
app.mount("#app");