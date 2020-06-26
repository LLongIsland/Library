<template>
  <div class="container">
    <Table border :columns="columns7" :data="data6" style="margin: 20px"></Table>
    <br>
    <Page :total="total" :page-size="10" @on-change="changePage"></Page>
  </div>
</template>
<script type="es6">
  export default {
    name: 'MyRecord',
    data() {
      return {
        total: '',
        condi: '',
        modal1: false,
        modal2: false,
        currIndex: 0,//最近被点击添加编号副本的图书编号
        formInline: {
          raccount: ''
        },
        columns7: [
          {
            title: '编号',
            key: 'id',
          },
          {
            title: '书名',
            key: 'atitle'
          },
          {
            title: '借阅者学号',
            key: 'raccount'
          },
          {
            title: '借阅者姓名',
            key: 'rname'
          },
          {
            title: '借阅时间',
            key: 'borrowTime'
          },
          {
            title: '借阅情况',
            key: 'condi'
          }
        ],
        data6: [],
        data7: [] //存放从后台请求过来的借阅记录
      }
    },
    mounted() {
      this.request(1);
    },
    methods: {
      request(currentPage) {
        var that = this
        this.$http.get(that.GLOBAL.serverPath + '/excise/getAllBorrowRecords',
          {
            params: {
              raccount: window.localStorage.getItem('account'),
            }
          }
        ).then(function (res) {
          that.total = res.data.content.length
          that.data6 = []
          that.data7 = res.data.content
          that.data7.forEach((e) => {
            let obj = {}
            obj.id = e.id
            obj.atitle = e.atitle
            obj.raccount = e.raccount
            obj.rname = e.rname
            obj.borrowTime = (new Date(Number(e.borrowTime))).toLocaleString()
            obj.condi = e.condi
            if (e.condi == 1) {
              obj.condi = '已归还'
            } else {
              var time = new Date().getTime();
              console.log(time)
              if (time < e.limitTime) {
                var restDay = Math.ceil((e.limitTime - time) / 86400000)
                obj.condi = '未归还，剩余' + restDay + '天'
              } else {
                var delayDay = Math.ceil((time - e.limitTime) / 86400000)
                obj.condi = '逾期' + delayDay + '天'
              }
            }
            that.data6.push(obj)
          })
        })
      },
      changePage: function (page) {
        this.request(page)
      },

    }
  }
</script>

