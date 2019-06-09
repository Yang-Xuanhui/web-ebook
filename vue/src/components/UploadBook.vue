<template>
    <div>
      <el-button class="goback" round icon="el-icon-back" v-on:click="$router.go(-1)">返回</el-button>
      <el-form ref="form"
                :model="form"
                :rules="rules"
                class="uploadForm">
        <el-form-item label="书名" prop="cname">
          <el-input v-model="form.cname"></el-input>
        </el-form-item>
        <el-form-item label="英文名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="简介(不超过100字)" prop="brief">
          <el-input v-model="form.brief" type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"></el-input>
        </el-form-item>
        <el-form-item label="书籍介绍(不超过300字)" prop="info">
          <el-input v-model="form.book_intro" type="textarea"
                    :autosize="{ minRows: 2, maxRows: 5}"></el-input>
        </el-form-item>
        <el-form-item label="书籍评价(不超过300字)" prop="info">
          <el-input v-model="form.book_comment" type="textarea"
                    :autosize="{ minRows: 2, maxRows: 5}"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="writer">
          <el-input v-model="form.writer"></el-input>
        </el-form-item>
        <el-form-item label="作者介绍(不超过300字)" prop="info">
          <el-input v-model="form.writer_intro" type="textarea"
                    :autosize="{ minRows: 2, maxRows: 5}"></el-input>
        </el-form-item>
        <el-form-item label="封面">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8011/books/upload"
            name = "image"
            :http-request="handleUploadCover"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="ISBN" prop="isbn">
          <el-input v-model="form.isbn"></el-input>
        </el-form-item>
        <el-form-item label="单价" required>
          <el-col :span="5">
              <el-input v-model.number="form.price" type="number"
              :step="0.01"
              :min="0"></el-input>
          </el-col>
          <el-col :span="1">元</el-col>
        </el-form-item>
        <el-form-item label="评分">
          <el-col :span="18">
            <el-slider
              v-model="form.rate"
              show-input
              :min="0"
              :max="5"
              :step="0.1"
            >
            </el-slider>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="库存" required>
              <el-input v-model.number="form.storage" type="number" :min="0"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="销量" required>
              <el-input v-model.number="form.sales" type="number" :min="0"></el-input>
            </el-form-item>
          </el-col>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
export default {
  name: 'UploadBook',
  data () {
    return {
      imageUrl: '',
      form: this.$route.params.Book,
      rules: {
        cname: [
          {required: true, message: '请输入书名', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入英文名', trigger: 'blur'}
        ],
        writer: [
          {required: true, message: '请输入作者', trigger: 'blur'}
        ],
        brief: [
          { max: 100, message: '不超过100字符', trigger: 'blur' }
        ],
        info: [
          { max: 300, message: '不超过300字符', trigger: 'blur' }
        ],
        isbn: [
          {required: true, message: '请输入ISBN编号', trigger: 'blur'}
        ]
      }
    }
  },
  props: ['Book'],
  methods: {
    handleUploadCover (a) {
      console.log(a.file)
      let formData = new FormData()
      formData.append('image', a.file)
      let config = {
        header: {
          'Content-Type': 'multipart/form-data'
        }
      }
      this.$axios.post('http://localhost:8011/books/upload', formData, config).then((res) => {
        console.log(res)
        this.form.img = res.data
        this.imageUrl = res.data
      })
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://localhost:8011/books/add', this.form).then((res) => {
            console.log(res.data)
            if (res.data === '没有增加图书的权限') {
              this.$message.error('没有增加图书的权限')
            } else if (res.data === '更新书籍') {
              this.$message.success('更新书籍')
            } else if (res.data === '成功增加书籍') {
              this.$message.success('增加书籍成功')
            }
          })
        } else {
          this.$message.error('未完成表格，不可提交')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
  .goback{
    float:right;
    margin-top: 10px;
    margin-left: 30px;

  }
  .avatar-uploader .el-upload {
    border: 1px dashed #818181;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .uploadForm {
    width: 70%;
    text-align: center;
  }
</style>
