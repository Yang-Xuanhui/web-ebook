import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '../theme/index.css'
import ElementUI from 'element-ui'
import './directive.js'
import axios from 'axios'
Vue.prototype.$http = axios
Vue.prototype.$axios = axios

Vue.use(ElementUI);
Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
