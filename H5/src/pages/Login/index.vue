<script lang="ts" setup>
import { Ref } from "vue";
import useUserStore from "@/store/user";
const store = useUserStore();
const router = useRouter();
const formEl = ref(null);
const form: Ref<{
  username: string,
  password: string
}> = ref({
  username: "",
  password: ""
});
const rules:Ref<Partial<any>>  = ref({
  username: [
    { required: true, trigger: "blur", message: "请输入账号" }
  ],
  password: [
    { required: true, trigger: "blur", message: "请输入密码" }
  ]
})
const clickSubmitForm = () => {
  (formEl.value as any).validate()
  .then(() => store.login(toRaw(form.value)))
  .then(() => router.replace({ path: "/" }));
}
</script>
<template>
<div class="login">
  <div class="login-form">
    <h1>用户登录</h1>
    <el-form ref="formEl" :model="form" :rules="rules" label-width="0">
      <el-form-item prop="username">
        <el-input v-model="form.username" size="large" prefix-icon="user" placeholder="请输入账号">
          <template #prepend>
            <span>账号</span>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="form.password" type="password" size="large" prefix-icon="lock" placeholder="请输入密码">
          <template #prepend>
            <span>密码</span>
          </template>
        </el-input>
      </el-form-item>
      <div class="control">
        <el-button size="large" @click="clickSubmitForm" style="width: 100%" type="primary">登录</el-button>
      </div>
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
    padding: 50px;
    width: 380px;
    background-color: #FFF;
    border-radius: 8px;
    box-shadow: 0 0 5px rgba(0, 0, 0, .3);
  }
}
</style>
