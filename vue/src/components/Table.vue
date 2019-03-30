<template>
    <div>
        <el-row type="flex" justify="start" class="sort-search">
            <el-col :span="2">
                <el-button id="name" v-on:click="setOrder('name')">
                    <span>书名</span>
                    <i class="el-icon-sort-up"></i>
                    <i class="el-icon-sort-down"></i>
                </el-button>
            </el-col>
            <el-col :span="2">
                <el-button id="writer" v-on:click="setOrder('writer')">
                    <span>作者</span>
                    <i class="el-icon-sort-up"></i>
                    <i class="el-icon-sort-down"></i>
                </el-button>
            </el-col>
            <el-col :span="2">
                <el-button id="storage" v-on:click="setOrder('storage')">
                    <span>库存</span>
                    <i class="el-icon-sort-up"></i>
                    <i class="el-icon-sort-down"></i>
                </el-button>
            </el-col>
            <el-col :span="2">
                <el-button id="sales" v-on:click="setOrder('sales')">
                    <span>销量</span>
                    <i class="el-icon-sort-up"></i>
                    <i class="el-icon-sort-down"></i>
                </el-button>
            </el-col>

            <el-col>
                <el-input v-model="search" style="width: 300px"
                          placeholder="请输入书名、作者或ISBN" prefix-icon="el-icon-search"/>
            </el-col>
        </el-row>

        <div>

                    <el-card class="book-card"
                             v-for="(book) in orderedBooks"
                             v-bind:key="book.name">
                        <img :src="book.cover" class="cover"/>
                        <div class="book-infor">
                            <p class="book-name">书名：{{book.name}}</p>
                            <p class="other-infor">
                                作者：{{book.writer}}<br/>
                                ISBN：{{book.ISBN}}<br/>
                                库存：{{book.storage}}<br/>
                                销量：{{book.sales}}
                            </p>
                            <el-input-number v-model="book.number"
                                             :min="0" :max=book.storage label="number"></el-input-number>
                            <el-button>加入购物车</el-button>
                            <el-button>查看详情</el-button>
                        </div>
                    </el-card>

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
                books:[
                    {
                        name:'白夜行',
                        cover: require('../assets/img/white.jpg'),
                        writer:'东野圭吾',
                        ISBN:'9787544291163',
                        storage:300,
                        sales:'4500000',
                        number:0
                    },
                    {
                        name:'ccc白夜行1',
                        cover: require('../assets/img/white.jpg'),
                        writer:'a东野圭吾',
                        ISBN:'9787544291163',
                        storage:300,
                        sales:'4500000',
                        number:1
                    },
                    {
                        name:'bbb白夜行2',
                        cover: require('../assets/img/white.jpg'),
                        writer:'c东野圭吾',
                        ISBN:'9787544291163',
                        storage:0,
                        sales:'4500004',
                        number:1
                    },
                    {
                        name:'aaa白夜行3',
                        cover: require('../assets/img/white.jpg'),
                        writer:'b东野圭吾1',
                        ISBN:'9787544291163',
                        storage:400,
                        sales:'4600001',
                        number:1
                    }
                ],
                Sort:{
                    sort_type:'',
                    order:''
                },
                checked:false,
            }
        },
        methods:{
            setOrder :function(type) {
                let order = this.Sort.order;
                if(order==='asc'){

                    this.$set(this.Sort,'order','desc')
                }
                else{

                    this.$set(this.Sort,'order','asc')
                }
                this.$set(this.Sort,'sort_type',type);

            }
        },
        computed: {
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
    }
    .book-card{
        width: 500px;
        height: 270px;
        float: left;
        margin-left: 70px;
        margin-top: 20px;
    }
    .cover{

        width:35%;
        float: left;
    }
    .book-infor {
        padding-left: 220px;
        font-family: "SimSun";
        text-align: left;
    }
    .book-name{
        font-size: large;
    }
</style>