<template>
  <div class="home">
    <header class="head" id="app-header">
    <a href="javascript: void(0)"><img class="add" @click="actionSheet" src="../assets/images/add.png"/></a>
      <div class="down">
          <select v-model="value" v-on:change="choose" style="width:120px; height:50px">
            <option v-for="item in options" :value="item.value" >
              {{item.name}}
            </option>
          </select>
      </div>
      <mt-actionsheet

        :actions= "sheets"

        v-model="sheetVisible">

      </mt-actionsheet>
    </header>
    <div v-for="item in banke">
<!--      <router-link :to="{path:'/classCourse_teacher',query:{Class_No:item.Class_No,Course_Name:item.Course_Name}}">-->
        <a @click="chooseClassCourse(item.Class_No)">
          <div class="banke">
            <div class="my-left"><img src="../assets/images/u1159.png" alt=""></div>
            <div class="my-right"><tr style="font-size: 15px">{{item.Class_Name}}</tr><tr style="font-size: 15px">{{item.Course_Name}}</tr><span>教师：{{item.Teacher_Name}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 学期：{{item.Course_Term}}</span>  </div>
          </div>
        </a>
<!--      </router-link>-->
    </div>
    <mt-tabbar v-model="selected"  fixed class="footer" >

      <mt-tab-item id="班课">
        <router-link to="/home_teacher">
          <p><img slot="icon" src="../assets/images/finger.png"></p>

          班课
        </router-link>
      </mt-tab-item>

        <mt-tab-item id="我的">
          <router-link to="/my_teacher">
          <p><img slot="icon" src="../assets/images/my.png"></p>
          我的
          </router-link>
        </mt-tab-item>

    </mt-tabbar>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    name : 'home',
    data(){
        return{
          telphone:'',
          sheetVisible: false,//默认不显示上拉框
          selected:'',
          value:1 ,//默认选中第一项
          options:[
            {
              name:'全部班课',
              value:0,
            },
            {
              name:'我创建的班课',
              value:1,
            },
          ],
          sheets:[
            {
            name: '创建班课',
            method : this.createClass// 调用methods中的函数
            }
            ],
          banke:[],    //全部班课
          mybanke:[]    //我创建的班课
        }
    },
    created(){

      this.telphone=this.$store.getters.getStorage('info').telphone;
      //先把我创建的班课赋值给mybanke
      this.$ajax({
        method:'get',
        url:'/api/classCourse/myClassCourse',
        params:{
          telphone:this.telphone,
        }
      }).then(response=>{
      //  console.log(response);
        this.mybanke=response.data;
      }).catch(function (err) {
        console.log(err);
      });
      this.choose();
    },
    computed: {},
    methods:{
   //下拉框
   choose(){
      if(this.value==0){
        //console.log('value为0');
        this.$ajax({
        method:'get',
        url:'/api/classCourse/allClassCourse',
      }).then(response=>{
        console.log(response);
        this.banke=response.data;
      }).catch(function (err) {
        console.log(err);
      });
      }
     if(this.value==1){
       console.log('value为1');
       this.$ajax({
         method:'get',
         url:'/api/classCourse/myClassCourse',
         params:{
           telphone:this.telphone,
         }
       }).then(response=>{
         console.log(response);
         this.banke=response.data;
       }).catch(function (err) {
         console.log(err);
       });
     }
   },
    //选择框
    actionSheet(){
     // 打开action sheet
      this.sheetVisible = true;
      },
    createClass(){
      console.log("创建班课");
      this.$router.push('/createClass');
    },
    chooseClassCourse(e){//只能操作自己创建的班课，其他老师创建的不能操作
     console.log('qqq');
    // console.log(e);
        var state=1;
        for(var i=0;i<this.mybanke.length;i++){
          if(this.mybanke[i].Class_No==e){
            state=2;
           // console.log(state);
            this.$router.push({
              path:'/classCourse_teacher',
              query:{
                Class_No:this.mybanke[i].Class_No,
                Course_Name:this.mybanke[i].Course_Name
              }
            });
          }
      }
      if(state===1){
        this.$toast({
          message: '不是您创建的班课，无权操作',
          duration: 2000
        });
        // this.$router.push('/home_teacher');
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/function';
  .home {
    width: 100%;
    height: 100%;
    overflow: hidden;
    padding-bottom: px2rem(260px);
    .head{
      height: 60px;
      background: #096dd9;
      position: relative;
      img{
        height: 50px;
        position: absolute;
        top: 5px;
        right: 20%;
        cursor: pointer;
      }
      .add{
        height: 20px;
        top: 20px;
        left: 10px;
      }
      .down{
        padding: 5px 10px 10px 0;
        display: flex;
        justify-content:center;
        align-items:Center;
        select{
          border: solid 1px #000;
          background-color: #096dd9;
          color: #FFFFFF;
          font-size: 18px;
          }
        }
      .hightColor{
        color: #FFFFFF;
        background-color: #EEEEEE;
      }
    }
    .footer {
      background-color:#096dd9;

      p {
        img{height:20px;width: 20px}
        line-height: 20px;
        font-size: 20px;
        color: #fff;
        margin-bottom: 2px;
      }
    }
    .banke{
      display: flex;
      border-bottom: 1px solid #999;
      .my-left{
        width: 50px;
        margin:10px 10px 0 0;
        img{
          width: 100%;
        }
      }
      .my-right{
        padding: 5px 10px;
        p{
          line-height: 25px;
        }
        span{
          line-height: 25px;
          color: #aaa;
        }
      }
    }
  }

</style>




