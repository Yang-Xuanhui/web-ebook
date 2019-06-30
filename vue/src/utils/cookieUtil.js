// 设置cookie
function setCookie (cname, cvalue, exdays) {
  var d = new Date()
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000))
  var expires = 'expires=' + d.toUTCString()
  console.info(cname + '=' + cvalue + '; ' + expires)
  document.cookie = cname + '=' + cvalue + '; ' + expires
  console.info(document.cookie)
}
// 获取cookie
function getCookie (cname) {
  var name = cname + '='
  var ca = document.cookie.split(';')
  // console.log('获取cookie,现在循环')
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i]
    // console.log(c)
    while (c.charAt(0) === ' ') c = c.substring(1)
    if (c.indexOf(name) !== -1) {
      return c.substring(name.length, c.length)
    }
  }
  return ''
}
// 清除cookie
function clearCookie () {
  setCookie('username', '', -1)
  setCookie('role', '', -1)
}
function checkCookie () {
  var user = getCookie('username')
  if (user !== '') {
    alert('Welcome again ' + user)
  } else {
    user = prompt('Please enter your name:', '')
    if (user !== '' && user != null) {
      setCookie('username', user, 365)
    }
  }
}

export {
  setCookie,
  getCookie,
  clearCookie,
  checkCookie
}
