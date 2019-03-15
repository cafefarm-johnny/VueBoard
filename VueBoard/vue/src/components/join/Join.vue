<template>
    <div class="join-row">
        <div class="join-form">
            <h1>회 원 가 입</h1>
            <p>
                <input type="text" placeholder="이름을 입력해주세요." autofocus v-model="userName">
            </p>
            <p>
                <input type="text" placeholder="ID를 입력해주세요." v-model="userId">
            </p>
            <p>
                <input type="password" placeholder="Password를 입력해주세요." v-model="userPwd">
            </p>
            <p>
                <button @click="join">가입</button>
                <button @click="cancel">취소</button>
            </p>
        </div>
    </div>
</template>

<script>
import axios from 'axios'; // 지역으로 등록하기 위한

export default {
    data() {
        return {
            userName : '',
            userId : '',
            userPwd : ''
        }
    },
    methods: {
        join() {
            if (this.userName && this.userId && this.userPwd)
            {
                axios.post('/api/join', {
                    // 서버로 전달할 JSON 데이터
                    userName: this.userName,
                    userId: this.userId,
                    userPwd: this.userPwd
                    }).then((res) => {
                        window.alert(res.data.msg)

                        if (res.data.result === 1)
                        {
                            this.$router.push('/')
                        }
                        else if (res.data.result === -1)
                        {
                            this.$router.replace('/join')
                        }
                        else
                        {
                            return;
                        }
                    }).catch((err) => {
                        window.alert(err)
                        console.log(err)
                    })
            }
            else
            {
                window.alert('모든 입력란을 작성해주세요.')
            }
        },
        cancel() {
            this.$router.replace('/')
        }
    },
}
</script>

<style>
    .join-row {
        clear: both;
        padding-top: 4%;
    }
    .join-form {
        margin: 0 auto;
        padding-top: 4%;
        padding-left: 10%;
        height: 400px;
        background-color: white;
        box-shadow: 3px 3px 3px 3px #d0d0d0;
    }
    h1 {
        padding-bottom: 50px;
    }
    .join-form input {
        border-style: none;
        border-bottom: solid black 1px;
        height: 30px;
        width: 80%;
        outline: none;
        padding-left: 2%;
    }
    .join-form p:last-child {
        text-align: right;
        padding-right: 15%;
    }
    .join-form button {
        height: 35px;
        width: 80px;
        border: none;
        border-radius: 5px;
    }
    .join-form button:first-child {
        background: #33c197;
        color: white;
    }
</style>
