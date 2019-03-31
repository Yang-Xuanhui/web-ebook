function hideLayer(id) {
    var obj = document.getElementById(id);
    obj.style.display = "none";
}

function showLayer(id, width, height) {
    var obj  = document.getElementById(id);
    var winWidth = document.documentElement.clientWidth;
    var winHeight = document.documentElement.clientHeight;
    var offsetTop = document.documentElement.offsetTop;
    var left = (winWidth - width)/2;
    var top  = (winHeight - height)/2 + offsetTop;
    obj.style.top = top + "px";
    obj.style.left = left + "px";
    obj.style.display = "block";
}
