// import loadUser from './loadData'
function banUser (user, that) {
  let json = {'username': user.username}
  that.$axios.post('http://localhost:8011/users/ban', json)
    .then(res => {
      let msg = res.data
      if (msg === '禁用用户' || msg === '解禁用户') {
        that.$notify({
          title: '成功',
          message: msg,
          type: 'success'
        })
        user.enable = user.enable ^ 1
      } else {
        that.$notify.error({
          title: '错误',
          message: '无解禁/禁用用户的权限'
        })
      }
    })
    .catch((err) => {
      console.log(err)
    })
}

export {
  banUser
}
