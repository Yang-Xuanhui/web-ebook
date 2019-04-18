<template>
    <el-row type="flex" justify="center" class="login">
        <el-form :model="user" label-width="80px">
            <el-form-item label="用户名">
                <el-input v-model="user.name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
            <el-input v-model="user.password" type="password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="success" round @click="login">登录</el-button>
            </el-form-item>
        </el-form>
    </el-row>
</template>
<script>
    export default {
        methods: {
            /*login () {
                this.$router.replace('/')
            }*/
            login:function() {
                //var userParams = this.$qs.stringify(this.User);
                /* var userParams = JSON.stringify(this.User);*/
                this.$axios.post("http://localhost:8011/login",this.user).then(res=>{
                    alert(res.data);
                });
            }
        },
        data () {
            return {
                user: {}
            }
        }
    }
</script>
<style>
    .login{
        margin-top: 20px;
    }
</style>