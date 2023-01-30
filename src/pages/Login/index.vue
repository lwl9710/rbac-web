<script lang="ts" setup>
import { Ref } from "vue";
const router = useRouter();
const formEl = ref(null);
const form = ref({
  username: "admin",
  password: "123456"
});
const rules:Ref<Partial<any>>  = ref({
  username: [
    { required: true, trigger: "blur", message: "请输入用户名" }
  ],
  password: [
    { required: true, trigger: "blur", message: "请输入密码" }
  ]
})
const clickSubmitForm = () => {
  console.log(formEl.value);
  (formEl.value as any).validate()
  .then(() => {
    router.replace({ path: "/" });
  })
}
</script>
<template>
<div class="login">
  <div class="login-form">
    <h1>用户登录</h1>
    <el-form ref="formEl" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" size="large" prefix-icon="user" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password" size="large" prefix-icon="user" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="large" @click="clickSubmitForm" style="width: 100%" type="primary">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>
<style lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
  background-color:  #909399;
  h1 {
    padding-bottom: 18px;
    text-align: center;
    line-height: 1em;
    font-size: 26px;
    color: #606266;
  }
  .login-form {
    padding-top: 18px;
    padding-right: 18px;
    width: 500px;
    background-color: #FFF;
    border-radius: 8px;
    box-shadow: 0 0 5px rgba(0, 0, 0, .3);
  }
}
</style>
