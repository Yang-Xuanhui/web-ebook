import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '../theme/index.css'
import ElementUI from 'element-ui'
import './directive.js'
import axios from 'axios'
import echarts from 'echarts'
Vue.prototype.$echarts = echarts //将echarts注册成Vue的全局属性
Vue.prototype.$axios = axios
axios.defaults.withCredentials=true
Vue.use(ElementUI);
Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
