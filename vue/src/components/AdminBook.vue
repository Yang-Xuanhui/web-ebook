<template>
        <!--用table方式呈现-->
        <div>
            <el-table :data="orderedBooks"
                      class="tb-edit"
                      stripe
                      style="width: 100%"
                      @cell-dblclick="dblhandleCurrentChange"
                      highlight-current-row>
                <el-table-column
                        type="index"
                >
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="书名"
                        sortable
                >
                    <template slot-scope="scope">
                        <el-input v-if="scope.row.nameflag"
                                  size="small" v-model="scope.row.cname"
                                  placeholder="please input"
                                  v-on:blur="inputblur"
                                  v-focus></el-input>
                        <span v-if="!scope.row.nameflag">{{scope.row.cname}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="writer"
                        label="作者"
                        sortable
                >
                    <template slot-scope="scope">
                        <el-input v-if="scope.row.writerflag"
                                  size="small" v-model="scope.row.writer"
                                  placeholder="please input"
                                  v-on:blur="inputblur"
                                  v-focus></el-input>
                        <span v-if="!scope.row.writerflag">{{scope.row.writer}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="ISBN"
                        label="ISBN"
                        sortable
                >
                    <template slot-scope="scope">
                        <el-input v-if="scope.row.isbnflag"
                                  size="small" v-model="scope.row.isbn"
                                  placeholder="please input"
                                  v-on:blur="inputblur"
                                  v-focus></el-input>
                        <span v-if="!scope.row.isbnflag">{{scope.row.isbn}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="price"
                        label="价格"
                        sortable
                >
                    <template slot-scope="scope">
                        <el-input v-if="scope.row.priceflag"
                                  size="small" v-model="scope.row.price"
                                  placeholder="please input"
                                  v-on:blur="inputblur"
                                  v-focus></el-input>
                        <span v-if="!scope.row.priceflag">{{scope.row.price}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="storage"
                        label="库存"
                        sortable
                >
                    <template slot-scope="scope">
                        <el-input v-if="scope.row.storageflag"
                                  size="small" v-model="scope.row.storage"
                                  placeholder="please input"
                                  v-on:blur="inputblur"
                                  v-focus></el-input>
                        <span v-if="!scope.row.storageflag">{{scope.row.storage}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="sales"
                        label="销量"
                        sortable
                ><template slot-scope="scope">
                    <el-input v-if="scope.row.salesflag"
                              size="small" v-model="scope.row.sales"
                              placeholder="please input"
                              v-on:blur="inputblur"
                              v-focus></el-input>
                    <span v-if="!scope.row.salesflag">{{scope.row.sales}}</span>
                </template></el-table-column>
                <el-table-column
                        label="详情">
                    <template slot-scope="scope">
                        <el-button size="small" type="text" v-on:click="getDetail(scope.row.name)" >查看详情</el-button>
                    </template>
                </el-table-column>
                <!--<el-table-column
                        label="修改">
                    <template slot-scope="scope">
                        <el-button size="small" type="text" v-on:click="Update(scope.row.name)" >修改</el-button>
                    </template>
                </el-table-column>
                -->
                <el-table-column
                        label="删除">
                    <template slot-scope="scope">
                        <el-button size="small" type="text" v-on:click="DeleteBook(scope.row.name)" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
</template>

<script>
import {loadBook} from '../api/loadData'

export default {
  name: 'adminBook.vue',
  props: {
    /* 图书内容从父组件获得 */
    books: {
      type: Array,
      required: true
    },
    orderedBooks: {
      type: Array,
      required: true
    }
  },
  methods: {
    getDetail: function (name) {
      loadBook(name, this)
    },
    /* 删除行，从当前排序下的数组中删除内容，没有根本删除 */
    DeleteBook: function (name) {
      console.log(name)
      let book = {}
      book.name = name
      this.$axios.post('http://localhost:8011/books/delete', book)
        .then(res => {
          if (res.data === true) {
            console.log('delete success')
            this.orderedBooks.splice(name, 1)
          } else {
            console.log('delete fail')
          }
        })
    },
    /* 根据该列的label设置对应的flag */
    dblhandleCurrentChange: function (row, column) {
      switch (column.label) {
        case '书名':
          row.nameflag = true
          break
        case '作者':
          row.writerflag = true
          break
        case 'ISBN':
          row.isbnflag = true
          break
        case '价格':
          row.priceflag = true
          break
        case '库存':
          row.storageflag = true
          break
        case '销量':
          row.salesflag = true
          break
      }
    },
    /* 修改完内容后失去对焦，修改flag，内容从input形式变为显示修改后内容 */
    inputblur () {
      let tableD = this.books
      tableD.forEach(function (item) {
        item.nameflag = false
        item.writerflag = false
        item.isbnflag = false
        item.priceflag = false
        item.storageflag = false
        item.salesflag = false
      })
    }
  }
}
</script>

<style scoped>

</style>
