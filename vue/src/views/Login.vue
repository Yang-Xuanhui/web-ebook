<template>
    <div>
        <div class="login-wrap" v-show="showLogin">

            <el-col :span="8" justify="center" >
                <h3>登录</h3>
                <p v-show="showTishi">{{tishi}}</p>
                <el-input placeholder="请输入用户名" v-model="username"></el-input>
                <el-input placeholder="请输入密码" v-model="password" show-password></el-input>
                <el-button >登录</el-button>
                <span >没有账号？马上注册</span>
            </el-col>
    </div>

        <div class="register-wrap" v-show="showRegister">
            <h3>注册</h3>
            <p v-show="showTishi">{{tishi}}</p>
            <input type="text" placeholder="请输入用户名" v-model="newUsername">
            <input type="password" placeholder="请输入密码" v-model="newPassword">
            <el-button >注册</el-button>
            <span >已有账号？马上登录</span>
        </div>
    </div>
</template>


<style>
    .login-wrap{text-align:center;}
    input{display:block; width:250px; height:40px; line-height:40px; margin:0 auto; margin-bottom: 10px; outline:none; border:1px solid #888; padding:10px; box-sizing:border-box;}
    span{cursor:pointer;}
</style>

<script>
    export default{
        name: 'Login',
        data(){
            return{
                showLogin: true,
                showRegister: false,
                showTishi: false,
                tishi: '',
                username: '',
                password: '',
                newUsername: '',
                newPassword: ''
            }
        }
    }
</script>