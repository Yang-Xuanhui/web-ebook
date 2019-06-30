<template>
        <!--用table方式呈现-->
        <div>
            <el-button class="addBook" round icon="el-icon-circle-plus-outline"
                       v-on:click="addBook()">增加书籍</el-button>
            <el-table :data="orderedBooks"
                      class="tb-edit"
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
                        prop="writer"
                        label="作者"
                        sortable
                ></el-table-column>
                <el-table-column
                        prop="isbn"
                        label="ISBN"
                        sortable
                >
                </el-table-column>
                <el-table-column
                        prop="price"
                        label="价格"
                        sortable
                >
                </el-table-column>
                <el-table-column
                        prop="storage"
                        label="库存"
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
                <el-table-column
                        label="修改">
                    <template slot-scope="scope">
                        <el-button size="small" type="text" v-on:click="updateBook(scope.row)" >修改</el-button>
                    </template>
                </el-table-column>
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
import UploadBook from '../components/UploadBook'

export default {
  name: 'adminBook.vue',
  components: {UploadBook},
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
  data () {
    return {
      empForm: {
        cname: '',
        name: '',
        writer: '',
        brief: '',
        img: '',
        isbn: '',
        price: 0,
        rate: 0,
        storage: 0,
        sales: 0,
        book_intro: '',
        writer_intro: '',
        book_comment: ''
      }
    }
  },
  methods: {
    getDetail: function (name) {
      loadBook(name, this)
    },
    addBook: function () {
      this.$router.push({name: 'uploadBook', params: {Book: this.empForm}})
    },
    updateBook: function (oldBook) {
      this.$router.push({name: 'uploadBook', params: {Book: oldBook}})
    },
    /* 删除行，从当前排序下的数组中删除内容，没有根本删除 */
    DeleteBook: function (name) {
      console.log(name)
      let book = {}
      book.name = name
      this.$axios.post('http://localhost:8011/books/delete', book)
        .then(res => {
          if (res.data === '删除成功') {
            // console.log('delete success')
            this.orderedBooks.splice(name, 1)
          } else {
            console.log('delete fail')
          }
        })
    }
  }
}
</script>

<style scoped>
  .addBook {
    margin: 20px 80px;
    float: right;
  }
  .cover{
    width: 100%;
  }
</style>
