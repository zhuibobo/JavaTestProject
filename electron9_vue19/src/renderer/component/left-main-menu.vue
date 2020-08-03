<template>
    <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" @select="handleChange" :collapse="isCollapse">
        <el-submenu index="echart">
            <template slot="title">
                <i class="el-icon-location"></i>
                <span slot="title">EChart</span>
            </template>
            <el-submenu index="echart-geomap">
                <span slot="title">geomap</span>
                <el-menu-item index="echart-geomap-dome1">dome1</el-menu-item>
                <el-menu-item index="echart-geomap-dome2">dome2</el-menu-item>
            </el-submenu>
            <el-menu-item-group>
                <span slot="title">分组一</span>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
                <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="2" disabled>
            <i class="el-icon-menu"></i>
            <span slot="title">磁盘管理</span>
        </el-menu-item>
        <el-submenu index="3">
            <template slot="title">
                <i class="el-icon-link"></i>
                <span slot="title">工具</span>
            </template>
            <el-menu-item-group>
                <span slot="title">处理工具</span>
                <el-menu-item index="resolve-javbus-magnet">解析JavBus磁力链接</el-menu-item>
                <el-menu-item index="local-video-tools">本地视频处理</el-menu-item>
                <el-menu-item index="hard-disk-overview-scanning">磁盘概况扫描</el-menu-item>
                <el-menu-item index="av-video-search-subtitle">番号匹配字幕(DEVING)</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4" disabled>
            <template slot="title">
                <i class="el-icon-setting"></i>
                <span slot="title">工具</span>
            </template>
            <el-menu-item-group>
                <span slot="title">Tools</span>
                <el-menu-item index="encryption-tools">加 / 解密</el-menu-item>
                <el-menu-item index="paras-setting">参数设置</el-menu-item>
            </el-menu-item-group>
        </el-submenu>
    </el-menu>
</template>

<script>
    export default {
        name: "left-main-menu",
        data: function() {
            return { isCollapse: true }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            handleChange(key, keyPath){
                this.$emit('change',key, keyPath)
                //console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>

</style>