import Vue from 'vue'
import Router from 'vue-router'
import Detail from './views/Detail.vue'
import Brief from './views/Brief.vue'

Vue.use(Router);

export default new Router({
    routes : [
        {
            path: '/brief',
            name: 'brief',
            component: Brief,
        },
        {
            path: '/detail',
            name: 'detail',
            component: Detail,
        }
    ]
})