<template>
  <div class="container">
    <el-form ref="formInline" :model="formInline" inline>
      <el-form-item prop="account">
        <el-input type="text" v-model="formInline.title" placeholder="书名">
        <i class="el-icon-search" slot="prepend"></i>
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
    name: 'SearchBook',
    data () {
      return {
        total: '',
        condi: '',
        modal1: false,
        modal2: false,
        currIndex: 0,//最近被点击添加编号副本的图书编号
        formInline: {
          title: ''
        },
        formItem2: {
          title: '',
          author: '',
          publisher: '',
          publishtime: '',
          descri: ''
        },
        ruleItem2: {
          title: [{
            required: true,
            message: '请填写书名！',
            trigger: 'blur'
          }],
          author: [{
            required: true,
            message: '请填书籍作者',
            trigger: 'blur'
          }],
          publisher: [{
            required: true,
            message: '请填出版社',
            trigger: 'blur'
          }],
          publishtime: [{
            required: true,
            message: '请填写出版时间'
          }],
          descri: [{
            required: true,
            message: '请填书籍描述',
            trigger: 'blur'
          }]
        },
        formItem3: {
          num: ''
        },
        ruleItem3: {
          num: [{
            required: true,
            message: '请填写副本数量！',
            trigger: 'blur'
          }]
        },
        columns7: [
          {
            title: '编号',
            key: 'aid',
            render: (h, params) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'document-text'
                  }
                }),
                h('strong', params.row.aid)
              ]);
            }
          },
          {
            title: '书名',
            key: 'title'
          },
          {
            title: '作者',
            key: 'author'
          },
          {
            title: '出版社',
            key: 'publisher'
          },
          {
            title: '出版时间',
            key: 'publishtime'
          },
          {
            title: '可借副本',
            key: 'num'
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
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small',
                    disabled: this.data6[params.index].unBorrowed
                  },
                  on: {
                    click: () => {
                      this.borrow(params.index)
                    }
                  }
                },'借阅')
              ]);
            }
          }
        ],
        data6: [],
        data7: []
      }
    },
    mounted(){
      this.request(1);
    },
    methods: {
      handleSubmit(account) {
        this.request(1)
      },
      show (index) {
        this.$Modal.info({
          title: '书籍信息',
          width: '1100',
          content: `书名：${this.data6[index].title}<br>作者：${this.data6[index].author}<br>出版社：${this.data6[index].publisher}<br>出版时间：${this.data6[index].publishtime}<br>副本数量：${this.data6[index].num}<br>介绍：${this.data6[index].descri}`
        })
      },
      borrow (index) {
        //this.data6.splice(index, 1);
        var that=this
        this.$http.post(that.GLOBAL.serverPath + '/excise/borrow',
          {
            aid: that.data6[index].aid,
            rid: window.localStorage.getItem('userId'),
            raccount: window.localStorage.getItem('account'),
            borrowTime:new Date().getTime(),
            limitTime:new Date().getTime()+86400000*30
          }
        ).then(function (res) {
          if(res.data.success){
            this.$Message.success('借阅成功,注意归还日期！')
            that.data6[index].num=that.data6[index].num-1
            if(that.data6[index].num == 0){
              this.data6[index].unBorrowed = true
            }
          }else{
            this.$Message.error('借阅失败，请重试!')
          }
        })
      },
      request (currentPage){
        var that=this
        this.$http.get(that.GLOBAL.serverPath + '/excise/getAllAlbums',
          {
            params:{
              title:that.formInline.title
             }
          }
        ).then(function (res) {
          console.log(res.data.content)
          that.total=res.data.content.length
          that.data6=[]
          that.data7=res.data.content
          that.data7.forEach((e) => {
            let obj={}
            obj.aid = e.id
            obj.title = e.title
            obj.author = e.author
            obj.publisher = e.publisher
            obj.publishtime = e.publishtime
            obj.num = e.num
            obj.descri = e.descri
            var s=''
            /*
            e.subalbums.forEach((item)=>{
              if( item.condi==0 ){
                count++
                s=s+item.number+','
              }
            })*/
            if(e.num == 0){
              obj.unBorrowed = true
            }else{
              obj.unBorrowed = false
            }
            obj.suba = s
            that.data6.push(obj)
          })
        })
      },
      changePage: function(page){
        this.request(page)
      }
    }
  }
</script>
