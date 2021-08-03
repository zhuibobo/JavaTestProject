<template>
    <div>
        threejs-360-pic-02
        <div id="threejs-360-pic-02"></div>
        <div>
            <img :src="img">
        </div>
    </div>
</template>

<script>
    let THREE = require("three");
    import { TrackballControls } from 'three/examples/jsm/controls/TrackballControls.js';

    const loader = new THREE.TextureLoader();

    let htmlPath="../../";

    let _scene = new THREE.Scene();
    let _camera;
    let _renderer;
    let _container;
    let trackballControls;
    // 初始化相机
    function initCamera() {
        _camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1100);
        _camera.position.set(0, 0, 1000);
        _camera.lookAt(new THREE.Vector3(0, 0, 0));

        let sphere = new THREE.SphereGeometry(500, 100, 100);
        sphere.scale(-1, 1, 1);
        let material = new THREE.MeshBasicMaterial({
            map: loader.load('../../imgs/360pic2--imgs.jpg'),
            //wireframe: true
        });
        //material.scale.x = -1;
        let mesh = new THREE.Mesh(sphere, material);
        //sphere.scale.x = -1;
        _scene.add(mesh);

        var axisHelper = new THREE.AxisHelper(2000);
        _scene.add(axisHelper);

        _camera.position.set(0, 0, 1);
        _camera.lookAt(new THREE.Vector3(0, 0, 0));

    }

    // 初始化渲染器
    function initRenderer() {
        _renderer = new THREE.WebGLRenderer();
        _renderer.setSize(window.innerWidth, window.innerHeight);
        _container = document.getElementById('threejs-360-pic-02');
        _container.appendChild(_renderer.domElement);
    }

    // 实时渲染
    function animate() {
        trackballControls.update();
        requestAnimationFrame(animate);
        _renderer.render(_scene, _camera);
    }

    export default {
        name: "threejs-360-pic-02",
        data () {
            return {
                img: htmlPath+require('../lib/imgs/360pic2.jpg')
            }
        },
        mounted() {
            initCamera();
            initRenderer();
            trackballControls=new TrackballControls(_camera,_renderer.domElement);
            trackballControls.rotateSpeed = 5.0;
            trackballControls.zoomSpeed = 1.2;
            trackballControls.panSpeed = 3;
            //trackballControls.noPan=true;
            //trackballControls.noRotate=true;

            //trackballControls.staticMoving = true;
            trackballControls.dynamicDampingFactor = 0.3;
            animate();
        }
    }
</script>

<style scoped>

</style>