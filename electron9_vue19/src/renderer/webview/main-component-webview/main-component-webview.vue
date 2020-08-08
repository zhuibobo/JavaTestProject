<template>
    <div>
        <component v-bind:is="mainComponent"></component>
    </div>
</template>

<script>
    import { CustEnv } from '../../lib/custlib';
    export default {
        name: "main-component-webview.vue",
        data:function () {
            return {
                mainComponent:"empty"
            }
        },
        mounted() {
            let mainComponent=CustEnv.QueryString("mainComponent");
            if (mainComponent){
                this.mainComponent=mainComponent;
            }
        }
    }
</script>

<style scoped>

</style>