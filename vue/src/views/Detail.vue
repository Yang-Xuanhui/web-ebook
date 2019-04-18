<template>
    <div class="detail">
        <div>

        </div>

        <div>
            <el-row>
                <el-col :span="6">
                    <img class="cover" :src='image'/>
                </el-col>
                <el-col :span="14" class="book-infor">
                    <ul>
                        <li class="book-name"><span>{{book.cname}}</span></li>
                        <li><hr size="1"></li>
                        <li class="brief-intro">{{book.brief}}</li>
                        <li>
                            <el-row :gutter="20">
                                <el-col :span="8">
                                        <p class="other-infor">作者：{{book.writer}}</p>
                                        <p class="other-infor">ISBN：{{book.isbn}}</p>
                                        <p class="other-infor">库存：{{book.storage}}</p>
                                        <p class="other-infor">销量：{{book.sales}}</p>
                                        <el-rate
                                                v-model="book.rate"
                                                disabled
                                                show-score
                                                text-color="#ff9900"
                                                score-template="{value}">
                                        </el-rate>
                                </el-col>
                                <el-col :span="16">
                                    <div class="price">
                                        &yen; <span>{{book.price}}</span>
                                    </div>
                                    <el-row>
                                        <el-col :span="12">
                                            <el-input-number v-model="orderitems.number"
                                                             :min="0" :max=book.storage label="number"
                                                              class="input-number"></el-input-number>
                                        </el-col>
                                        <el-col :span="12">
                                            <el-button round type="success">加入购物车</el-button>
                                        </el-col>
                                    </el-row>
                                </el-col>
                            </el-row>
                        </li>
                    </ul>
                </el-col>
                <el-col :span="4">
                    <el-button class="goback" round icon="el-icon-back" v-on:click="$router.go(-1)">返回</el-button>
                </el-col>
            </el-row>
        </div>
        <hr size="1">
        <div>
            <el-tabs type="border-card" class="Detail">
                <el-tab-pane label="内容简介">{{book.book_intro}}</el-tab-pane>
                <el-tab-pane label="作者简介">{{book.writer_intro}}</el-tab-pane>
                <el-tab-pane label="评价">{{book.book_comment}}</el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                image:this.$route.params.Image,
                book: this.$route.params.Book,
                orderitems:{
                    number:0
                },
            }
        },
        props:['Image','Book'],
        computed:{
        },
        methods:{
        },
    }
</script>
<style scoped>
    .goback{
        float:right;
        margin-top: 10px;
        margin-left: 30px;

    }
    .cover{
        padding: 10px;
        width:100%;
    }
    .book-infor{
        font-family: "Microsoft YaHei";
        text-align: left;
    }
    .book-name{
        font-size: 30px;
        font-weight: bolder;
        margin-bottom: 20px;
    }
    .brief-intro{
        font-family: "KaiTi";
        font-weight: bolder;
        font-size: 20px;
        margin-bottom: 30px;
        white-space: pre-line;
    }
    .other-infor{
        font-size: 13px;
        color:rgba(9, 39, 51, 0.86);
        margin-bottom: 15px;
    }
    .price{
        padding: 20px;
        font-size:20px;
        color: #ff86a5;
        font-weight: bolder;
    }
    .price span{
        font-size:50px
    }
    .Detail{
        font-family: "Microsoft YaHei";
        text-align: left;
        width:80%;
        margin:0 auto;
        white-space: pre-line;
        margin-bottom: 50px;
    }
</style>