// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import { router } from './router/router';
import es6pro from 'es6-promise';
// import axios from 'axios'; // 전역으로 등록하기
import { store } from './vuex/vuex';

es6pro.polyfill();
Vue.config.productionTip = false;
// Vue.prototype.$http = axios // 전역으로 등록하는 방법 - 이후에 다른 vue소스 내에서 $http.get().then()으로 사용하면 된다.

Vue.prototype.$bus = new Vue();


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});