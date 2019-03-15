import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
    // 배포시 반드시 지우고 배포해야한다!!
    // strict: true, // 엄격모드에서는 외부에서 변이 될 때 마다 오류를 발생시키며, 디버깅 도구로 모든 상태 변이를 추적할 수 있다.
    strict: process.env.NODE_ENV !== 'production', // 배포 버전
    state: {
        // 로그인
        loginFlag: sessionStorage.getItem('loginFlag') === null ? false : (sessionStorage.getItem('loginFlag') === 'true'),
        loginUser: sessionStorage.getItem('loginUser') === null ? '' : sessionStorage.getItem('loginUser'),

        // 페이징
        paging: {
            pageNumber: 1, // 현재 페이지 번호
            pageSize: 10, // 페이지 사이즈
            totalPages: 0, // 전체 페이지 갯수
            totalElements: 0, // 전체 게시물 개수
            firstPage: 1, // 가장 첫 페이지
            prevPage: 0, // 이전 페이지
            nextPage: 0, // 다음 페이지
            endPage: 0, // 끝 페이지

            pageNumberList: [1] // 페이지 번호 리스트 (Not index)
        }
    },
    getters: {
        getLoginFlag: function (state) {
            return state.loginFlag;
        },
        getUserName: function (state) {
            return state.loginUser;
        },
        getBoard: function (state) {
            return state.board;
        },
        getPaging: function (state) {
            return state.paging;
        }
    },
    mutations: {
        loginFlush: function (state, payload) {
            sessionStorage.setItem('loginFlag', true);
            sessionStorage.setItem('loginUser', payload);
            state.loginFlag =  true;
            state.loginUser = payload;
        },
        logoutFlush: function (state, payload) {
            sessionStorage.setItem('loginFlag', false);
            sessionStorage.setItem('loginUser', '');
            state.loginFlag = false;
            state.loginUser = '';
        },
        pageFlush: function (state, payload) {
            state.paging.pageNumber = payload.pageNumber + 1;
            state.paging.pageSize = payload.pageSize;
            state.paging.totalPages = payload.totalPages;
            state.paging.totalElements = payload.totalElements;

            // 이전 페이지 구하기
            if (Math.floor(payload.pageNumber / payload.pageSize) === 0)
            {
                state.paging.prevPage = 1;
            }
            else 
            {
                state.paging.prevPage = Math.floor(payload.pageNumber / payload.pageSize) * payload.pageSize;
            }
            // 다음 페이지 구하기
            if ((state.paging.prevPage + payload.pageSize) > payload.totalPages)
            {
                state.paging.nextPage = payload.totalPages;
            }
            else
            {
                // 1 페이지 블록에선 11을, 2 페이지 블록부터 21, 31, 41...
                state.paging.nextPage = state.paging.prevPage === 1 ? (state.paging.prevPage + payload.pageSize) : (state.paging.prevPage + payload.pageSize) + 1;
            }
            // 끝 페이지 구하기
            state.paging.endPage = payload.totalPages;

            // 현재 페이지 블록의 페이지 번호 리스트 구하기
            let tempPageNumberList = [];
            let curBlockEndPage = state.paging.nextPage === payload.totalPages ? (state.paging.totalPages + 1) : state.paging.nextPage;
            let i = state.paging.prevPage === 1 ? 1 : (state.paging.prevPage + 1);

            for (i; i < curBlockEndPage; i += 1)
            {
                tempPageNumberList.push(i);
            }
            state.paging.pageNumberList = tempPageNumberList;
        }
    },
    actions: {
    }
});