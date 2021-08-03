import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import mainComponentWebView from './main-component-webview.vue'
import echartGeomapDome01 from '../../component/echart-geomap-dome01.vue'
import echartGeomapDome02 from '../../component/echart-geomap-dome02.vue'
import echartGeomapDome03 from '../../component/echart-geomap-dome03.vue'
import echartGeomapDome04 from '../../component/echart-geomap-dome04.vue'
import d3HelloWord from '../../component/d3-hello-word.vue'
import d3RectDemo01 from '../../component/d3-rect-demo01.vue'
import d3RectDemo02 from '../../component/d3-rect-demo02.vue'
import d3RectDemo03 from '../../component/d3-rect-demo03.vue'
import d3RectDemo04 from '../../component/d3-rect-demo04.vue'

import empty from "../../component/empty";
import threejsChapter0101BasicSkeleton from '../../component/threejs-chapter-01-01-basic-skeleton.vue'
import threeJsChapter0102FirstScene from '../../component/threejs-chapter-01-02-first-scene.vue'
import threeJsChapter0103MaterialsLight from '../../component/threejs-chapter-01-03-materials-light.vue'
import threeJsChapter0104MaterialsLightAnimation from '../../component/threejs-chapter-01-04-materials-light-animation.vue'
import threeJsChapter0105ControlGui from '../../component/threejs-chapter-01-05-control-gui.vue'
import threeJsChapter0201BasicScene from '../../component/threejs-chapter-02-01-basic-scene.vue'
import threeJs360Pic01 from '../../component/threejs-360-pic-01.vue'
import threeJs360Pic02 from '../../component/threejs-360-pic-02.vue'

import es6VarInit from '../../component/es6-var-init.vue'
import es6ImportD1 from '../../component/es6-import-d1.vue'
import es6Promise from '../../component/es6-promise.vue'
import es6Await from '../../component/es6-await.vue'
import es6Symbol from '../../component/es6-symbol.vue'

import css3FlexLayout from '../../component/css3-flex-layout.vue'
import css3FlexLayout2 from '../../component/css3-flex-layout2.vue'

Vue.use(ElementUI);

Vue.component('main-component-webview', mainComponentWebView);
Vue.component('empty', empty);
Vue.component('echart-geomap-dome01', echartGeomapDome01);
Vue.component('echart-geomap-dome02', echartGeomapDome02);
Vue.component('echart-geomap-dome03', echartGeomapDome03);
Vue.component('echart-geomap-dome04', echartGeomapDome04);
Vue.component('d3-hello-word', d3HelloWord);
Vue.component('d3-rect-demo01', d3RectDemo01);
Vue.component('d3-rect-demo02', d3RectDemo02);
Vue.component('d3-rect-demo03', d3RectDemo03);
Vue.component('d3-rect-demo04', d3RectDemo04);

Vue.component('threejs-chapter-01-01-basic-skeleton', threejsChapter0101BasicSkeleton);
Vue.component('threejs-chapter-01-02-first-scene', threeJsChapter0102FirstScene);
Vue.component('threejs-chapter-01-03-materials-light', threeJsChapter0103MaterialsLight);
Vue.component('threejs-chapter-01-04-materials-light-animation', threeJsChapter0104MaterialsLightAnimation);
Vue.component('threejs-chapter-01-05-control-gui', threeJsChapter0105ControlGui);
Vue.component('threejs-chapter-02-01-basic-scene', threeJsChapter0201BasicScene);
Vue.component('threejs-360-pic-01', threeJs360Pic01);
Vue.component('threejs-360-pic-02', threeJs360Pic02);

Vue.component('es6-var-init', es6VarInit);
Vue.component('es6-import-d1', es6ImportD1);
Vue.component('es6-promise', es6Promise);
Vue.component('es6-await', es6Await);
Vue.component('es6-symbol', es6Symbol);

Vue.component('css3-flex-layout', css3FlexLayout);
Vue.component('css3-flex-layout2', css3FlexLayout2);

const app=new Vue({
    el: '#app',
    data: function() {
        return {}
    },
    mounted(){
        //alert("main-component-webview");
        //console.log(window.location);
        //console.log(CustEnv.QueryString("mainComponent"));
    },
    methods: {}
})
