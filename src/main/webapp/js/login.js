//页面加载时初始化操作
$(document).ready(function(){
    login();
});

function login() {
    //监听提交按钮
    $("#loginBtn").button().click(function(){
        var userName = $("#username").val();
        var password = $("#password").val();

        var reqMap ={"userName":userName,"password":password};
        //请求后端服务器，进行用户登录身份验证
        $.ajax({
            url : "user/login.do",
            type : 'post',
            dataType : 'json',
            data:reqMap,
            success : function(data) {
                alert("登录成功！");
            }
        });
        
    });
}