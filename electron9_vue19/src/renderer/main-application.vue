<template>
    <el-container style="height: 100%;border: 1px solid #eee">
        <el-header>
            Alex-Application
        </el-header>
        <el-container>
            <el-aside width="65px">
                <left-main-menu @change="menuChange"></left-main-menu>
            </el-aside>

            <el-main>
                <component v-bind:is="mainComponent"></component>
            </el-main>
        </el-container>
        <el-footer>
            <div style="float: right;margin-top: 18px">
                Create At 2020
            </div>
        </el-footer>
    </el-container>
</template>

<script>
    export default {
        name: "main-application",
        data:function () {
            return {
                mainComponent:"empty"
            }
        },
        methods:{
            menuChange:function (key, keyPath) {
                //if(key=="encryption-tools"){
                    this.mainComponent=key;
                //}
                console.log(key);
            }
        }
    }
</script>

<style scoped>
    .el-header {
        background-color: #303133;
        color: #ffffff;
        line-height: 60px;
    }

    .el-aside {
        background-color: #ffffff;
    }

    .el-main{
        background-color: #F2F6FC;
    }

    .el-footer{
        background-color: #303133;
        color: #ffffff;
    }
</style>