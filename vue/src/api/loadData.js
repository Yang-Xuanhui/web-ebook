function loadBook (name, that) {
  console.log(name)
  that.$axios.get('http://localhost:8011/books/detail', {params: {name: name}})
    .then((response) => {
      let book = response.data
      // console.log(book)
      // console.log(response.data);
      /* 路由，跳转到对应详情页 */
      that.$router.push({name: 'detail', params: {Book: book}})
    })
    .catch((err) => {
      console.log(err)
    })
}

function loadUser (that) {
  that.$axios.post('http://localhost:8011/users/showusers')
    .then((response) => {
      let users = response.data
      if (users !== null) {
        that.$set(that.list, 'users', users)
      }
    })
    .catch((err) => {
      console.log(err)
    })
}

export {
  loadBook,
  loadUser
}
