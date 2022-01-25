let index = {
    init:function() {
        // this 를 바인딩하기 위해 arrow function 사용
        $("#btn-board-save").on("click", ()=> {
            this.save();
        });

        $("#btn-board-delete").on("click", ()=> {
            this.deleteById();
        });

        $("#btn-board-update").on("click", ()=> {
            this.update();
        });
    },

    save:function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type:"POST",
            url:"/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("글쓰기가 완료되었습니다.");
            location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    deleteById:function() {
        let id = $("#id").text();

        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            dataType: "json"
        }).done(function(response) {
            alert("삭제가 완료되었습니다.");
            location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update:function() {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type:"PUT",
            url:"/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("글 수정이 완료되었습니다.");
            location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
}

index.init();