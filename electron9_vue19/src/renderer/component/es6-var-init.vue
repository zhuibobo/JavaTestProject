<template>
  <div>
    <details>
      <summary>summary</summary>
      detailsdetailsdetailsdetailsdetailsdetails
    </details>
  </div>
</template>

<script>
export default {
  name: "es6-var-init",
  mounted() {
    var [a,b,c,d=4]=[1,2,3];

    console.log(a);
    console.log(b);
    console.log(c);
    console.log(d)

    var jsonObj={objFA:"objFA",objFB:"objFB",objFD:"objFD"};
    var {objFA,objFB,objFC="objFC",objFD:objFDNewName}=jsonObj;

    console.log(objFA);
    console.log(objFB);
    console.log(objFC);
    console.log(objFDNewName);

    for(let i of [1,2,3,4,5]) {
      console.log(i);
    }

    var arry1=[{name:"z",sex:"m"},{name: "r",sex:"g"}];

    for(let {name,sex} of arry1) {
      console.log(name + "----" + sex);
    }
  }
}
</script>

<style scoped>

</style>