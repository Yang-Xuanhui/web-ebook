import {setCookie} from '../utils/cookieUtil'

function login (that) {
  that.$axios.post('http://localhost:8011/users/login', that.user)
    .then(res => {
      console.log(that.user)
      if (res.data === 1) { // user
        setCookie('username', that.user.username, 1)
        setCookie('role', 'user')
        that.$router.push({ path: '/' }) // 登录成功之后重定向到首页
        that.$router.go(0)
      } else if (res.data === 2) { // admin
        setCookie('username', that.user.username, 1)
        setCookie('role', 'admin')
        that.$router.push({ path: '/' }) // 登录成功之后重定向到首页
        that.$router.go(0)
      } else if (res.data === -1) {
        that.$notify.error({
          title: '登录失败',
          message: '用户被禁用'
        })
      } else if (res.data === 0) {
        that.$notify.error({
          title: '登录失败',
          message: '用户名或密码错误'
        })
      }
    })
    .catch((err) => {
      console.log(err)
    })
}

export {
  login
}
