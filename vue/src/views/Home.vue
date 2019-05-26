<template>
    <div class="carousel">
        <el-carousel :interval="4000"
                     type="card"
                     :autoplay="true"
                     height="320px">
            <el-carousel-item v-for="img in imgs" v-bind:key="img.Name">
                <img :src="img.src">
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
export default {
  name: 'home',
  data () {
    return {
      imgs: [
        {
          Name: 'white',
          src: require('../static/img/whiteNights.jpg')
        },
        {
          Name: 'renjianshige',
          src: require('../static/img/renjianshige.jpg')
        },
        {
          Name: 'walden',
          src: require('../static/img/walden.jpg')
        },
        {
          name: 'wukong',
          src: require('../static/img/wukong.jpg')
        }
      ]
    }
  }
}
</script>
<style>
    .carousel{
        width: 80%;
        margin: auto;
        margin-top: 40px;
        box-shadow: 3px 3px 5px #f7e9e3;

    }
    .carousel img{
        width: 60%;
    }
</style>
