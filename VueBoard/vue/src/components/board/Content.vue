<template>
    <div class="content-row">
        <h1>게시글 상세 페이지</h1>
        <div class="content-btn-box">
            <button v-if="userNameCheck === true" @click="content_modify" class="btn-content-modify">수정</button>
            <button v-if="userNameCheck === true" @click="content_delete" class="btn-content-delete">삭제</button>
            <button @click="cancel" class="btn-content-cancel">취소</button>
        </div>
        <div class="content-box">
            <div class="content-info">
                <p>{{ board.userName }}</p>
                <p>{{ board.modifyDate }}</p>
            </div>
            <div class="content-title">
                <p>#{{ board.id }}</p>
                <p>{{ board.title }}</p>
            </div>
            <div class="content-content">
                <p>{{ board.content }}</p>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            board: {
                id: '',
                title: '',
                content: '',
                userName: '',
                modifyDate: ''
            }
        }
    },
    created() {
        this.fetchData()
    },
    watch: {
        // 라우트 객체를 감시하고 있다가 fetchData()함수를 호출한다.
        '$route': 'fetchData'
    },
    computed: {
        userNameCheck() {
            return this.$store.getters.getUserName === this.board.userName
        }
    },
    methods: {
        content_modify() {
            this.$router.push('/modify/' + this.$route.params.id)
        },
        content_delete() {
            if (window.confirm('게시글을 삭제하시겠습니까?'))
            {
                axios.post('/api/delete', { 
                        id: this.board.id 
                    }).then((res) => {
                        window.alert(res.data.msg)

                        if (res.data.result === 1)
                        {
                            this.$router.push('/')
                        }
                        else
                        {
                            this.$router.replace('/')
                        }
                    }).catch((err) => {
                        window.alert(err)
                        console.log(err)
                    })
            }
        },
        cancel() {
            this.$router.replace('/')
        },
        fetchData() {
            axios.get('/api/content/' + this.$route.params.id)
                .then((res) => {
                    if (res.data.result === 1)
                    {
                        this.board = res.data.board
                    }
                    else
                    {
                        window.alert(res.data.msg)
                        this.$router.replace('/')
                    }
                }).catch((err) => {
                    window.alert(err)
                    console.log(err)
                })
        }
    },
}
</script>

<style>
    .content-row {
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
    .content-btn-box {
        text-align: right;
    }
    .content-btn-box button {
        height: 35px;
        width: 80px;
        border: none;
        border-radius: 5px;
    }
    .btn-content-modify {
        background: #33c197;
        color: white;
    }
    .btn-content-delete {
        background: #cb5659;
        color: white;
    }
    .content-info {
        padding-left: 4%;
    }
    .content-info p {
        display: inline-block;
    }
    .content-info p:nth-of-type(2) {
        float: right;
    }

    .content-title {
        padding-left: 4%;
        padding-bottom: 3%;
        border-bottom: solid 1px #a8a8a8;
    }
    .content-title p {
        display: inline;
    }
    .content-title p:nth-of-type(1) {
        color: #5a5a5a;
    }
    .content-content {
        padding-top: 5%;
        padding-left: 4%;
    }
</style>
