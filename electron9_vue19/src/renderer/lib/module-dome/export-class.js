var exportClassA={
    _field1:"exportClassA_field1",
    _field2:"exportClassA_field2",
    _fieldObj:{
        _fieldObj1:"exportClassA原始值",
        _fieldObj2:"exportClassA原始值"
    },
    init:function (field1,field2,fieldObj){
        if(field1) {
            this._field1 = field1;
        }
        if(field2) {
            this._field2 = field2;
        }
        if(fieldObj) {
            this._fieldObj = fieldObj;
        }
    },
    m1:function (){
        return this._field1;
    },
    mObj:function (){
        return this._fieldObj;
    }
}

class exportClassB{
    constructor(desc,count) {
        this.desc=desc;
        this.count=count;
    }

    getDesc(){
        this.address="address-------123";
        return this.desc+"&"+this.count;
    }

    getAddress(){
        return this.address;
    }

    set prop_name(_name){
        this.name=_name;
    }

    get prop_name(){
        return this.name;
    }

    static static_func(){
        return "call static func"
    }
}

class exportClassB1 extends exportClassB{

    static static_func_1({p1,p2}){
        return p1+"---++++---"+p2;
    }

    static yieldDemo(){
        function* y1(){
            yield "call1";
            yield "call2";
            yield "call3";
        }

        var y1r=y1();
        console.log(y1r);
        console.log(y1r.next());
        console.log(y1r.next());
        console.log(y1r.next());
        console.log(y1r.next());
    }
    /*constructor(desc,count) {
        super.constructor(desc,count);
    }*/
}

export {
    exportClassA,
    exportClassB,
    exportClassB1
}