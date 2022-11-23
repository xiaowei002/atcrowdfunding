<%--
  Created by IntelliJ IDEA.
  User: www
  Date: 2022/11/21
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jQuery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#btn1").click(function (){
                $.ajax({
                    url:"send/array",
                    type:"POST",
                    data:{
                        "array":[5,8,12]
                    },
                    dataType:"text",
                    "success":function (response){
                        alert(response)
                    },
                    "error":function (response){
                        alert(response)
                    }
                })
            })
        });

        $(function (){
            $("#btn2").click(function (){
                $.ajax({
                    url:"send/array/two",
                    type:"POST",
                    data:{
                        "array[0]":5,
                        "array[1]":8,
                        "array[2]":12
                    },
                    dataType:"text",
                    "success":function (response){
                        alert(response)
                    },
                    "error":function (response){
                        alert(response)
                    }
                })
            })
        });

        let array = [5, 8, 12];

        let requestBody = JSON.stringify(array);

        $(function (){
            $("#btn3").click(function (){
                $.ajax({
                    url:"send/array/three",
                    type:"POST",
                    data:requestBody,
                    contentType:"application/json;charset=UTF-8",
                    dataType:"text",
                    "success":function (response){
                        alert(response)
                    },
                    "error":function (response){
                        alert(response)
                    }
                })
            })
        });

    </script>
</head>
<body>
<a href="test/ssm">测试ssm整合环境</a>
<br>
 <button id="btn1">测试发送数组</button>

<br>
<button id="btn2">测试发送数组</button>

<br/>
<button id="btn3">测试发送数组</button>


</body>
</html>
