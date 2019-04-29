<template>
    <div>
        <div class="login" v-show="!isLogin">
            <goLogin/>
        </div>
        <div class="order" v-show="isLogin">
            <div class="block">
                <el-date-picker
                        v-model="date"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
                <el-button round @click="getWithDate" class="checkOrder">查看订单</el-button>
            </div>
            <div v-show="isEmpty">
                <p>没有可查看的订单</p>
            </div>
            <div v-show="!isEmpty">
                <div v-show="date" class="orderInfo"><span>{{this.startDate}}-{{this.endDate}}</span>  共消费
                    <span>{{this.money}}</span> 元</div>
                <el-card class="order-card"
                         v-for="(order) in list.orders"
                         v-bind:key="order.info.oid">
                    <div slot="header" class="clearfix">
                        <el-row type="flex" justify="space-between" class="orderInfo">
                            <el-col>
                                订单号：<span>{{order.info.oid}}</span>
                            </el-col>
                            <el-col>
                                下单时间：<span>{{order.info.date}}</span>
                            </el-col>
                            <el-col>
                                订单金额：<span class="money">{{order.info.total_money}}</span>
                            </el-col>
                        </el-row>
                    </div>
                    <div class="text item">
                        <el-table
                                :data="order.items"
                                style="width: 100%">
                            <el-table-column
                                    prop="img"
                                    label="封面"
                                    width="180">
                                <template slot-scope="scope">
                                    <img :src="require('../static/img/'+scope.row.img)" class="cover"/>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="cname"
                                    label="书名"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="amount"
                                    label="数量"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="price"
                                    label="单价">
                            </el-table-column>
                        </el-table>
                    </div>
                    <div>
                        <el-button class="deleteOrder" round @click="deleteOrder(order.info.oid)">删除订单</el-button>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
    import {getCookie} from '../utils/cookieUtil.js'

    import GoLogin from '../components/GoLogin'

    export default {
        name: "Order",
        components: {GoLogin},
        data(){
            return{
                date:'',
                search:'',
                list:{
                    orders:[],
                    empty:true,
                },

            }
        },
        methods:{
            getWithDate:function(){
                let json={"start":"","end":""};
                if(this.date!=='' && this.date!==null){
                    json.start = this.date[0];
                    json.end = this.date[1];
                }
                this.$axios.post("http://localhost:8011/orders/readOrder",json)
                    .then(res=>{
                        this.$set(this.list,"orders",res.data);
                        console.log(this.list.orders);
                    })
                    .catch((err)=>{
                        console.log(err)
                    })
            },
            routerTo :function (book) {
                this.$router.push({ name: 'detail', params: { Book: book, Name:book.Name }});
            },
            deleteOrder(index){
                console.log(index);
                let json={"oid":index,"start":"","end":""};
                if(this.date!==null){
                    json.start = this.date[0];
                    json.end = this.date[1];
                }

                this.$axios.post("http://localhost:8011/orders/delete",json)
                    .then(res=>{
                        this.$set(this.list,"orders",res.data);
                    })
                    .catch((err)=>{
                        console.log(err)
                    })
            },
        },
        computed: {
            isLogin:function(){
                return getCookie("username");
            },
            isEmpty:function () {
                if(this.list.orders===null || this.list.orders.length===0){
                    return true;
                }
                return false;
            },
            startDate:function(){
                if(this.date === '' || this.date === null){
                    return " ";
                }
                else{
                    return this.date[0];
                }
            },
            endDate:function(){
                if(this.date === '' || this.date === null){
                    return " ";
                }
                else{
                    return this.date[1];
                }
            },
            money:function () {
                let total = 0;
                let orders=this.list.orders;
                let i = 0;
                for(i;i<orders.length;i++){
                    total = total + orders[i].info.total_money;
                }
                return total;
            }
        }
    }
</script>

<style scoped>
    .order{
        margin-top: 20px;
    }
    .cover{
        width:100%
    }
    .order-card,.deleteOrder{
        margin-top: 10px;
    }
    .orderInfo span{
        color: #23a393;
        font-weight: bolder;
        font-size:20px;
    }
    .checkOrder{
        margin-left: 10px;
    }
</style>