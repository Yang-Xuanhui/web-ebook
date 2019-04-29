<template>
    <div>
        <p>请先登录</p>
        <el-button round @click="goLogin">去登录</el-button>
    </div>
</template>

<script>
    export default {
        name: "GoLogin",
        methods:{
            goLogin:function () {
                this.$router.replace('/login')
            }
        }
    }
</script>

<style scoped>

</style>