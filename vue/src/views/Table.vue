<template>
    <div>
        <!--排序、搜索栏 card和table切换键-->
        <el-row class="sort-search" type="flex" justify="space-between">
            <el-col :span="12">
                <el-button-group >
                    <el-button id="name" type="success"
                               v-on:click="setOrder('name')" >
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
            <el-col :span="6">
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
                     v-bind:key="book.name">
                <img :src="book.cover" class="cover"/>
                <div class="book-infor">
                    <ul>
                        <li class="book-name"><span>{{book.name}}</span></li>
                        <li class="other-infor">作者：{{book.writer}}</li>
                        <li class="other-infor">ISBN：{{book.ISBN}}</li>
                        <li class="other-infor">库存：{{book.storage}}</li>
                        <li class="other-infor">销量：{{book.sales}}</li>
                        <li class="price">&yen; <span>{{book.price}}</span></li>
                    </ul>

                    <el-input-number v-model="book.number"
                                     :min="0" :max=book.storage
                                     label="number"
                                     size="small" class="input-number"></el-input-number>
                    <el-button size="small" type="success">加入购物车</el-button>
                    <el-button size="small" type="text" v-on:click="routerTo(book)" >查看详情</el-button>
                </div>
            </el-card>
        </div>
        <!--用table方式呈现-->
        <div v-show="checked">
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
                                      size="small" v-model="scope.row.name"
                                      placeholder="please input"
                                      v-on:blur="inputblur"
                                      v-focus></el-input>
                            <span v-if="!scope.row.nameflag">{{scope.row.name}}</span>
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
                                      size="small" v-model="scope.row.ISBN"
                                      placeholder="please input"
                                      v-on:blur="inputblur"
                                      v-focus></el-input>
                            <span v-if="!scope.row.isbnflag">{{scope.row.ISBN}}</span>
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
                        <el-button size="small" type="text" v-on:click="routerTo(scope.row)" >查看详情</el-button>
                            </template>
                    </el-table-column>
                <el-table-column
                        label="删除">
                    <template slot-scope="scope">
                        <el-button size="small" type="text" v-on:click="Delete(scope.$index)" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

        </div>
    </div>
</template>

<script>
    let _ = require('lodash');
    export default {
        name: "Table",
        data(){
            return{
                search:'',
                Sort:{
                    sort_type:'',
                    order:''
                },
                checked:false,
            }
        },
        props:{
            /* 图书内容从父组件获得*/
            books:{
                type:Array,
                required:true,
            }
        },
        methods:{
            /* 升降序切换*/
            setOrder :function(type) {
                let order = this.Sort.order;
                if(order==='asc'){

                    this.$set(this.Sort,'order','desc')
                }
                else{

                    this.$set(this.Sort,'order','asc')
                }
                this.$set(this.Sort,'sort_type',type);

            },
            /* 路由，跳转到对应详情页*/
            routerTo :function (book) {
                this.$router.push({ name: 'detail', params: { Book: book, Name:book.Name }});
            },
            /* 根据该列的label设置对应的flag */
            dblhandleCurrentChange :function (row, column) {
                switch (column.label) {
                    case "书名":
                        row.nameflag = true;
                        break;
                    case "作者":
                        row.writerflag = true;
                        break;
                    case "ISBN":
                        row.isbnflag = true;
                        break;
                    case "价格":
                        row.priceflag = true;
                        break;
                    case "库存":
                        row.storageflag = true;
                        break;
                    case "销量":
                        row.salesflag = true;
                        break;
                }
            },
            /* 修改完内容后失去对焦，修改flag，内容从input形式变为显示修改后内容*/
            inputblur() {
                let tableD = this.books;
                tableD.forEach(function (item) {
                    item.nameflag = false;
                    item.writerflag = false;
                    item.isbnflag = false;
                    item.priceflag = false;
                    item.storageflag = false;
                    item.salesflag = false;
                });
            },
            /*删除行，从当前排序下的数组中删除内容，没有根本删除*/
            Delete(index){
                this.orderedBooks.splice(index,1);
            },
        },
        computed: {
            /* 图书排序并筛选*/
            orderedBooks: function () {
                let search = this.search;
                return (_.orderBy(this.books, this.Sort.sort_type,this.Sort.order))
                    .filter(
                        data=>!search ||
                            data.name.includes(search) ||
                            data.writer.includes(search) ||
                            data.ISBN.includes(search));
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