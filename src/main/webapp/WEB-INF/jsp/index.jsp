<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="height:100%">
<head>
    <title></title>
    <script type="text/javascript" src="/js/jquery1.42.min.js"></script>
</head>
<body style="padding: 10px">
    <h1>欢迎你，${username}</h1>
    <input id="token" type="text" value="${token}">
    <button onclick="test()">测试</button>
</body>
</html>
<script type="text/javascript">
    var token = document.getElementById("token").value;
    function test() {
        $.ajax({
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", token)
            },
            url:"/index/text",
            type:"post",
            dataType:'json',
            success:function (data) {

            }

        })
    }
</script>
