import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import '../theme/index.css'
import ElementUI from 'element-ui'
import './directive.js'
import axios from 'axios'

Vue.prototype.$axios = axios
// 向后端发送http请求时带cookie
axios.defaults.withCredentials = true
Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
