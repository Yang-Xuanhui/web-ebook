<template>
    <div id="myChart" style='width：600px;height:600px'></div>
</template>

<script>
    // vue文件中引入echarts工具
    let echarts = require('echarts/lib/echarts')
    require('echarts/lib/chart/line')
    // 以下的组件按需引入
    require('echarts/lib/component/tooltip')   // tooltip组件
    require('echarts/lib/component/title')   //  title组件
    require('echarts/lib/component/legend')  // legend组件

    export default {
        name: "Chart",
        data() {
            return {
            };
        },
        props: {
            Dates:{
                type:Array
            },
            Money:{
                type:Array
            },
            option: {
                type: Object,
                //Object类型的prop值一定要用函数return出来，不然会报错。原理和data是一样的，
                //使用闭包保证一个vue实例拥有自己的一份props
                default() {
                    return {
                        title: {
                            text: "vue-Echarts"
                        },
                        legend: {
                            data: ["销量"]
                        },
                        xAxis: {
                            data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子","tuoxie"]
                        },
                        series: [
                            {
                                name: "销量",
                                type: "line",
                                data: [5, 20, 36, 10, 10, 70]
                            }
                        ]
                    };
                }
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                let myChart = echarts.init(document.getElementById('myChart'))
                myChart.setOption(this.option)
            }
        }
    }
</script>

<style scoped>

</style>