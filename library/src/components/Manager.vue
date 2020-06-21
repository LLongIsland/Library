<template>

  <div class="layout">
    <router-link to="/">返回登录界面</router-link>
    <el-row type="flex">
      <el-col span="5" class="layout-menu-left">
      <el-menu  active-name="1-1" theme="light" width="auto" :open-names="['1']">
        <div class="layout-logo-left">
          <h2 style="color:gray;">图书馆</h2>
        </div>
        <el-submenu name="1">
          <template slot="title">
            <Icon type="ios-navigate"></Icon>
            欢迎你，图书管理员:{{msg}}
          </template>
          <el-menu-item name="1-1" @click.native="lookBook"><span>图书管理</span></el-menu-item>
          <el-menu-item name="1-2" @click.native="lookRecord"><span>借阅记录</span></el-menu-item>
        </el-submenu>
      </el-menu>
      </el-col>
      <el-col span="19">
      <div class="layout-header"></div>
      <div class="layout-breadcrumb">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item href="#">{{one_nav}}</el-breadcrumb-item>
          <el-breadcrumb-item href="#">{{two_nav}}</el-breadcrumb-item>
          <el-breadcrumb-item>{{three_nav}}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="layout-content">
        <div class="layout-content-main">
          <template id="bookManage"></template>
          <template id="recordManage"></template>
          <component :is="currentView"></component>
        </div>
      </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import bookManage from '../components/BookManage.vue'
  import recordManage from '../components/RecordManage.vue'
  import Button from 'iview/src/components/button/button'
  export default {
    name: 'Manager',
    data () {
      return {
        msg: 'haha',
        one_nav: '主页',
        two_nav: '后台管理',
        three_nav: '图书管理',
        currentView: 'bookManage'
      }
    },
    mounted () {
      this.msg = window.localStorage.getItem('username')
    },
    methods: {
      lookBook () {
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '图书管理'
        this.currentView = 'bookManage'
      },
      lookRecord () {
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '借阅记录'
        this.currentView = 'recordManage'
      }
    },
    components: {
      Button,
      bookManage: bookManage,
      recordManage: recordManage
    }
  }
</script>
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    margin-top:-60px;
  }
  .layout-breadcrumb{
    padding: 10px 15px 0;
    text-align:left;
  }
  .layout-content{
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
  }
  .layout-content-main{
    padding: 10px;
  }
  .layout-copy{
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }
  .layout-menu-left{
    background: #ffffff;
  }
  .layout-header{
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  .layout-logo-left{
    width: 90%;
    height: 30px;
    background: #f3f5f8;
    border-radius: 3px;
    margin: 15px auto;
  }
  Button{
    color:white;
  }
</style>
