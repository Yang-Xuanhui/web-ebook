<template>
    <div>
      <div>
        <el-table :data="newUsers"
                  class="tb-edit"
                  stripe
                  style="width: 100%"
                  highlight-current-row>
          <el-table-column
            prop="username"
            label="用户名">
          </el-table-column>
          <el-table-column
            prop="email"
            label="邮箱">
          </el-table-column>
          <el-table-column
            label="身份">
            <template slot-scope="scope">
              <span v-show="scope.row.role==1">用户</span>
              <span v-show="scope.row.role==0">管理员</span>
            </template>
          </el-table-column>
          <el-table-column
            label="状态">
            <template slot-scope="scope">
              <span v-show="scope.row.enable==1">正常</span>
              <span v-show="scope.row.enable==0" class="ban">禁用</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button v-on:click="ban(scope.row)" size="small" round>
                <span v-show="scope.row.enable==1">禁用</span>
                <span v-show="scope.row.enable==0">解禁</span>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
</template>

<script>
import {loadUser} from '../api/loadData'
import {getCookie} from '../utils/cookieUtil'
import {banUser} from '../api/user_manage'

export default {
  name: 'Admin',
  data () {
    return {
      list: {
        users: []
      }
    }
  },
  methods: {
    ban: function (user) {
      banUser(user, this)
    }
  },
  created: function () {
    if (this.isAdmin) {
      loadUser(this)
    }
  },
  computed: {
    isAdmin: function () {
      let role = getCookie('role')
      if (role === 'admin') {
        return true
      } else {
        return false
      }
    },
    newUsers: function () {
      return this.list.users
    }
  }
}
</script>

<style scoped>
.ban{
  color:red;
}
</style>
