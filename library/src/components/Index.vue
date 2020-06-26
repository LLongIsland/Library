<template>
  <div class="layout">
    <el-row type="flex" class="row">
      <el-col span="5" class="layout-menu-left">
      <el-menu active-name="1-1" width="auto" :open-names="['1']">
        <div class="layout-logo-left">
          <h2 style="color:gray;">图书馆</h2>
        </div>
        <el-submenu name="1">
          <template slot="title">
            <Icon type="ios-navigate"></Icon>
            后台管理
          </template>
          <el-menu-item name="1-1" @click.native="lookUser">用户管理</el-menu-item>
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
          <template id="userManage"></template>
          <component :is="currentView"></component>
        </div>
      </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import userManage from '../components/UserManage.vue'
  import Button from 'iview/src/components/button/button'
  export default {
    name: 'Index',
    data () {
      return {
        msg: 'haha',
        one_nav: '主页',
        two_nav: '后台管理',
        three_nav: '用户管理',
        currentView: 'userManage'
      }
    },
    methods: {
      lookUser () {
        this.currentView = 'userManage'
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '用户管理'
        this.currentView = 'userManage'
      }
    },
    components: {
      Button,
      userManage: userManage
    }
  }
</script>
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    margin-top:-60px;
    margin-bottom:10px;
    height:600px;
  }
  .layout-breadcrumb{
    position: absolute;
    background-color: rgba(255, 255, 255, 0.67);
    width:240px;
    margin-top: -25px;
    margin-left: 15px;
    border-radius: 5px;
    padding-buttom: 10px;
    padding-top: 10px;
    padding-left: 20px;
    text-align:left;
    height:30px;
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
    background: #ffffff;
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
