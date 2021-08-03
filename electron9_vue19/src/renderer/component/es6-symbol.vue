<template>
  <div>
    es6Symbol
  </div>
</template>

<script>
export default {
  name: "es6-symbol",
  mounted() {
    let nameSymbol=Symbol("名称");
    console.log(nameSymbol);
    let obj={
      "prop1":"prop1"
    }
    obj[nameSymbol]="zzzzz";
    console.log(obj);
    console.log(obj[nameSymbol]);
    console.log(JSON.stringify(obj));
    console.log(JSON.parse(JSON.stringify(obj)));
  }
}
</script>

<style scoped>

</style>