<template>
    <div>
      <el-date-picker
        v-model="date"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        value-format="yyyy-MM-dd HH:mm:ss">
      </el-date-picker>
      <el-button round @click="getBooksales" class="checkOrder">查看图书销量统计</el-button>
      <el-table :data="list.books"
                stripe
                style="width: 100%"
                highlight-current-row>
        <el-table-column
          type="index"
        >
        </el-table-column>
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
          sortable
        >
        </el-table-column>
        <el-table-column
          prop="sales"
          label="销量"
          sortable
        ></el-table-column>
        <el-table-column
          label="详情">
          <template slot-scope="scope">
            <el-button size="small" type="text" v-on:click="getDetail(scope.row.name)" >查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
import {getBookSales} from '../api/statistic'
import {loadBook} from '../api/loadData'

export default {
  name: 'BookSales',
  data () {
    return {
      date: '',
      list: {
        books: []
      }
    }
  },
  methods: {
    getBooksales: function () {
      getBookSales(this)
    },
    getDetail: function (name) {
      loadBook(name, this)
    }
  }
}
</script>

<style scoped>
  .checkOrder{
    margin-left: 10px;
  }
  .cover{
    width:100%
  }
</style>
