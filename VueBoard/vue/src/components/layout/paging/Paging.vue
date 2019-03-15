<template>
    <div class="paging-row">
        <ul>
            <li v-on:click="movePage(1)">
                <button class="first-page">&lt;&lt;</button>
            </li>
            <li v-on:click="movePage(myPaging.prevPage)">
                <button class="prev-page">&lt;</button>
            </li>
            <li  v-for="index in myPaging.pageNumberList" 
                v-bind:key="index">
                <span v-on:click="movePage(index)"
                    v-if="index === myPaging.pageNumber" 
                    v-text="index" 
                    style="font-weight: bold; font-size: 18px;" 
                    >
                </span>
                <span v-on:click="movePage(index)" 
                    v-else v-text="index">
                </span>
            </li>
            <li v-on:click="movePage(myPaging.nextPage)">
                <button class="next-page">&gt;</button>
            </li>
            <li v-on:click="movePage(myPaging.endPage)">
                <button class="end-page">&gt;&gt;</button>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {
            myPaging: {
                pageNumber: 1, // 현재 페이지 번호
                pageSize: 0, // 페이지 사이즈
                totalPages: 0, // 전체 페이지 갯수
                totalElements: 0, // 전체 게시물 갯수
                firstPage: 1, // 가장 첫 페이지
                prevPage: 0, // 이전 페이지
                nextPage: 0, // 다음 페이지
                endPage: 0, // 끝 페이지
                
                pageNumberList: [1] // 페이지 번호 리스트 (Not index)
            }
        }
    },
    created() {
        this.myPaging = this.$store.getters.getPaging
    },
    methods: {
        movePage(pageNo) {
            this.$bus.$emit('movePage', (pageNo - 1))
        }
    }
}
</script>

<style>
    .paging-row {
        text-align: center;
    }
    .paging-row li {
        list-style-type: none;
        display: inline;
    }
    .paging-row button {
        height: 30px;
        width: 40px;
        border: none;
        background: #44cea5;
        color: white;
        font-weight: bold;
    }
    .paging-row span {
        font-size: 16px;
    }
    .paging-row span::after {
        content: '';
        padding-left: 5px;
        padding-right: 5px;
    }
</style>
