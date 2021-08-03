<template>
  <div>
    es6-await
  </div>

</template>

<script>
async function asy1(){
  let value=0;
  await new Promise((resolve, reject) => {
    window.setTimeout(function (){
      console.log("1111");
      value+=2000;
      resolve();
    },2000);
  });
  await new Promise((resolve, reject) => {
    window.setTimeout(function (){
      console.log("2222");
      value+=2000;
      resolve();
    },2000);
  });
  await new Promise((resolve, reject) => {
    window.setTimeout(function (){
      console.log("3333");
      value+=2000;
      resolve();
    },2000);
  });
  return value;
}

export default {
  name: "es6-await",
  mounted() {
    asy1().then((v1)=>{
      console.log(v1);
    });
  }
}
</script>

<style scoped>

</style>