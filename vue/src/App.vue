<template>
  <div id="app">
    <el-row type="flex" justify="end">
      <el-col :span="3" >
        <!-- 未实现-->
        <el-button index="/login">
          注册/登录
        </el-button>
      </el-col>
    </el-row>
    <el-row type="flex" justify="space-between">
        <el-col :span="24">
          <el-menu class="header" router
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
            <el-menu-item index="/about">
              关于
            </el-menu-item>
          </el-menu>
          <transition name="slide-fade">
            <router-view/>
          </transition>
        </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'app',
  components: {
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
  margin-top: 60px;
}
ul
{
  list-style:none;
}
</style>
