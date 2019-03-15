<template>
    <div class="modify-row">
        <h1>수정</h1>
        <div class="modify-title-box">
            <input type="text" placeholder="제목" v-model="board.title">
        </div>
        <div class="modify-content-box">
            <textarea placeholder="내용" v-model="board.content"></textarea>
        </div>
        <div class="modify-btn-box">
            <button v-on:click="modify">수정</button>
            <button v-on:click="cancel">취소</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            board: {
                title: '',
                content: '',
            }
        }
    },
    created() {
        this.fetchData()
    },
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData() {
            axios.get('/api/content/' + this.$route.params.id)
                .then((res) => {
                    this.board = null

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
        },
        modify() {
            if (this.board.title.length > 0 && this.board.content.length > 0)
            {
                if (window.confirm('게시글을 수정하시겠습니까?'))
                {
                    axios.post('/api/modify', this.board)
                        .then((res) => {
                            window.alert(res.data.msg)

                            if (res.data.result === 1)
                            {
                                this.$router.push('/content/' + this.$route.params.id)
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
            }
        },
        cancel() {
            this.$router.replace('/content/' + this.$route.params.id)
        }
    }
}
</script>

<style>
    .modify-row {
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
    .modify-title-box {
        padding-bottom: 4%;
    }
    .modify-title-box input {
        padding-left: 4%;
        height: 30px;
        width: 100%;
        border-style: none;
        border-bottom: solid 1px #a8a8a8;
        outline: none;
    }
    .modify-content-box textarea {
        padding-top: 5%;
        padding-left: 4%;
        height: 500px;
        width: 100%;
        border-style: none;
        border-top: solid 1px #a8a8a8;
        border-bottom: solid 1px #a8a8a8;
        outline: none;
    }
    .modify-btn-box {
        text-align: right;
    }
    .modify-btn-box button {
        height: 35px;
        width: 80px;
        border: none;
        border-radius: 5px;
    }
    .modify-btn-box button:first-child {
        background: #33c197;
        color: white;
    }
</style>
