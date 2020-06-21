<template>

  <div class="hello">
<!--    登录还用不了，这里点进去可以看到页面<br>-->
<!--    <router-link to="/Manager">Manger.vue</router-link><br>-->
<!--    <router-link to="/Reader">Reader.vue</router-link><br>-->
<!--    <router-link to="/Index">Index.vue</router-link><br>-->
    <el-form ref="formItem" :model="formItem" :rules="ruleItem" :label-width="80">
      <el-row>
        <el-col :xs="23" :sm="23" :md="23" :lg="23">
        <h1>{{msg}}</h1>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="23" :sm="23" :md="23" :lg="23">
        <el-form-item prop="account" label="账号">
          <Input type="text" v-model="formItem.account" placeholder="你的注册邮箱"></Input>
        </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="23" :sm="23" :md="23" :lg="23">
        <el-form-item prop="password" label="密码">
          <Input type="password" v-model="formItem.password" placeholder="你的密码"></Input>
        </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="24" :md="24" :lg="24">
        <el-button id="login_btn" v-if="!loading" type="primary" :loading="loading" @click.native="handleSubmit('formItem')">
          登录
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  /* eslint-disable */
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '图书管理系统',
      verifyCode: 'hello',
      formItem: {
        account: '',
        password: '',
      },
      ruleItem: {
        account: [{
          required: true,
          message: '请填写账号！',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请填写密码',
          trigger: 'blur'
        }, {
          type: 'string',
          min: 3,
          message: '密码长度不能小于6位',
          trigger: 'blur'
        }],
      },
      loading: false
    }
  },
  mounted () {
  },
  methods: {
    handleSubmit (name) {
      var that = this
      this.loading = true
      this.$refs[name].validate((valid) => {
        if (valid) {
            that.$http.post(that.GLOBAL.serverPath + '/excise/login',
              {
                email: that.formItem.account,
                password: that.formItem.password
              }
            ).then((res) =>{
              console.log(res.data)
              if (res.data.success) {
                this.$Message.success('登录成功!')
                window.localStorage.setItem('userId', res.data.content.id)
                window.localStorage.setItem('username', res.data.content.name)
	window.localStorage.setItem('account', res.data.content.account)
                window.localStorage.setItem('sex', res.data.content.credit)
                window.localStorage.setItem('condi', res.data.content.condi)
                console.log('hahaha' + res.data.condi)
                if (res.data.content.condi ==2) {
                  this.$router.replace({path: '/index'})
                } else if (res.data.content.condi == 1) {
                  this.$router.replace({path: '/manager'})
                } else {
                  this.$router.replace({path: '/reader'})
                }
              } else {
                this.$Message.error('账号或密码有误！')
                this.loading = false
              }
            })
        } else {
          this.loading = false
        }
      })
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .hello{
    margin: auto;
  }
  h1{
    color:#2D8CF0;
    margin-bottom:20px;
  }
  Form{
    margin:0 auto;
    width:450px;
    border: 2px  grey;
    padding:50px 0;
    /*background-color:red;*/
  }
  #login_btn{
    width:70%;
  }
  .tip{
    margin-top:10px;
    color:darkgrey;
  }
  #verifyCode{
  }
</style>
