<template>
  <div class="layout">
    <el-row>
      <el-button plain  size="mini" style="float:right; margin: 10px">
        <router-link to="/"  >退出登录</router-link>
      </el-button>
    </el-row>
    <el-row type="flex">
      <el-col span="5" class="layout-menu-left">
      <el-menu active-name="1-1" theme="light" width="auto" :open-names="['1']">
        <div class="layout-logo-left">
          <h2 style="color:gray;">图书管理系统</h2>
        </div>
        <el-submenu name="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            欢迎你，{{msg}}同学
          </template>
          <el-menu-item name="1-1" @click.native="searchBook">搜索书籍</el-menu-item>
          <el-menu-item name="1-2" @click.native="lookRecord"><span>借阅记录</span></el-menu-item>
        </el-submenu>
      </el-menu>
      </el-col>
      <el-col span="19">
      <div class="layout-header"></div>
      <div class="layout-breadcrumb">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item href="#">{{one_nav}}</el-breadcrumb-item>
          <el-breadcrumb-item>{{two_nav}}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="layout-content">
        <div class="layout-content-main">
          <template id="searchBook"></template>
          <template id="myRecord"></template>
          <component :is="currentView"></component>
        </div>
      </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import searchBook from '../components/SearchBook.vue'
  import myRecord from '../components/MyRecord.vue'
  import Button from 'iview/src/components/button/button'
  export default {
    name: 'Reader',
    data () {
      return {
        msg: '',
        one_nav: '主页',
        two_nav: '搜索书籍',
        currentView: 'searchBook'
      }
    },
    mounted () {
      this.msg = window.localStorage.getItem('username')
    },
    methods: {
      searchBook () {
        this.one_nav = '主页'
        this.two_nav = '搜索书籍'
        this.currentView = 'searchBook'
      },
      lookRecord () {
        this.one_nav = '主页'
        this.two_nav = '借阅记录'
        this.currentView = 'myRecord'
      }
    },
    components: {
      Button,
      searchBook: searchBook,
      myRecord: myRecord
    }
  }
</script>
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: url("timg.png");
    position: relative;
    margin-top:-60px;

  }
  .layout-breadcrumb{
    position: absolute;
    background-color: rgba(255, 255, 255, 0.67);
    width:240px;
    margin-top: -25px;
    margin-left: 15px;
    border-radius: 5px;
    padding-buttom: 15px;
    padding-top: 10px;
    padding-left: 20px;
    text-align:left;
    height:33px;
  }
  .layout-content{
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
    opacity: 0.9;
  }
  .layout-content-main{
    padding: 10px;
    height:650px;
  }
  .layout-copy{
    text-align: center;
    padding: 10px 0 20px;
    /* color: #9ea7b4; */
  }
  .layout-menu-left{
    /* background: #464c5b; */
    margin-top:18px;
    margin-left:10px;
    border-radius:3px;
    opacity: 0.9;
  }
  .layout-header{
    height: 0px;
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
