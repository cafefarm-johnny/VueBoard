import Vue from 'vue'
import Router from 'vue-router'
import Body from '../components/layout/body/Body';
import Join from '../components/join/Join';
import Login from '../components/login/Login';
import Write from '../components/board/Write';

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Body
    },
    {
      path: '/join',
      component: Join
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/write',
      component: Write
    }
  ]
})
