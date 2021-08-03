<template>
    <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" @select="handleChange" :collapse="isCollapse">
        <el-submenu index="echart">
            <template slot="title">
                <i class="el-icon-pie-chart"></i>
                <span slot="title">EChart</span>
            </template>
            <el-menu-item-group>
                <span slot="title">EChart</span>
                <el-submenu index="echart-geomap">
                    <span slot="title">geomap</span>
                    <el-menu-item index="echart-geomap-dome01">dome1</el-menu-item>
                    <el-menu-item index="echart-geomap-dome02">dome2</el-menu-item>
                    <el-menu-item index="echart-geomap-dome03">dome3</el-menu-item>
                    <el-menu-item index="echart-geomap-dome04">dome4</el-menu-item>
                </el-submenu>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="d3">
            <template slot="title">
                <i class="el-icon-bangzhu"></i>
                <span slot="title">D3</span>
            </template>
            <el-menu-item-group>
                <span slot="title">D3</span>
                <el-submenu index="d3-rect">
                    <span slot="title">rect</span>
                    <el-menu-item index="d3-hello-word">hello-word</el-menu-item>
                    <el-menu-item index="d3-rect-demo01">demo01</el-menu-item>
                    <el-menu-item index="d3-rect-demo02">demo02</el-menu-item>
                    <el-menu-item index="d3-rect-demo03">demo03</el-menu-item>
                    <el-menu-item index="d3-rect-demo04">demo04</el-menu-item>
                </el-submenu>
            </el-menu-item-group>
        </el-submenu>
        <el-submenu index="threeJS">
            <template slot="title">
                <i class="el-icon-coin"></i>
                <span slot="title">threeJS</span>
            </template>
            <el-menu-item-group>
                <span slot="title">threeJS</span>
                <el-submenu index="threejs-chapter-01">
                    <span slot="title">threejs-chapter-01</span>
                    <el-menu-item index="threejs-chapter-01-01-basic-skeleton">threejs-chapter-01-01-basic-skeleton</el-menu-item>
                    <el-menu-item index="threejs-chapter-01-02-first-scene">threejs-chapter-01-02-first-scene</el-menu-item>
                    <el-menu-item index="threejs-chapter-01-03-materials-light">threejs-chapter-01-03-materials-light</el-menu-item>
                    <el-menu-item index="threejs-chapter-01-04-materials-light-animation">threejs-chapter-01-04-materials-light-animation</el-menu-item>
                    <el-menu-item index="threejs-chapter-01-05-control-gui">threejs-chapter-01-05-control-gui</el-menu-item>
                </el-submenu>
                <el-submenu index="threejs-chapter-02">
                    <span slot="title">threejs-chapter-02</span>
                    <el-menu-item index="threejs-chapter-02-01-basic-scene">threejs-chapter-02-01-basic-scene</el-menu-item>
                </el-submenu>
                <el-submenu index="threejs-360-pic">
                    <span slot="title">threejs-360-pic</span>
                    <el-menu-item index="threejs-360-pic-01">threejs-360-pic-01</el-menu-item>
                    <el-menu-item index="threejs-360-pic-02">threejs-360-pic-02</el-menu-item>
                </el-submenu>
            </el-menu-item-group>
        </el-submenu>
      <el-submenu index="es6">
        <template slot="title">
          <i class="el-icon-bangzhu"></i>
          <span slot="title">ES6</span>
        </template>
        <el-menu-item-group>
          <span slot="title">ES6</span>
          <el-submenu index="ES6-Group">
            <span slot="title">ES6</span>
            <el-menu-item index="es6-var-init">es6-var-init</el-menu-item>
            <el-menu-item index="es6-import-d1">es6-import-d1</el-menu-item>
            <el-menu-item index="es6-promise">es6-promise</el-menu-item>
            <el-menu-item index="es6-await">es6-await</el-menu-item>
            <el-menu-item index="es6-symbol">es6-symbol</el-menu-item>
          </el-submenu>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="css3">
        <template slot="title">
          <i class="el-icon-bangzhu"></i>
          <span slot="title">CSS3</span>
        </template>
        <el-menu-item-group>
          <span slot="title">CSS3</span>
          <el-submenu index="CSS3-Group">
            <span slot="title">CSS3</span>
            <el-menu-item index="css3-flex-layout">css3-flex-layout1</el-menu-item>
            <el-menu-item index="css3-flex-layout2">css3-flex-layout2</el-menu-item>
          </el-submenu>
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