<template>
  <div>
    promise
  </div>
</template>

<script>
function mockAjax(mockPara,mockResult,terr){
  let promiseObj=new Promise(((resolve, reject) =>{
    console.log("开始模拟Ajax调用:"+mockPara);
    setTimeout(function (){
      if (terr){
        //throw "err:"+mockPara;
        reject(Error("err:"+mockPara));
      }
      resolve(mockResult);
    },2000);
  } ))

  return promiseObj;
}

export default {
  name: "es6-promise",
  mounted() {
    mockAjax("1111", "1111请求成功!").then((msg) => {
      console.log(msg);
      return mockAjax("2222", "2222请求成功!");
    }).then((msg) => {
      console.log(msg);
    });

    Promise.all([mockAjax("3333-1", "3333-1请求成功!"),
      mockAjax("3333-2", "3333-2请求成功!"),
      mockAjax("3333-3", "3333-3请求成功!")]
    ).then((allResult) => {
      console.log(allResult);
    });

    Promise.all([mockAjax("4444-1", "4444-1请求成功!"),
      mockAjax("4444-2", "4444-2请求成功!",true),
      mockAjax("4444-3", "4444-3请求成功!")]
    ).then((allResult) => {
      console.log(allResult);
    }).catch((reason)=>{
      console.log("4444中存在失败调用");
      console.log(reason);
    });

    Promise.all([mockAjax("5555-1", "5555-1请求成功!"),
      mockAjax("5555-2", "5555-2请求成功!"),
      mockAjax("5555-3", "5555-3请求成功!")]
    ).then((allResult) => {
      throw Error("5555调用完成后处理程序异常!");
      console.log(allResult);
    }).catch((reason)=>{
      //console.log("5555中存在失败调用");
      console.log(reason);
    });
  }
}
</script>

<style scoped>

</style>