<template>
    <div>
        <div class="login" v-show="!isLogin">
            <goLogin/>
        </div>
        <div class="order" v-show="isLogin">
            <el-row>
              <el-col :span="12">
                <el-date-picker
                  v-model="date"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd HH:mm:ss">
                </el-date-picker>
                <el-button round @click="getWithDate" class="checkOrder">查看订单</el-button>
              </el-col>
              <el-col :span="8" v-show="isAdmin">
                <el-input v-model="search" style="width: 300px"
                          placeholder="请输入用户名" prefix-icon="el-icon-search"/>
              </el-col>
            </el-row>
            <div v-show="isEmpty">
                <p>没有可查看的订单</p>
            </div>
            <div v-show="!isEmpty">
                <el-row type="flex" justify="end"
                        v-show="date" class="orderInfo">
                  <el-col :span="8">累计消费
                    <span>{{this.money}}</span> 元
                  </el-col>
                </el-row>
                <el-card class="order-card"
                         v-for="(order) in searchOrder"
                         v-bind:key="order.info.oid">
                    <div slot="header" class="clearfix">
                        <el-row type="flex" justify="space-between" class="orderInfo">
                            <el-col v-show="isAdmin">
                              用户名：<span>{{order.info.user}}</span>
                            </el-col>
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
                                    <img :src="scope.row.img" class="cover"/>
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
import {getWithDate, deleteOrder} from '../api/orderApi'
import GoLogin from '../components/GoLogin'

export default {
  name: 'Order',
  components: {GoLogin},
  data () {
    return {
      date: '',
      search: '',
      list: {
        orders: [],
        empty: true
      }

    }
  },
  methods: {
    getWithDate: function () {
      getWithDate(this)
    },
    routerTo: function (book) {
      this.$router.push({name: 'detail', params: { Book: book, Name: book.Name }})
    },
    deleteOrder (index) {
      deleteOrder(index, this)
    }
  },
  computed: {
    isLogin: function () {
      return getCookie('username')
    },
    isAdmin: function () {
      let role = getCookie('role')
      if (role === 'admin') {
        return true
      } else {
        return false
      }
    },
    isEmpty: function () {
      if (this.list.orders === null || this.list.orders.length === 0) {
        return true
      }
      return false
    },
    startDate: function () {
      if (this.date === '' || this.date === null) {
        return ' '
      } else {
        return this.date[0]
      }
    },
    endDate: function () {
      if (this.date === '' || this.date === null) {
        return ' '
      } else {
        return this.date[1]
      }
    },
    money: function () {
      let total = 0
      let orders = this.list.orders
      let i = 0
      for (i; i < orders.length; i++) {
        total = total + orders[i].info.total_money
      }
      return total.toFixed(2)
    },
    searchOrder: function () {
      if (this.isAdmin) {
        return this.list.orders.filter(data => !this.search ||
          data.info.user.includes(this.search))
      } else {
        return this.list.orders
      }
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
        margin: 10px 20px;
        alignment: right;
        color: #23a393;
        font-weight: bolder;
    }
    .checkOrder{
        margin-left: 10px;
    }
</style>
