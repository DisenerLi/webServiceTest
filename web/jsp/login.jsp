<%--
  Created by IntelliJ IDEA.
  User: LJ0000276
  Date: 2017/6/7
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="false" pageEncoding="UTF-8" %>
<html>
<head>
    <title>login</title>
    <link rel="icon" href="/webService/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js" ></script>
</head>
<body>
<script>

    ;(function($) {
        var citys
        var data ;
        $.ajax({
            url: 'https://zt.api.bnq.com.cn/bnq_owner/apis/common/getCitiesAndShop.share',
            dataType: 'json',
            method: 'get',
            success: function (data) {
                if (data.response.code == 0) {
                    console.log(data, 'mydata');
                    data = data.response.data;
                    citys = data.citys;
                    try {
                    var option = '<option value="请选择">-请选择-</option>';
                    citys.forEach(function(item,index){
                        option += '<option data-index="' + index + '" value="' + item.name + '">' + item.name + '</option>'
                    });
                    }catch (e) {

                    }
                    $('#city').empty().append(option);
                } else {
                    alert(data.response.message + '请刷新页面重试');
                }

            },
            error: function (err) {
                console.log(err);
                errorMsg = setTimeout(function () {
                    alert('网络或服务器异常，请稍后重试！');
                }, 1500)
            }
        });

        $(document).on('change', '#city', function(){
            var current = $('#city option:selected').attr('data-index');
            var opt = '<option value="请选择">-请选择-</option>';
            try {
                citys[current].shops.forEach(function (item, index) {
                    opt += '<option value="' + item.shopCode + '">' + item.shopName + '</option>';
                });
            }catch (e) {

            }

            $('#shop').empty().append(opt);
        });

    })(jQuery);

</script>
<h1>登录</h1>
<div class="login-contain">
    <span>账号登录</span>
    <form action="login.do" method="post" name="loginForm" >
        <div>用户:<input type="text" name="username" placeholder="请输入用户名"/></div>
        <div>密码:<input type="password" name="password" placeholder="请输入密码"/></div>
        <span> <input type="submit" name="submit" content="提交" value="提交" ></span>
        <div>城市：<select id="city" name="city">
        </select></div>
        <div>门店：<select id="shop" name="shop">
        </select></div>
    </form>
</div>
</body>
</html>
