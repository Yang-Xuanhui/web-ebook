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
import {login} from '../api/login'
export default {
  methods: {

    login: function () {
      login(this)
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
