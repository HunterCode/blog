function regist() {
    var username = $("#username").val();
    var nickname = $("#nickname").val();
    var password = $("#password").val();
    var password2 = $("#password2").val();
    var str = /^\w{4,16}$/;
    if(username == "") {
        alert("用户名不能为空");
        return false;
    }
    if(nickname == "") {
        alert("昵称不能为空");
        return false;
    }
    if(password == "") {
        alert("密码不能为空");
        return false;
    }
    if(!str.test(username)) {
        alert("用户名应由4-16位数字或字母组成");
        return false;
    }
    if(!str.test(password)) {
        alert("密码应由4-16位数字或字母组成");
        return false;
    }
    if(password != password2) {
        alert("请确认两次输入的密码是否一致");
        return false;
    }
        $.ajax({
            type: "post",
            url: "/doregist",
            data: {
                "username": username,
                "password": password,
                "nickname": nickname
            },
            success: function (result) {
                if(result) {
                    alert("注册成功！");
                    window.location.replace("/");
                } else {
                    alert("用户名已存在");
                }
            }
        })
    return false;
}