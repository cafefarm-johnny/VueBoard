import Vue from 'vue'
import Router from 'vue-router'
import Body from '../components/layout/body/Body';
import Join from '../components/join/Join';
import Login from '../components/login/Login';
import Write from '../components/board/Write';
import Content from '../components/board/Content';
import Modify from '../components/board/Modify';

Vue.use(Router);

export const router = new Router({
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
      component: Write,
      // 라우트 가드
      beforeEnter: (to, from, next) => {
        let loginFlag = sessionStorage.getItem('loginFlag') === null ? false : (sessionStorage.getItem('loginFlag') === 'true');
        if (loginFlag === false)
        {
          window.alert('로그인 후 이용해주시기 바랍니다.');
        }
        else
        {
          next();
        }
      }
    },
    {
      path: '/content/:id',
      component: Content
    },
    {
      path: '/modify/:id',
      component: Modify,
      // 라우트 가드
      beforeEnter: (to, from, next) => {
        let loginFlag = sessionStorage.getItem('loginFlag') === null ? false : (sessionStorage.getItem('loginFlag') === 'true');
        if (loginFlag === false)
        {
          window.alert('로그인 후 이용해주시기 바랍니다.');
        }
        else
        {
          next();
        }
      }
    }
  ]
});