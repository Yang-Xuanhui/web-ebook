function getWithDate (that) {
  let json = {'start': '', 'end': ''}
  if (that.date !== '' && that.date !== null) {
    json.start = that.date[0]
    json.end = that.date[1]
  }
  that.$axios.post('http://localhost:8011/orders/readOrder', json)
    .then(res => {
      that.$set(that.list, 'orders', res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

function deleteOrder (index, that) {
  let json = {'oid': index, 'start': '', 'end': ''}
  if (that.date !== null && that.date !== '') {
    console.log(that.date)
    json.start = that.date[0]
    json.end = that.date[1]
  }

  that.$axios.post('http://localhost:8011/orders/delete', json)
    .then(res => {
      that.$set(that.list, 'orders', res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}
export {
  getWithDate,
  deleteOrder
}
