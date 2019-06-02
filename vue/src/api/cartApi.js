function buyOne (cid, amount, that) {
  let oneOrder = new Array(1)
  oneOrder[0] = {'cart_id': cid, 'amount': amount}
  let json = {'cartlist': oneOrder}
  console.log(json)
  that.$axios.post('http://localhost:8011/carts/buy', json)
    .then(res => {
      that.$set(that.list, 'carts', res.data)
      that.$notify({
        title: '成功',
        message: '购买成功',
        type: 'success'
      })
    })
    .catch((err) => {
      console.log(err)
    })
}

function buySome (orders, that) {
  if (orders.length === 0) {
    that.$notify.error({
      title: '错误',
      message: '没有选中书籍'
    })
    return
  }
  let list = []
  for (var i = 0; i < orders.length; i++) {
    let order = {'cart_id': orders[i].cid, 'amount': orders[i].amount}
    list[i] = order
  }
  let json = {'cartlist': list}
  that.$axios.post('http://localhost:8011/carts/buy', json)
    .then(res => {
      that.$set(that.list, 'carts', res.data)
      that.$notify({
        title: '成功',
        message: '购买成功',
        type: 'success'
      })
    })
    .catch((err) => {
      console.log(err)
    })
}

function deleteOne (id, that) {
  let oneItem = new Array(1)
  oneItem[0] = id
  let json = {'cartlist': oneItem}
  that.$axios.post('http://localhost:8011/carts/delete', json)
    .then(res => {
      that.$set(that.list, 'carts', res.data)
      that.$notify({
        title: '成功',
        message: '成功删除',
        type: 'success'
      })
    })
    .catch((err) => {
      console.log(err)
    })
}

function deleteSome (items, that) {
  if (items.length === 0) {
    that.$notify.error({
      title: '错误',
      message: '没有选中书籍'
    })
    return
  }
  let list = []
  for (var i = 0; i < items.length; i++) {
    list[i] = items[i].cid
  }
  let json = {'cartlist': list}
  that.$axios.post('http://localhost:8011/carts/delete', json)
    .then(res => {
      that.$set(that.list, 'carts', res.data)
      that.$notify({
        title: '成功',
        message: '购买删除',
        type: 'success'
      })
    })
    .catch((err) => {
      console.log(err)
    })
}
function updateAmount (cid, amount, that) {
  let json = {'cart_id': cid, 'amount': amount}
  that.$axios.post('http://localhost:8011/carts/update', json)
    .then(res => {
      console.log(res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

function listCart (that) {
  that.$axios.post('http://localhost:8011/carts/list')
    .then(res => {
      that.$set(that.list, 'carts', res.data)
    })
    .catch((err) => {
      console.log(err)
    })
}

function addCart (bid, amount, that) {
  if (amount > 0) {
    let json = {'book_id': bid, 'amount': amount}
    let msg = ''
    that.$axios.post('http://localhost:8011/carts/add', json)
      .then(
        res => {
          msg = res.data
          if (msg === '加入购物车') {
            that.$notify({
              title: '成功',
              message: msg,
              type: 'success'
            })
          } else {
            that.$notify.error({
              title: '错误',
              message: msg
            })
          }
        }
      )
      .catch((err) => {
        console.log(err)
      })
  } else {
    that.$notify({
      title: '警告',
      message: '数量不能为0',
      type: 'warning'
    })
  }
}
export {
  buyOne,
  buySome,
  updateAmount,
  deleteOne,
  deleteSome,
  listCart,
  addCart
}
