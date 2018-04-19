//页面加载时初始化操作
$(document).ready(function(){
    login();
});

function login() {
    //监听提交按钮
    $("#loginBtn").button().click(function(){
        var userName = $("#username").val();
        var password = $("#password").val();
        //校验用户名和密码
        if(userName == null || userName ==""){
            alert("用户名不能为空！");
            return;
        }
        if(password == null || password == ""){
            alert("用户密码不能为空！");
            return;
        }

        var reqMap ={"userName":userName,"password":password};
        //请求后端服务器，进行用户登录身份验证
        $.ajax({
            url : "user/login",
            type : 'post',
            dataType : 'json',
            data:reqMap,
            success : function(data) {
                console.log(data);
                //如果登录成功，则将用户信息放到cookie里
                if(data.resultCode==200){
                   setCookie("userName",data.data.currentUser.userName);
                   setCookie("roleName",data.data.currentUser.roleName);
                   window.location.href ="main.html";
                }
            },
            error : function () {
                alert("登录失败！");
                window.location.href = "index.html";
            }
        });
        
    });
}