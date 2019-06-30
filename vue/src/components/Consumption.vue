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
      <el-button round @click="searchConsumption" class="checkOrder">查看用户消费统计</el-button>
      <el-table :data="list.users"
                class="utable"
                stripe
                style="width: 50%"
                highlight-current-row>
        <el-table-column
          type="index"
        >
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          sortable
        >
        </el-table-column>
        <el-table-column
          prop="consumption"
          label="消费总计"
          sortable
        ></el-table-column>
      </el-table>
    </div>
</template>

<script>
import {getConsumption} from '../api/statistic'

export default {
  name: 'Consumption',
  data () {
    return {
      date: '',
      list: {
        users: []
      }
    }
  },
  methods: {
    searchConsumption: function () {
      getConsumption(this)
    }
  }
}
</script>

<style scoped>
  .utable{
    margin: auto;
  }
</style>
