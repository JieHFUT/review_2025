<script setup>
  /*导入 pinia 数据*/
  import {defineUser} from "../store/userStore.js";
  import {defineSchedule} from '../store/scheduleStore.js'

  let sysUser = defineUser()
  let schedule = defineSchedule()

  // 挂载完毕后，应该立即查询当前用户的所有日程信息
  import {ref, reactive, onUpdated, onMounted} from "vue";
  import request from "../utils/request.js";
  onMounted(async () => {
    showSchedule()
  })
  // 查询当前用户所有的日程信息，并且将其展示在视图上的办法
  async function showSchedule(){
    // 挂载完毕后，应该发送异步请求，获得当前用户的所有日程记录
    let {data} = await request.get("schedule/findAllSchedule", {params:{"uid":sysUser.uid}})
    console.log(data)
    schedule.itemList = data.data.items
  }
  // 为当前用户增加一条空的日程记录，数据库有一条空记录，刷新页面，用户填写完后，发送请求将其保存在数据库中
  async function addItem() {
    let {data} = await request.get("schedule/addDefaultSchedule", {params:{"uid":sysUser.uid}})
    console.log(data)
    if (data.code === 200) {
      //增加成功，刷新页面数据后填写数据
      showSchedule()
    }else {

    }
  }

  // 点击保存修改按钮后，向后端发送请求，同时更新数据库中的数据
  async function updateItem(index) {
    // 找到要修改的数据，将其发送给服务端，更新进入数据库
    // post 请求中路径后面的这个对象会被转换为 JSON 字符串放在请求体中
    let {data} = await request.post("schedule/updateSchedule", schedule.itemList[index])
    if (data.code === 200) {
      // 更新成功
      showSchedule()
      alert("更新成功")
    } else {
      alert("数据更新失败")
    }
  }

  // 点击删除按钮后，前端把日程信息发送给后端，后端从数据库中删除该对象
  async function removeItem(index) {
    let {data} = await request.post("schedule/removeSchedule", schedule.itemList[index])
    if (data.code === 200) {
      // 更新成功
      showSchedule()
      alert("删除成功")
    } else {
      alert("数据删除失败")
    }
  }


</script>

<template>
  <div>
    <h3 class="ht">您的日程如下</h3>
    <table class="tab" cellspacing="0px">
      <tr class="ltr">
        <th>编号</th>
        <th>内容</th>
        <th>进度</th>
        <th>操作</th>
      </tr>
      <tr class="ltr" v-for="item, index in schedule.itemList":key="index">
        <td v-text="index + 1"></td>
    <!--<td v-text="item.title"></td>-->
        <td>
          <input type="text" v-model="item.title">
        </td>
        <td>
          <input type="radio" value="1" v-model="item.completed"> 已完成
          <input type="radio" value="0" v-model="item.completed"> 未完成
        </td>
        <td class="buttonContainer">
          <button class="btn1" @click="removeItem(index)">删除</button>
          <button class="btn1" @click="updateItem(index)">保存修改</button>
        </td>
      </tr>
      <tr class="ltr buttonContainer" >
        <td colspan="4">
          <button class="btn1" @click="addItem()">新增日程</button>
        </td>

      </tr>
    </table>
  </div>
</template>

<style scoped>

.ht{
  text-align: center;
  color: cadetblue;
  font-family: 幼圆;
}
.tab{
  width: 80%;
  border: 5px solid cadetblue;
  margin: 0px auto;
  border-radius: 5px;
  font-family: 幼圆;
}
.ltr td{
  border: 1px solid  powderblue;

}
.ipt{
  border: 0px;
  width: 50%;

}
.btn1{
  border: 2px solid powderblue;
  border-radius: 4px;
  width:100px;
  background-color: antiquewhite;

}
#usernameMsg , #userPwdMsg {
  color: gold;
}

.buttonContainer{
  text-align: center;
}

</style>
