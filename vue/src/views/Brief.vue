<template>
    <div class="brief">
        <Table v-bind:books="list.books"/>
    </div>
</template>

<script>
    // @ is an alias to /src
    import Table from './Table'

    export default {
        name: 'brief',
        components: {
            Table
        },
        data(){
            return{
                list:{
                    books:[]
                }
            }
        },
        methods:{
        },
        beforeCreate:function () {
            this.$axios.get('http://localhost:8011/books/booklist')
                .then((response) =>{
                    let books = response.data;

                    this.$set(this.list,"books",books)
                    //console.log(books)
                    //console.log(response.data);
                })
                .catch((err)=>{
                    console.log(err)
                })
        }
    }
</script>