<template>
    <div>
        <!--排序、搜索栏 card和table切换键-->
        <el-row class="sort-search" type="flex" justify="space-between">
            <el-col :span="12">
                <el-button-group >
                    <el-button id="name" type="success"
                               v-on:click="setOrder('cname')" >
                        <span>书名</span>
                        <i class="el-icon-sort-up"></i>
                        <i class="el-icon-sort-down"></i>
                    </el-button>
                    <el-button id="writer" type="success"
                               v-on:click="setOrder('writer')">
                        <span>作者</span>
                        <i class="el-icon-sort-up"></i>
                        <i class="el-icon-sort-down"></i>
                    </el-button>
                    <el-button id="storage" type="success"
                               v-on:click="setOrder('storage')">
                        <span>库存</span>
                        <i class="el-icon-sort-up"></i>
                        <i class="el-icon-sort-down"></i>
                    </el-button>
                    <el-button id="sales" type="success"
                               v-on:click="setOrder('sales')">
                        <span>销量</span>
                        <i class="el-icon-sort-up"></i>
                        <i class="el-icon-sort-down"></i>
                    </el-button>
                </el-button-group>
            </el-col>
            <el-col :span="6">
                <el-input v-model="search" style="width: 250px"
                          placeholder="请输入书名、作者或ISBN" prefix-icon="el-icon-search"/>
            </el-col>
            <el-col :span="6" v-show="isAdmin">
                <el-switch
                        v-model="checked"
                        active-text="列表"
                        inactive-text="卡片">
                </el-switch>
            </el-col>
        </el-row>
        <!--图书浏览内容用card方式呈现-->
        <div v-show="!checked">
            <el-card class="book-card"
                     v-for="(book) in orderedBooks"
                     v-bind:key="book.name"
                     shadow="hover">
                <img :src="require('../static/img/'+book.img)" class="cover"/>
                <div class="book-infor">
                    <ul>
                        <li class="book-name"><span>{{book.cname}}</span></li>
                        <li class="other-infor">作者：{{book.writer}}</li>
                        <li class="other-infor">ISBN：{{book.isbn}}</li>
                        <li class="other-infor">库存：{{book.storage}}</li>
                        <li class="other-infor">销量：{{book.sales}}</li>
                        <li class="price">&yen; <span>{{book.price}}</span></li>
                    </ul>

                    <el-input-number v-model="book.amount"
                                     :min="0" :max=book.storage
                                     label="number"
                                     size="small" class="input-number"></el-input-number>
                    <el-button size="small" type="success" @click="addToCart(book.bid,book.amount)">加入购物车</el-button>
                    <el-button size="small" type="text" v-on:click="getDetail(book.name)" >查看详情</el-button>
                </div>
            </el-card>
        </div>
        <!--管理员可视界面-->
        <div v-show="isAdmin && checked">
            <AdminBook v-bind:books="this.books" v-bind:orderedBooks="this.orderedBooks"></AdminBook>
        </div>
    </div>
</template>

<script>
/* 从后端get书籍详情 */
import {loadData} from '../api/loadData.js'
import {getCookie} from '../utils/cookieUtil'
import {bookInOrder} from '../api/bookApi'
import {addCart} from '../api/cartApi'
import AdminBook from '../components/AdminBook'

export default {
  name: 'Table',
  components: {
    AdminBook
  },
  data () {
    return {
      search: '',
      Sort: {
        sort_type: '',
        order: ''
      },
      checked: false
    }
  },
  props: {
    /* 图书内容从父组件获得 */
    books: {
      type: Array,
      required: true
    }
  },
  methods: {
    getDetail: function (name) {
      loadData(name, this)
    },
    addToCart: function (bid, amount) {
      addCart(bid, amount, this)
    },
    /* 升降序切换 */
    setOrder: function (type) {
      let order = this.Sort.order
      if (order === 'asc') {
        this.$set(this.Sort, 'order', 'desc')
      } else {
        this.$set(this.Sort, 'order', 'asc')
      }
      this.$set(this.Sort, 'sort_type', type)
    }
  },
  computed: {
    /* 图书排序并筛选 */
    orderedBooks: function () {
      return bookInOrder(this.books, this.Sort, this.search)
    },
    isAdmin: function () {
      let username = getCookie('username')
      if (username === 'admin') {
        return true
      } else {
        return false
      }
    }
  }

}

</script>

<style scoped>
    .sort-search{
        margin-top: 10px;
        margin-left: 10px;
        font-size:16px;
        font-family: "Microsoft YaHei";
    }
    .book-card{
        width: 550px;
        height: 250px;
        float: left;
        margin-left: 50px;
        margin-top: 20px;
    }
    .cover{

        width:40%;
        float: left;
    }
    .book-infor {

        padding-left: 180px;
        font-family: "SimSun";
        text-align: left;
    }
    .book-name{
        font-size: large;
    }
    .book-name span{
        font-weight: bold;
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
    .input-number{
        width:40%;
        margin-right: 20px;
    }
</style>
