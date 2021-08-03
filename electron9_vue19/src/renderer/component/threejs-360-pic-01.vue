<template>
    <div>
        <div id="threejs-360-pic-01"></div>
        <div>
            红色代表X轴，绿色代表Y轴，蓝色代表Z轴
        </div>
    </div>
</template>

<script>
    let THREE = require("three");
    import { TrackballControls } from 'three/examples/jsm/controls/TrackballControls.js';

    export default {
        name: "threejs-360-pic-01",
        mounted() {
            // create a scene, that will hold all our elements such as objects, cameras and lights.
            var scene = new THREE.Scene();
            var camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);

            var renderer = new THREE.WebGLRenderer({antialias:true,
                alpha:false});
            renderer.setSize( 800, 600 );
            document.getElementById("threejs-360-pic-01").appendChild( renderer.domElement );

            var geometry = new THREE.BoxGeometry();
            var material = new THREE.MeshStandardMaterial( { color: 0x645d50 } );
            var cube = new THREE.Mesh( geometry, material );
            scene.add( cube );

            var axisHelper = new THREE.AxisHelper(100);
            scene.add(axisHelper);

            //camera.position.x = -30;
            //camera.position.y = 40;
            camera.position.z = 6;
            renderer.render( scene, camera );

            // 创建平行光-照亮几何体
            var directionalLight = new THREE.DirectionalLight(0xffffff, 1);
            directionalLight.position.set(-4, 8, 12);
            scene.add(directionalLight);
            // 创建环境光
            var ambientLight = new THREE.AmbientLight(0xffffff);
            scene.add(ambientLight);


            let trackballControls=new TrackballControls(camera,renderer.domElement);
            trackballControls.rotateSpeed = 5.0;
            trackballControls.zoomSpeed = 1.2;
            trackballControls.panSpeed = 3;
            //trackballControls.noPan=true;
            //trackballControls.noRotate=true;

            //trackballControls.staticMoving = true;
            trackballControls.dynamicDampingFactor = 0.3;
            animate();
            function animate(){
                trackballControls.update();
                renderer.render(scene, camera);
                requestAnimationFrame(animate);
            }
        }
    }
</script>

<style scoped>

</style>