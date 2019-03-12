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
                    <div class="tb-row">
                        <p>1</p>
                        <p>테스트용 글</p>
                        <p>쟌쟌쟈니</p>
                        <p>2017-05-06</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            list: ''
        }
    },
    methods: {
        getList() {
            axios.get('/api/list')
                .then((res) => {
                    console.log(res)
                })
                .catch((err) => {
                    window.alert(err)
                    console.log(err)
                })
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
        height: 900px;
        width: 50%;
        background-color: white;
        box-shadow: 3px 3px 3px 3px #d0d0d0;
    }
    h1 {
        padding-bottom: 5%;
    }
    .body-btn-box {
        padding-right: 15%;
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
        width: 85%;
        border-bottom: solid 1px black;
    }
    .tb-body {
        width: 85%;
    }
    .tb-row {
        border-bottom: solid 1px #d0d0d0;
    }
    p {
        display: inline-block;
    }
    p:nth-child(1) {
        width: 10%;
    }
    p:nth-child(2) {
        width: 45%;
    }
    p:nth-child(3) {
        width: 15%;
    }
</style>