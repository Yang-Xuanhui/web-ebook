<template>
    <div class="order">
        <el-input v-model="search" style="width: 250px"
                  placeholder="请输入书名" prefix-icon="el-icon-search"/>
        <el-table :data="searchOrders"
                  class="tb-edit"
                  stripe
                  style="width: 100%"
                  highlight-current-row>
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="书名"
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
                    prop="number"
                    label="数量"
                    sortable>
            </el-table-column>
            <el-table-column
                    label="详情">
                <template slot-scope="scope">
                    <el-button size="small"
                               type="text"
                               v-on:click="routerTo(scope.row)" >查看详情</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    label="删除">
                <template slot-scope="scope">
                    <el-button size="small"
                               type="text"
                               v-on:click="Delete(scope.$index)" >删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "Order",
        data(){
            return{
                search:'',
                orders:[
                    {
                        name:"白夜行",
                        Name:"baiyexing",
                        cover: require('../static/img/white.jpg'),
                        writer:"东野圭吾",
                        ISBN:"9787544291163",
                        price:59.60,
                        storage:300,
                        sales:4500000,
                        nameflag:false,
                        writerflag:false,
                        isbnflag:false,
                        priceflag:false,
                        storageflag:false,
                        salesfalg:false,
                        number:2,
                        rate:4.9,
                        brief:"\"我的人生就像一直行走在白夜里。我的新年愿望是能在白天走路。\"\n" +
                        "\"我的天空里没有太阳，总是黑夜，但并不黑暗，因为有东西代替了太阳。\"",
                        detail:"《白夜行》被普遍视为日本作家东野圭吾作品的无冕之王，一经推出即成为东野圭吾的长篇小说代表作，中文版销量超450万册，与《嫌疑人X的献身》《恶意》《解忧杂货店》并称为东野圭吾四大杰作。\n" +
                        "\n" +
                        "《白夜行》是东野圭吾当之无愧的鸿篇巨制，全书故事跨越19年时间，登场人物超过50个，草蛇灰线伏脉千里，情节线索纵横交错又丝丝入扣，用一桩离奇命案牵出案件相关人跨越19年惊心动魄的故事、悲凉的爱情、吊诡的命运、令人发指的犯罪、白描又生动的社会图景、复杂人性的对决与救赎，读来令人叹为观止。\n" +
                        "\n" +
                        "1973年，大阪一栋废弃建筑中发现一名遭利器刺死的男子。警方怀疑一个叫西本文代的女人，但缺少证据。不久西本被判定因意外事故死亡，从此案件成谜。此后19年，众多案件相关者的命运出现了离奇的转折，有人走向上流社会，有人在暗夜中游走挣扎。只有一个老警察追查不休，渐渐拼出了惊人的真相。",
                        writer_intro:"东野圭吾，日本作家。\n" +
                        "\n" +
                        "1985年，《放学后》获第31届江户川乱步奖，开始专职写作；\n" +
                        "\n" +
                        "1999年，《白夜行》获周刊文春推理小说榜年度第 1 名，《秘密》获第52届日本推理作家协会奖；\n" +
                        "\n" +
                        "2005年出版的《嫌疑人X的献身》同时获得第134届直木奖、第6届本格推理小说大奖，以及三大推理小说排行榜年度第 1 名；\n" +
                        "\n" +
                        "2008年，《流星之绊》获第43届新风奖；\n" +
                        "\n" +
                        "2009年出版的《新参者》获两大推理小说排行榜年度第 1 名；\n" +
                        "\n" +
                        "2012年，《解忧杂货店》获第7届中央公论文艺奖；\n" +
                        "\n" +
                        "2014年，《祈祷落幕时》获第48届吉川英治文学奖。",
                        comment:"亮司和雪穗为什么活得如此坚强？因为他们已经没有别的生存道路，不会迷茫。不迷茫，才可以那么坚强。——东野圭吾\n" +
                        "\n" +
                        "我花费了很多心思创作《白夜行》，这部作品无法轻易概括。我只想站在旁观者的角度描写一个人的成长经历，主人公的内心就交给读者自由想象。登场人物不知道发生了什么，唯有读者清楚真相。——东野圭吾\n" +
                        "\n" +
                        "《白夜行》将无望却坚守的凄凉爱情和执着而缜密的冷静推理完 美结合，被众多书迷视作东野圭吾作品中的无冕之王。——《北京晚报》",
                    },
                    {
                        name:"123",
                        Name:"123",
                        cover: require('../static/img/white.jpg'),
                        writer:"123",
                        ISBN:"1111111111",
                        price:111,
                        storage:111,
                        sales:111,
                        nameflag:false,
                        writerflag:false,
                        isbnflag:false,
                        priceflag:false,
                        storageflag:false,
                        salesfalg:false,
                        number:1,
                        rate:2.3,
                        brief:"",
                        detail:"",
                        writer_intro:"",
                        comment:"",
                    }
                ]
            }
        },
        methods:{
            routerTo :function (book) {
                this.$router.push({ name: 'detail', params: { Book: book, Name:book.Name }});
            },
            Delete(index){
                this.searchOrders.splice(index,1);
            },
        },
        computed: {
            searchOrders: function () {
                let search = this.search;
                return this.orders.filter(
                            data=>!search ||
                            data.name.includes(search));
            }
        }
    }
</script>

<style scoped>
    .order{
        margin-top: 20px;
    }
</style>