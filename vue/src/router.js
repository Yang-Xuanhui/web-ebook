import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Home from './views/Home.vue'

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/brief',
            name: 'brief',
            // route level code-splitting
            // this generates a separate chunk (about.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import(/* webpackChunkName: "about" */ './views/Brief.vue')
        },
        {
            path: '/detail/:Book.id',
            name: 'detail',
            component: () => import('./views/Detail.vue'),
        },
        {
            path: '/cart',
            name: 'cart',
            component: () => import('./views/Cart.vue')
        },
        {
            path: '/order',
            name: 'order',
            component: () => import('./views/Order.vue')
        },
        {
            path: '/statistics',
            name: 'statistics',
            component: () => import('./views/Statistics.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/register',
            name: 'register',
            component: ()=>import('./views/Register.vue')
        }
    ]
})
