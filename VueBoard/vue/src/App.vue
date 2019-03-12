<template>
    <div id="app">
        <Header></Header>
        <router-view></router-view>
        <Footer></Footer>
    </div>
</template>

<script>
import Header from './components/layout/header/Header';
import Footer from './components/layout/footer/Footer';

export default {
    components : {
        'Header' : Header,
        'Footer' : Footer
    }
}
</script>

<style>
    body {
        background-color: #ebebeb;
    }
    #app {
        padding-left: 25%;
        padding-right: 25%;
    }
</style>
