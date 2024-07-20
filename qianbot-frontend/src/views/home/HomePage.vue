<script lang="ts" setup>
const githubUrl = "https://github.com/zgh-qian";
</script>
<template>
  <div id="homePage">
    <a-typography-title :heading="3"> 你好! </a-typography-title>
    <a-typography-paragraph>
      当你探索网站时，你将发现一个丰富多样的答题应用，涵盖从MBTI性格测试到趣味性问答的各种选择。
    </a-typography-paragraph>
    <a-typography-paragraph>
      不仅如此，智能图表分析功能允许你上传文件，通过AI技术进行深入分析，帮助你揭示数据背后的洞见和趋势。
    </a-typography-paragraph>
    <a-typography-paragraph>
      无论你是寻找娱乐放松，还是需要数据驱动的决策支持，网站都能为你提供令人满意的解决方案。探索你的潜力，了解你的数据，尽在平台上。
    </a-typography-paragraph>
    <a-typography-paragraph>
      <a-link href="/app">【速戳】快来试试答题应用！！！</a-link>
    </a-typography-paragraph>
    <a-typography-paragraph>
      <a-link href="/chart">【速戳】快来试试图表分析！！！</a-link>
    </a-typography-paragraph>
    <a-typography-paragraph>
      <a-link :href="githubUrl">欢迎访问GitHub仓库</a-link>
    </a-typography-paragraph>
  </div>
</template>
<style scoped></style>
