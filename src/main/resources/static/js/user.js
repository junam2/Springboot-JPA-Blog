let index = {
    init:function() {
        // this 를 바인딩하기 위해 arrow function 사용
        $("#btn-save").on("click", ()=> {
            this.save();
        });

        $("#btn-update").on("click", ()=> {
            this.update();
        });
    },

    save:function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
            type:"POST",
            url:"/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("회원가입이 완료되었습니다.");
            location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update:function() {
        let data = {
            id: $("#id").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
            type:"PUT",
            url:"/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("회원수정이 완료되었습니다.");
            location.href='/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

}

index.init();