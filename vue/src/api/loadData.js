import axios from 'axios'
axios.defaults.withCredentials = true
function loadData (name, info) {
  console.log(name)
  axios.get('http://localhost:8011/books/detail', {params: {name: name}})
    .then((response) => {
      let book = response.data
      // console.log(book)
      // console.log(response.data);
      /* 路由，跳转到对应详情页 */
      info.$router.push({name: 'detail', params: {Book: book}})
    })
    .catch((err) => {
      console.log(err)
    })
}

export {
  loadData
}
