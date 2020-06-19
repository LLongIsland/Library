<template>
  <div class="container">
    <el-form ref="formInline" :model="formInline" :rules="ruleInline" inline>
      <el-form-item prop="account">
        <el-input  type="text" v-model="formInline.account" placeholder="学号">
        <i class="el-icon-user" slot="prepend"></i>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button round size="mini" type="primary" @click="handleSubmit('formInline')">查找</el-button>
      </el-form-item>
      <el-form-item>
        <el-button round size="mini" type="primary" @click="modal1 = true">新添用户</el-button>
      </el-form-item>
    </el-form>
    <Table border :columns="columns7" :data="data6"></Table>
    <br>
    <Page :total="total" :page-size="10" @on-change="changePage"></Page>

    <Modal
      v-model="modal1"
      title="新添用户"
      @on-ok="ok('formItem2')"
      >
      <Form ref="formItem2" :model="formItem2" :rules="ruleItem2" :label-width="80">
        <FormItem label="学号" prop="account">
          <Input v-model="formItem2.account" placeholder=""></Input>
        </FormItem>
        <FormItem label="姓名" prop="name">
          <Input v-model="formItem2.name" placeholder=""></Input>
        </FormItem>
        <FormItem label="性别" prop="sex">
          <RadioGroup v-model="formItem2.sex">
            <Radio label="1">男</Radio>
            <Radio label="0">女</Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="身份" prop="condi">
          <Select v-model="formItem2.condi">
            <Option value="0">学生</Option>
            <Option value="1">图书管理员</Option>
          </Select>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>
<script type="es6">
  export default {
    name: 'UserManage',
    data () {
      return {
        total: '',
        condi: '',
        modal1: false,
        formInline: {
          account: ''
        },
        formItem2: {
          account: '',
          name: '',
          sex: '男',
          condi: '0'
        },
        ruleItem2: {
          account: [{
            required: true,
            message: '请填写账号！',
            trigger: 'blur'
          }],
          name: [{
            required: true,
            message: '请填写学生姓名',
            trigger: 'blur'
          }]
        },
        columns7: [
          {
            title: '学号',
            key: 'account',
            render: (h, params) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'person'
                  }
                }),
                h('strong', params.row.account)
              ]);
            }
          },
          {
            title: '姓名',
            key: 'name'
          },
          {
            title: '性别',
            key: 'sex'
          },
          {
            title: '身份',
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
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.remove(params.index)
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        data6: []
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
        if(this.data6[index].condi==0){
          this.condi='学生'
        }else{
          this.condi='图书管理员'
        }
        if(this.data6[index].condi==0){
          this.sex='女'
        }else{
          this.sex='男'
        }
        this.$Modal.info({
          title: '用户信息',
          content: `姓名：${this.data6[index].name}<br>年龄：${this.data6[index].sex}<br>学号：${this.data6[index].account}<br>身份：${this.condi}`
        })
      },
      remove (index) {
        this.data6.splice(index, 1);
      },
      request (currentPage){
        var that=this
        this.$http.get(that.GLOBAL.serverPath + '/excise/getAllReaders',
          {
            params:{
              account: that.formInline.account
            }
          }
        ).then(function (res) {
          console.log(res.data.content)
          that.total=res.data.content.length
          that.data6=res.data.content
        }).catch((e) => {
          this.$Message.fail('网络有误！')
        })
      },
      changePage: function(page){
        this.request(page)
      },
      ok (name) {
        var that=this
        this.$refs[name].validate((valid) => {
          if (valid) {
            that.$http.post(that.GLOBAL.serverPath + '/excise/addReader',
              {
                account: that.formItem2.account,
                name: that.formItem2.name,
                sex: Number(that.formItem2.sex),
                condi: Number(that.formItem2.condi),
              }
            ).then(function (res) {
              console.log(res.data.success)
              if(res.data.success){
                that.$Message.success('新增成功')
                that.formInline.account=''
                that.request(1)
              }else{
                that.$Message.error('已存在该学号的用户')
              }

            }).catch((e) => {
              that.$Message.fail('网络有误！')
            })
          }
        })
      }
    }
  }
</script>
