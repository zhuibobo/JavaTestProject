<template>
    <div id="d3-hello-word"></div>
</template>

<script>
    let d3 = require("d3");

    export default {
        name: "d3-hello-word",
        mounted() {
            console.log("1111111111111111111111111111111");
            d3.select("#d3-hello-word")
                .selectAll("p")
                .data([4, 8, 15, 16, 23, 42])
                .enter().append("p")
                .text(function(d) { return "hello word " + d + "!"; });
        }
    }
</script>

<style scoped>

</style>