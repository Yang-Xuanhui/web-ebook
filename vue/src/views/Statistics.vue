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
                <el-button round @click="getWithDate" class="checkOrder">统计订单</el-button>
            </div>
            <div v-show="isEmpty">
                <p>没有可查看的订单</p>
            </div>
            <div v-show="!isEmpty">
                <!--<Chart v-bind:Dates="list.dates" v-bind:Money="list.money"></Chart>-->
            </div>
        </div>
    </div>
</template>

<script>
import GoLogin from '../components/GoLogin'
import {getCookie} from '../utils/cookieUtil'

export default {
  name: 'Statistics',
  components: {GoLogin},
  data () {
    return {
      date: '',
      search: '',
      list: {
        orders: [],
        dates: [],
        money: [],
        empty: true
      }

    }
  },
  methods: {
    getWithDate: function () {
      let json = {'start': '', 'end': ''}
      if (this.date !== '') {
        json.start = this.date[0]
        json.end = this.date[1]
      }
      this.$axios.post('http://localhost:8011/orders/readOrder', json)
        .then(res => {
          this.$set(this.list, 'orders', res.data)
          this.$set(this.list, 'dates', this.getData('date'))
          this.$set(this.list, 'money', this.getData('money'))
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getData: function (type) {
      let num = this.list.orders.length
      if (num > 0) {
        let data = new Array(num)
        let i = 0
        for (i = 0; i < num; i++) {
          if (type === 'date') {
            data[i] = this.list.orders[i].info.date
          } else if (type === 'money') {
            data[i] = this.list.orders[i].info.total_money
          }
        }
        return data
      }
      return []
    }
  },
  computed: {
    isLogin: function () {
      return getCookie('username')
    },
    isEmpty: function () {
      if (this.list.orders === null || this.list.orders.length === 0) {
        return true
      }
      return false
    }
  }
}
</script>

<style scoped>
    .checkOrder{
        margin-left: 10px;
    }
</style>
