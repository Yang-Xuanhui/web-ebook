<template>
    <div>
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="图书销量" name="bookSales">
          <BookSales></BookSales>
        </el-tab-pane>
        <el-tab-pane label="用户消费" name="consumption">
          <Consumption></Consumption>
        </el-tab-pane>
      </el-tabs>
    </div>
</template>

<script>
import BookSales from '../components/BookSales'
import Consumption from '../components/Consumption'

export default {
  name: 'Statistic',
  components: {
    BookSales,
    Consumption
  },
  data () {
    return {
      activeName: 'bookSales'
    }
  }
}
</script>

<style scoped>

</style>
