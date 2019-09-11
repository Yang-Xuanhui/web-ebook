function getBookSales (that) {
  let json = {'start': '', 'end': ''}
  if (that.date !== '' && that.date !== null) {
    json.start = that.date[0]
    json.end = that.date[1]
  }
  that.$axios.post('http://localhost:8011/orders/booksales', json)
    .then(res => {
      that.$set(that.list, 'books', res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}
function getConsumption (that) {
  let json = {'start': '', 'end': ''}
  if (that.date !== '' && that.date !== null) {
    json.start = that.date[0]
    json.end = that.date[1]
  }
  that.$axios.post('http://localhost:8011/orders/consumption', json)
    .then(res => {
      that.$set(that.list, 'users', res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

export {
  getBookSales,
  getConsumption
}
