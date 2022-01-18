let index = {
    init:function() {
        // this 를 바인딩하기 위해 arrow function 사용
        $("#btn-save").on("click", ()=> {
            this.save();
        });

        $("#btn-login").on("click", ()=> {
            this.login();
        })
    },

    save:function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
            type:"POST",
            url:"/blog/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("회원가입이 완료되었습니다.");
            location.href='/blog/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    login:function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        }

        $.ajax({
            type:"POST",
            url:"/blog/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("로그인이 완료되었습니다.");
            location.href='/blog';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();