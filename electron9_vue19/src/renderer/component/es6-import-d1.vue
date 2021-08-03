<template>
  <div>es6-import-d1</div>
</template>

<script>
import {exportVarA,exportVarB,exportVarC,exportVarDNewName,exportVarE as exportVarENewName} from "../lib/module-dome/export-var.js";
import defVar from "../lib/module-dome/export-var.js";
import * as allVar from "../lib/module-dome/export-var.js";

import {exportFuncA,exportFuncB,exportFuncC,exportFuncDNewName,exportFuncE as exportFuncENewName} from "../lib/module-dome/export-func.js";
import defFunc from "../lib/module-dome/export-func.js";
import * as allFunc from "../lib/module-dome/export-func.js";

import {exportClassA,exportClassB,exportClassB1} from "../lib/module-dome/export-class.js";

export default {
  name: "es6-import-d1",
  mounted() {
    console.log("----导出变量开始----");
    console.log(exportVarA);
    console.log(exportVarB);
    console.log(exportVarC);
    console.log(exportVarDNewName);
    console.log(exportVarENewName);
    console.log(defVar);
    console.log(allVar);
    console.log("----导出变量结束----");

    console.log("----导出函数开始----");
    console.log(exportFuncA());
    console.log(exportFuncB());
    console.log(exportFuncC());
    console.log(exportFuncDNewName());
    console.log(exportFuncENewName());
    console.log(defFunc);
    console.log(defFunc.exportFuncDefA());
    console.log(defFunc.exportFuncDefB());
    console.log(allFunc);
    console.log("----导出函数结束----");

    console.log("----导出类开始----");
    //----------------exportClassA-------------------
    var refObj={
      _fieldObj1:"exportClassA-Obj值被修改1",
      _fieldObj2:"exportClassA-Obj值被修改2"
    }
    var classA1=Object.create(exportClassA);
    classA1.init(null,null,Object.assign({},refObj));
    var classA2=Object.create(exportClassA);
    refObj._fieldObj1="exportClassA2-Obj值被修改1111",
    refObj._fieldObj2="exportClassA2-Obj值被修改2222",
    classA2.init("值被修改1","值被修改2",refObj)

    console.log(classA1.m1());
    console.log(classA1.mObj());
    console.log(classA2.m1());
    console.log(classA2.mObj());

    //----------------exportClassB-------------------
    var classB1=new exportClassB("hello",18);
    console.log(classB1.getDesc());
    console.log(classB1.getAddress());
    classB1.prop_name="hello 2021";
    console.log(classB1.prop_name)
    console.log(exportClassB.static_func());
    console.log(exportClassB1.static_func());
    console.log(exportClassB1.static_func_1({p1:"ppp1",p2:"ppp2"}));
    exportClassB1.yieldDemo();
    console.log("----导出类结束----");
  }
}
</script>

<style scoped>

</style>