import axios from 'axios'
axios.defaults.withCredentials=true
function buyOne(cid,amount,cart) {
    let oneOrder = new Array(1);
    oneOrder[0]={"cart_id":cid,"amount":amount};
    let json = {"cartlist":oneOrder};
    console.log(json);
    axios.post("http://localhost:8011/carts/buy",json)
        .then(res=>{
            cart.$set(cart.list,"carts",res.data);
            cart.$notify({
                title: '成功',
                message: '购买成功',
                type: 'success'
            });
        })
        .catch((err)=>{
                console.log(err)
        })
}

function buySome(orders,cart) {
    if(orders.length==0){
        cart.$notify.error({
            title: '错误',
            message: "没有选中书籍"
        });
        return;
    }
    let list = new Array();
    for(var i =0;i<orders.length;i++){
        let order = {"cart_id":orders[i].cid,"amount":orders[i].amount}
        list[i] = order;
    }
    let json = {"cartlist":list};
    axios.post("http://localhost:8011/carts/buy",json)
        .then(res=>{
            cart.$set(cart.list,"carts",res.data);
            cart.$notify({
                title: '成功',
                message: '购买成功',
                type: 'success'
            });
        })
        .catch((err)=>{
            console.log(err)
        })
}

function deleteOne(id,cart) {
    let oneItem = new Array(1);
    oneItem[0]=id
    let json = {"cartlist":oneItem};
    axios.post("http://localhost:8011/carts/delete",json)
        .then(res=>{
            cart.$set(cart.list,"carts",res.data);
            cart.$notify({
                title: '成功',
                message: '成功删除',
                type: 'success'
            });
        })
        .catch((err)=>{
            console.log(err)
        })
}

function deleteSome(items,cart) {
    if(items.length==0){
        cart.$notify.error({
            title: '错误',
            message: "没有选中书籍"
        });
        return;
    }
    let list = new Array();
    for(var i =0;i<items.length;i++){
        list[i]=items[i].cid;
    }
    let json = {"cartlist":list};
    axios.post("http://localhost:8011/carts/delete",json)
        .then(res=>{
            cart.$set(cart.list,"carts",res.data);
            cart.$notify({
                title: '成功',
                message: '购买删除',
                type: 'success'
            });
        })
        .catch((err)=>{
            console.log(err)
        })
}
function updateAmount(cid,amount) {
    let json={"cart_id":cid,"amount":amount};
    axios.post("http://localhost:8011/carts/update",json)
        .then(res=>{
            console.log(res.data)
        })
        .catch((err)=>{
            console.log(err)
        })
}

function listCart(cart) {
    axios.post("http://localhost:8011/carts/list")
        .then(res=>{
            cart.$set(cart.list,"carts",res.data);
        })
        .catch((err)=>{
            console.log(err)
        })
}

function addCart(bid,amount,cart) {
    if(amount >0){
        let json = {"book_id":bid,"amount":amount};
        let msg="";
        axios.post("http://localhost:8011/carts/add",json)
            .then(
                res=>{
                    msg = res.data;
                    if(msg==="add to cart"){
                        cart.$notify({
                            title: '成功',
                            message: '加入购物车',
                            type: 'success'
                        });
                    }
                    else{
                        cart.$notify.error({
                            title: '错误',
                            message: msg
                        });
                    }
                }
            )
            .catch((err)=>{
                console.log(err)
            })

    }
    else{
        cart.$notify({
            title: '警告',
            message: '数量不能为0',
            type: 'warning'
        });
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