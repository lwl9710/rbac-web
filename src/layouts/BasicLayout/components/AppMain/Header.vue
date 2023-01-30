<script lang="ts" setup>
import useSettingStore from "@/store/setting";
import {useRouter} from "vue-router";
const store = useSettingStore();
const router = useRouter();
const clickChangeExpand = () => {
  store.isCollapse = !store.isCollapse;
}
const clickLogout = () => {
  ElMessageBox.confirm("您确定退出登录吗?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: 'warning',
  }).then(() => {
    router.replace({ name: "Login" });
  });
}
</script>
<template>
<header>
  <div class="left">
    <div class="control">
      <el-icon color="#303133" size="26" @click="clickChangeExpand">
        <Expand v-if="store.isCollapse" />
        <Fold v-else />
      </el-icon>
    </div>
  </div>
  <div class="right">
    <el-dropdown trigger="click">
      <div class="avatar">
        <img src="https://img2.baidu.com/it/u=1385559459,94521354&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1675098000&t=21386f6dfa6bd270796954b514ba58f4" alt="">
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="clickLogout">
            <span style="color: #F56C6C">退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</header>
</template>
<style lang="scss" scoped>
header {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
  height: 45px;
  background-color: #FFF;
  box-shadow: 0 0 3px #B4B4B4;
  .left {
    .control {
      cursor: pointer;
    }
  }
  .right {
    .avatar {
      cursor: pointer;
      line-height: 40px;
      img {
        width: 40px;
        height: 40px;
        object-fit: cover;
        border-radius: 50%;
      }
    }
  }
}
</style>
