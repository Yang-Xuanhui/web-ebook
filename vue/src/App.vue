<template>
  <div id="app">
      <el-header id="header">
          <img id="logo" src="./assets/logo.png"/>
          <span>E-BOOK</span>
      </el-header>
      <el-main>
          <el-row type="flex" justify="space-between">
          <el-col :span="24">
              <el-menu router
                       :default-active=$route.path mode="horizontal">
                  <el-menu-item index="/">
                      主页
                  </el-menu-item>
                  <el-menu-item index="/brief">
                      图书浏览
                  </el-menu-item>
                  <el-menu-item index="/cart">
                      购物车
                  </el-menu-item>
                  <el-menu-item index="/order">
                      订单
                  </el-menu-item>
                  <!-- 没有内容
                <el-menu-item index="/about">
                  关于
                </el-menu-item>
                -->
                  <el-menu-item index="/login" id="login">
                      <el-col :span="3" >
                          <!-- 未具体实现-->
                          <el-button index="/login" v-on:click="Login" type="text">
                              注册/登录
                          </el-button>
                      </el-col>
                  </el-menu-item>
              </el-menu>
              <transition name="slide-fade">
                  <router-view/>
              </transition>
          </el-col>
      </el-row>
    </el-main>
  </div>
</template>

<script>
export default {
  name: 'app',
  components: {
  },
    methods:{
      Login :function () {
          this.$router.replace('/login')
      }
    }

}
</script>

<style>

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#logo{
    width: 60px;
    height: 55px;
    float: left;
}
#header{
    background-color: #ffe2e2;
    font-family: "Comic Sans MS";
    font-size: 40px;
    text-align: left;
    color: #20716a;
}
#header span{
    margin-left: 20px;
}
#login{
    float: right;
}
ul
{
  list-style:none;
}
</style>
