<template>
    <div class="write-row">
        <h1>글쓰기</h1>
        <div class="write-title-box">
            <input type="text" placeholder="제목" v-model="title">
        </div>
        <div class="write-content-box">
            <textarea placeholder="내용" v-model="content"></textarea>
        </div>
        <div class="write-btn-box">
            <button v-on:click="write">작성</button>
            <button v-on:click="cancel">취소</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            title: '',
            content: '',
        }
    },
    methods: {
        write() {
            if (this.title.length > 0 && this.content.length > 0)
            {
                axios.post('/api/write', {
                    userName: this.$store.getters.getUserName,
                    title: this.title,
                    content: this.content
                }).then((res) => {
                    window.alert(res.data.msg)
                    
                    if (res.data.result === 1)
                    {
                        this.$router.push('/')
                    }
                    else
                    {
                        this.$router.replace('/write')
                    }
                }).catch((err) => {
                    window.alert(err)
                    console.log(err)
                })
            }
            else
            {
                window.alert('모든 내용을 작성하고 시도해주세요.')
            }
        },
        cancel() {
            this.$router.replace('/')
        }
    }
}
</script>

<style>
    .write-row {
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
    .write-title-box {
        padding-bottom: 4%;
    }
    .write-title-box input {
        padding-left: 4%;
        height: 30px;
        width: 85%;
        border-style: none;
        border-bottom: solid 1px #a8a8a8;
        outline: none;
    }
    .write-content-box textarea {
        padding-top: 5%;
        padding-left: 4%;
        height: 500px;
        width: 85%;
        border-style: none;
        border-top: solid 1px #a8a8a8;
        border-bottom: solid 1px #a8a8a8;
        outline: none;
    }
    .write-btn-box {
        padding-right: 8%;
        text-align: right;
    }
    .write-btn-box button {
        height: 35px;
        width: 80px;
        border: none;
        border-radius: 5px;
    }
    .write-btn-box button:first-child {
        background: #33c197;
        color: white;
    }
</style>
