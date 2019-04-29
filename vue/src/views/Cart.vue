<template>
    <div class="cart">
        <div v-show="!isLogin">
            <goLogin/>
        </div>
        <div v-show="isLogin">
            <div class="operation">
                <el-button type="danger" round class="buy" @click="BuySome">购买</el-button>
                <el-button round class="buy" @click="DeleteSome">删除</el-button>
            </div>
            <el-table :data="list.carts"
                      class="tb-edit"
                      stripe
                      style="width: 100%"
                      highlight-current-row
                      @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
                <el-table-column
                        label="图书">
                    <template slot-scope="scope">
                        <el-row type="flex" justify="">
                            <el-col :span="4" class="cover">
                                <img :src="require('../static/img/'+scope.row.img)" class="cover"/>
                            </el-col>
                            <el-col :span="20" class="info">
                                <ul v-show="!scope.row.isDelete">
                                    <li class="book-name"><span>{{scope.row.cname}}</span></li>
                                    <li class="other-infor">库存：{{scope.row.storage}}</li>
                                    <li class="price">&yen; {{scope.row.price}}</li>
                                    <li>
                                        <el-input-number v-model="scope.row.amount"
                                                         :min="1" :max=scope.row.storage label="number"
                                                         size="mini" class="input-number"></el-input-number>
                                    </li>
                                    <li>
                                        <el-button size="mini"
                                                   @click="UpdateAmount(scope.row)">保存数量</el-button>
                                    </li>
                                </ul>
                                <ul v-show="scope.row.isDelete">
                                    <li class="book-name"><span>{{scope.row.cname}}</span></li>
                                    <li class="price">图书已下架</li>
                                </ul>
                            </el-col>
                        </el-row>
                    </template>
                </el-table-column>
                <el-table-column
                        label="总价"
                        align="right">
                    <template slot-scope="scope">
                        <div class="price"
                             v-show="!scope.row.isDelete">&yen; <span>{{scope.row.price*scope.row.amount}}</span></div>
                    </template>

                </el-table-column>
                <el-table-column
                        label="操作"
                        align="right">
                    <template slot-scope="scope">
                        <el-button v-show="!scope.row.isDelete" size="small"
                                   round
                                   v-on:click="getDetail(scope.row.name)" >查看详情</el-button>
                        <el-button size="small"
                                   round
                                   v-on:click="DeleteOne(scope.row.cid)" >删除</el-button>
                        <el-button v-show="!scope.row.isDelete"
                                   size="middle"
                                   type="danger"
                                   round
                                   plain
                                   v-on:click="BuyOne(scope.row)"
                        >购买</el-button>

                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import {loadData} from "../api/loadData";
    import {listCart} from "../api/cartApi";
    import {buyOne} from "../api/cartApi";
    import {buySome} from "../api/cartApi";
    import {deleteOne} from "../api/cartApi";
    import {deleteSome} from "../api/cartApi";
    import {updateAmount} from "../api/cartApi";
    import {getCookie} from "../utils/cookieUtil";

    import GoLogin from "../components/GoLogin"
    export default {
        name: "Cart",
        components:{GoLogin},
        data(){
            return{
                list:{
                    carts:[]
                },
                multipleSelection:[]
            }
        },
        created: function () {
            listCart(this)
        },
        methods:{
            getDetail:function(name){
                loadData(name,this)
            },
            UpdateAmount:function(row){
                updateAmount(row.cid,row.amount)
            },
            /* delete row from the table, but not delete from the origin data*/
            DeleteOne:function(id){
                deleteOne(id,this)
            },
            DeleteSome:function(){
                deleteSome(this.multipleSelection,this)
            },
            BuyOne:function(row){
                buyOne(row.cid,row.amount,this)
            },
            BuySome:function(){
                buySome(this.multipleSelection,this)
            },
            routerTo :function (book) {
                this.$router.push({ name: 'detail', params: { Book: book, Name:book.Name }});
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                console.log(this.multipleSelection)
            }
        },
        computed:{
            isLogin:function(){
                return getCookie("username");
            },
        }
    }
</script>

<style scoped>
    .cart{
        margin-top: 20px;
    }
    .operation{
        float:right;
    }
    .buy{
        width: 100px;
        margin-top: 30px;
    }
    .cover{
        width:100%;
    }
    .info li{
        margin-top: 10px;
    }
    .book-name{
        font-size: large;
        font-weight: bold;
    }
    .book-name span{

    }
    .other-infor{
        font-size: 14px;
        margin-top: 5px;
        color: rgba(9, 39, 51, 0.86);
    }
    .price{
        color: #ff86a5;
        font-family: "Microsoft YaHei";
        font-size:18px;
    }
    .price span{
        font-size: 25px;
        font-weight: bold;
    }
</style>