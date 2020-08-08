<template>
    <el-container style="height: 100%;border: 1px solid #eee">
        <el-header>
            electron9_vue191
        </el-header>
        <el-container>
            <el-aside width="65px">
                <left-main-menu @change="menuChange"></left-main-menu>
            </el-aside>

            <el-main>
                <webview ref="mainComponentIFrame" nodeintegration src="webview/main-component-webview/mainComponentWebView.html" style="display:inline-flex; width: 100%;height: 99%"></webview>
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
                mainComponent:"empty",
                webview:null
            }
        },
        mounted() {
            this.webview=this.$refs.mainComponentIFrame;
            let webview= this.webview;
            webview.addEventListener("dom-ready", function() {
                webview.openDevTools();
            });
        },
        methods:{
            menuChange:function (key, keyPath) {
                //if(key=="encryption-tools"){
                    //this.mainComponent=key;
                //}
                this.webview.src="webview/main-component-webview/mainComponentWebView.html?mainComponent="+key;
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