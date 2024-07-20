<script setup lang="ts">
const githubPicUrl =
  "https://img.88icon.com/upload/jpg/20210622/fb720f1b29c17acca066e40fcc15fbc2_17304_512_512.jpg!bg";
const githubUrl = "https://github.com/zgh-qian";
</script>

<template>
  <div class="footer">
    <a-space>
      <a-avatar :size="32">
        <img alt="github" :src="githubPicUrl" />
      </a-avatar>
      <a-link style="color: #000000" :href="githubUrl" :hoverable="false"
        >全民制作人</a-link
      >
    </a-space>
  </div>
</template>

<style scoped>
.footer {
  /*position: fixed;
  right: 0;
  bottom: 0;
  left: 0;*/
  padding: 16px;
  text-align: center;
  background-color: #efefef;
}
</style>
