<template>
  <div class="container">
    <el-form ref="formInline" :model="formInline" :rules="ruleInline" inline>
      <el-form-item prop="borrowRecord">
        <el-input type="text" v-model="formInline.raccount" placeholder="借阅者学号">
          <i class="el-icon-user" slot="prepend"></i>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button round size="mini" type="primary" @click="handleSubmit('formInline')">查找</el-button>

      </el-form-item>
    </el-form>
    <Table border :columns="columns7" :data="data6"></Table>
    <br>
    <Page :total="total" :page-size="10" @on-change="changePage"></Page>
  </div>
</template>
<script type="es6">
  export default {
    name: 'UserManage',
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
            title: '归还情况',
            key: 'condi'
          },
          {
            title: '操作',
            key: 'action',
            width: 150,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.show(params.index)
                    }
                  }
                }, '查看'),
                (params.row.condi.indexOf("已")==-1)?h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.reback(params.index)
                    }
                  }
                }, '确认归还'):undefined
              ]);
            }
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
      handleSubmit(account) {
        this.request(1)
      },
      show(index) {
        this.$Modal.info({
          title: '书籍信息',
          content: `书名：${this.data6[index].atitle}<br>书籍编号：${this.data6[index].aid}<br>借阅者学号：${this.data6[index].raccount}<br>借阅者姓名：${this.data6[index].rname}<br>借阅时间：${this.data6[index].borrowTime}<br>应归还时间：${this.data6[index].limitTime}<br>状态：${this.data6[index].condi}`
        })
      },
      reback(index) {
        //this.data6.splice(index, 1);
        var that = this
        this.$http.get(that.GLOBAL.serverPath + '/excise/reback',
          {
            params: {
              id: that.data6[index].id,
              returnTime: new Date().getTime()
            }
          }
        ).then(function (res) {
          if (res.data.success) {
            that.data6.splice(index, 1)
            this.$Message.success('操作成功')
          } else {
            this.$Message.status('操作失败')
          }
        })
      },
      request(currentPage) {
        var that = this
        this.$http.get(that.GLOBAL.serverPath + '/excise/getAllBorrowRecords',
          {
            params: {
              raccount: that.formInline.raccount.toString(),
            }
          },
        ).then(function (res) {
          console.log(res.data.content)
          that.total = res.data.content.length
          that.data6 = []
          that.data7 = res.data.content
          that.data7.forEach((e) => {
            let obj = {}
            obj.id = e.id
            obj.atitle = e.atitle
            obj.aid=e.aid
            obj.raccount = e.raccount
            obj.rname = e.rname
            obj.borrowTime = new Date(Number(e.borrowTime)).toLocaleString()
            obj.condi = e.condi
            if (e.condi == 1) {
              var c = new Date(Number(e.returnTime))
              obj.condi = "已于"+c.toLocaleString()+"归还"
            } else {
              var time = new Date().getTime();
              console.log(time)
              if (time < e.limitTime) {
                obj.condi = '未归还'
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
