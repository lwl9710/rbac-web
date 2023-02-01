import { createApp } from "vue";
import router from "@/router";
import pinia from "@/store";
import App from "@/App.vue";
import "@/styles/global.scss";
import "@/styles/initialize.scss";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";

const app = createApp(App);
app.use(pinia);
app.use(router);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.mount("#app");
