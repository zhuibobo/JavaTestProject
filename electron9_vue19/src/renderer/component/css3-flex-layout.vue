<template>
  <div style="height: 100vh">
    <div class="flex-layout1">
      <div class="flex-l1-ic"></div>
      <div class="flex-l1-ic"></div>
      <div class="flex-l1-ic"></div>
    </div>

    <div class="flex-layout1" style="flex-direction: column">
      <div class="flex-l1-ic"></div>
      <div class="flex-l1-ic"></div>
      <div class="flex-l1-ic"></div>
    </div>

    <div class="flex-layout1" style="flex-wrap: wrap">
      <div class="flex-l1-ic" style="width: 250px"></div>
      <div class="flex-l1-ic" style="width: 250px"></div>
      <div class="flex-l1-ic" style="width: 250px"></div>
    </div>

    <div class="flex-layout1" style="flex-wrap: wrap">
      <div class="flex-l1-ic" style="width: 250px;flex-grow: 1"></div>
      <div class="flex-l1-ic" style="width: 250px"></div>
      <div class="flex-l1-ic" style="width: 250px"></div>
    </div>

    <div class="flex-layout1">
      <div class="flex-l1-ic" style="width: initial;flex-grow: 1"></div>
      <div class="flex-l1-ic" style="width: initial;flex-grow: 3"></div>
      <div class="flex-l1-ic" style="width: initial;flex-grow: 1"></div>
    </div>

    <div class="flex-layout1" style="flex-wrap: wrap">
      <div class="flex-l1-ic" style="width: 250px;flex-basis: 280px;"></div>
      <div class="flex-l1-ic" style="width: 100px;flex-grow: 1"></div>
      <div class="flex-l1-ic" style="width: 100px;flex-grow: 1"></div>
      <div class="flex-l1-ic" style="width: 250px"></div>
    </div>

    <div class="flex-layout1">
      <div class="flex-l1-ic" style="flex-basis: 480px;"></div>
      <div class="flex-l1-ic" style="flex-basis: 480px;flex-shrink: 2"></div>
    </div>

    <div class="flex-layout1">
      <div class="flex-l1-ic" style="flex-basis: 480px;"></div>
      <div class="flex-l1-ic" style="flex-basis: 480px;flex-shrink: 2"></div>
    </div>
  </div>
</template>

<script>
export default {
name: "css3-flex-layout"
}
</script>

<style scoped>
  .flex-layout1{
    display: flex;
    border: #303133 1px dotted;
    width: 600px;
    margin-bottom: 10px;
  }
  .flex-l1-ic{
    background: #6cc096;
    width: 100px;
    height: 20px;
    border: #303133 1px dotted;
    box-sizing: border-box;
  }
</style>