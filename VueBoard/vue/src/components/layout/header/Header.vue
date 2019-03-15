<template>
    <div class="header">
        <div class="header-logo">
            <img src="../../../assets/logo.png">
        </div>
        <div class="header-menu">
            <ul>
                <li v-if="loginFlagCheck === false">
                    <router-link to="/login">로그인</router-link>
                </li>
                <li v-if="loginFlagCheck === true">
                    <a v-on:click="logout">로그아웃</a>
                </li>
                <li v-if="loginFlagCheck === false">
                    <router-link to="/join">회원가입</router-link>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    computed: {
        loginFlagCheck() {
            return this.$store.getters.getLoginFlag
        }
    },
    methods: {
        logout() {
            if (window.confirm('로그아웃 하시겠습니까?'))
            {
                if (window.confirm('정말요...? \n진짜 로그아웃하실건가요...?'))
                {
                    this.$store.commit('logoutFlush')
                    window.alert('다시 만나요... \n꼭이요...')
                    this.$router.replace('/')
                }
            }
        }
    }
}
</script>

<style>
    .header-logo {
        float: left;
    }
    .header-logo img {
        width: 100px;
    }
    .header-menu {
        float: right;
    }
    .header-menu li {
        list-style-type: none;
        display: inline;
    }
    .header-menu li::after {
        content: '|';
        padding-left: 10px;
        padding-right: 3px;
        color: #cacaca;
    }
    .header-menu li:last-child::after {
        content: '';
    }
    a:link, a:hover, a:visited {
        text-decoration: none;
        color: black;
    }
</style>
