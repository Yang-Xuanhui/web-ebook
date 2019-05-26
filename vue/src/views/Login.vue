<template>
    <el-card class="login">
        <el-form :model="user" label-width="80px">
            <el-form-item label="用户名">
                <el-input v-model="user.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
            <el-input v-model="user.password" type="password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" round @click="login">登录</el-button>
                <el-button round @click="register">注册</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
import {setCookie} from '../utils/cookieUtil.js'
export default {
  methods: {

    login: function () {
      this.$axios.post('http://localhost:8011/users/login', this.user)
        .then(res => {
          console.log(this.user)
          /* admin */
          if (res.data === 2 || res.data === 1) {
            setCookie('username', this.user.username, 1)
            this.$router.go(0)
            this.$router.push({ path: '/' }) // 登录成功之后重定向到首页
          } else if (res.data === -1) {
            this.$notify.error({
              title: '登录失败',
              message: '用户被禁用'
            })
          } else if (res.data === 0) {
            this.$notify.error({
              title: '登录失败',
              message: '用户名或密码错误'
            })
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
    register: function () {
      this.$router.push({name: 'register'})
    }
  },
  data () {
    return {
      isLogin: false,
      user: {}
    }
  }
}
</script>
<style>
    .login{
        width: 40%;
        margin-top: 20px;
        margin-left: auto;
        margin-right: auto;
    }
</style>
