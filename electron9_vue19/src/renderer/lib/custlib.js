let querystring =require('querystring');

let CustEnv={
    QueryString:function (name) {
        let obj=querystring.parse(window.location.search.replace("?",""));
        console.log(obj);
        return obj[name];
    }
}

function f() {
    return "!";
}

module.exports={
    CustEnv
}