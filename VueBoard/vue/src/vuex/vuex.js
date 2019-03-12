import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
    // 배포시 반드시 지우고 배포해야한다!!
    strict: true, // 엄격모드에서는 외부에서 변이 될 때 마다 오류를 발생시키며, 디버깅 도구로 모든 상태 변이를 추적할 수 있다.
    state: {
        loginFlag: sessionStorage.getItem('loginFlag') === null ? false : (sessionStorage.getItem('loginFlag') === 'true'),
        loginUser: ''
    },
    getters: {
        getLoginFlag: function (state) {
            return state.loginFlag;
        },
        getUserName: function (state) {
            return state.loginUser;
        }
    },
    mutations: {
        loginFlush: function (state, payload) {
            sessionStorage.setItem('loginFlag', true);
            state.loginFlag =  true;
            state.loginUser = payload;
        },
        logoutFlush: function (state, payload) {
            sessionStorage.setItem('loginFlag', false);
            state.loginFlag = false;
            state.loginUser = '';
        }
    },
    actions: {
    }
});