//const Vue = require('vue').default;
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import leftMainMenu from './component/left-main-menu.vue'
import mainApplication from './main-application.vue'
import empty from './component/empty.vue'
import echartGeomapDome1 from './component/echart-geomap-dome1.vue'
import echartGeomapDome2 from './component/echart-geomap-dome2.vue'

Vue.use(ElementUI);
Vue.component('left-main-menu', leftMainMenu);
Vue.component('main-application', mainApplication);
Vue.component('empty', empty);
Vue.component('echart-geomap-dome1', echartGeomapDome1);
Vue.component('echart-geomap-dome2', echartGeomapDome2);

const app=new Vue({
    el: '#app',
    data: function() {
        return {}
    },
    methods: {}
})