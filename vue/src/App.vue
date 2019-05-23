<template>
  <div id="app">
      <el-header id="header">
          <img id="logo" src="./assets/logo.png"/>
          <span>E-BOOK</span>
      </el-header>
      <el-main id="main">
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
                  <!-- 未实现
                <el-menu-item index="/statistics">
                  统计
                </el-menu-item>
                -->
                  <el-menu-item index="/login" id="login" v-show="!isLogin">
                  <el-col :span="3" >
                      <el-button index="/login" v-on:click="Login" type="text" >
                          注册/登录
                      </el-button>
                  </el-col>
              </el-menu-item>
                  <el-menu-item id="logout" v-show="isLogin">
                      <el-col :span="3" >
                          <el-dropdown @command="handleCommand">
                                <span class="el-dropdown-link">
                                    Hi {{isLogin}}<i class="el-icon-arrow-down el-icon--right"></i>
                                </span>
                              <el-dropdown-menu slot="dropdown">
                                  <el-dropdown-item command="login">切换用户</el-dropdown-item>
                                  <el-dropdown-item command="logout">退出</el-dropdown-item>
                              </el-dropdown-menu>
                          </el-dropdown>
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
    import {getCookie} from './utils/cookieUtil.js'
    import {clearCookie} from './utils/cookieUtil.js'
export default {
  name: 'app',
  components: {
  },
    methods:{
        Login :function () {
          this.$router.replace('/login')
        },
        handleCommand(command) {
            if(command==="login"){
                clearCookie();
                // 强制刷新页面
                this.$router.go(0);
                this.$router.replace('/login');
            }

            else if(command==="logout"){
                alert("Goodbye "+getCookie("username"));
                clearCookie();
                this.$router.go(0);
                this.$router.replace('/');

            }
        }
    },
    computed:{
        isLogin :function () {
            let user = getCookie("username");
            if(user===""){
                return false;
            }
            else{
                return user;
            }
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
#login,#logout{
    float: right;
}

ul
{
  list-style:none;
}
</style>
