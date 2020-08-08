<template>
    <div id="threejs-chapter-01-01"></div>
</template>

<script>
    let THREE = require("three");

    export default {
        name: "threejs-chapter-01-01-basic-skeleton",
        mounted() {
            var scene = new THREE.Scene();
            var camera = new THREE.PerspectiveCamera( 75, 800 / 600, 0.1, 1000 );

            var renderer = new THREE.WebGLRenderer();
            renderer.setSize( 800, 600 );
            document.getElementById("threejs-chapter-01-01").appendChild( renderer.domElement );

            var geometry = new THREE.BoxGeometry();
            var material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
            var cube = new THREE.Mesh( geometry, material );
            scene.add( cube );

            camera.position.z = 5;

            var animate = function () {
                requestAnimationFrame( animate );

                cube.rotation.x += 0.01;
                cube.rotation.y += 0.01;

                renderer.render( scene, camera );
            };

            animate();
        }
    }
</script>

<style scoped>

</style>