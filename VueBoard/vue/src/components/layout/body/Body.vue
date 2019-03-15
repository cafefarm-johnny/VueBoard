<template>
    <div class="body-row">
        <h1>메인 페이지</h1>
        <div class="body-btn-box">
            <router-link to="/write"><button>글쓰기</button></router-link>
        </div>
        <div class="table-box">
            <div class="tb">
                <div class="tb-header">
                    <p>번호</p>
                    <p>제목</p>
                    <p>글쓴이</p>
                    <p>작성일</p>
                </div>
                <div class="tb-body">
                    <div class="tb-row" v-for="(item) in boardList" :key="item.id">
                        <p>{{ item.id }}</p>
                        <p><a @click="enterContent(item.id)">{{ item.title }}</a></p>
                        <p>{{ item.userName }}</p>
                        <p>{{ item.modifyDate }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="paging-box">
            <Paging></Paging>
        </div>
    </div>
</template>

<script>
import Paging from '../paging/Paging';
import axios from 'axios';

export default {
    components: {
        'Paging': Paging
    },
    data() {
        return {
            list: '',
            boardList: ''
        }
    },
    created() {
        this.fetchData(0)
        this.$bus.$on('movePage', (pageNo) => {
            this.fetchData(pageNo)
        })
    },
    watch: {
        '$route': 'fetchData',
    },
    methods: {
        fetchData(pageNo) {
            axios.get('/api/list/' + pageNo)
                .then((res) => {
                    this.boardList = res.data.list.content

                    // Vuex Store에 페이징 정보 설정
                    this.$store.commit('pageFlush', {
                        pageNumber: res.data.list.number,
                        pageSize: res.data.list.size,
                        totalPages: res.data.list.totalPages,
                        totalElements: res.data.list.totalElements
                    })
                })
                .catch((err) => {
                    window.alert(err)
                    console.log(err)
                })
        },
        enterContent(boardId) {
            this.$router.push('/content/' + boardId)
        }
    }
}
</script>

<style>
    .body-row {
        clear: both;
        margin: 0 auto;
        padding-top: 3%;
        padding-left: 10%;
        padding-right: 10%;
        height: 900px;
        background-color: white;
        box-shadow: 3px 3px 3px 3px #d0d0d0;
    }
    h1 {
        padding-bottom: 5%;
    }
    .body-btn-box {
        text-align: right;
    }
    .body-btn-box button {
        height: 35px;
        width: 80px;
        border: none;
        border-radius: 5px;
        background: #33c197;
        color: white;
    }
    .tb-header {
        border-bottom: solid 1px black;
    }
    .tb-row {
        border-bottom: solid 1px #d0d0d0;
    }
    .tb p {
        display: inline-block;
    }
    .tb p:nth-child(1) {
        width: 10%;
    }
    .tb p:nth-child(2) {
        width: 60%;
    }
    .tb p:nth-child(3) {
        width: 15%;
    }
</style>