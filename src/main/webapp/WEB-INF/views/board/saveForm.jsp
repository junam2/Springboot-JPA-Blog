<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form >
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" placeholder="Enter Title" id="title">
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" id="content" rows="5"></textarea>
        </div>

        <button id="btn-board-save" class="btn btn-primary">글 쓰기</button>
    </form>
</div>

<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>