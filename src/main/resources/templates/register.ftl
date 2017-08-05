<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <title>企业注册</title>
</head>
<link rel="stylesheet" href="/css/miniHr.css" type="text/css" />
<script src="/js/jquery-3.2.1.min.js">

</script>
<body>
<div id="stylized" class="myform">
    <form id="form" name="form" method="post" action="/admin/register">
        <input type="hidden" name="boothId" value="${id}">
        <h1>公司注册</h1>
        <p>您预订的是${id}号展位</p>
        <label>
            <span class="small">公司名称</span>
        </label>
        <input type="text" name="companyName" id="companyName"/>
        <label>
            <span class="small">公司联系人</span>
        </label>
        <input type="text" name="name" id="name"/>
        <label>
            <span class="small">公司联系电话</span>
        </label>
        <input type="text" name="phone" id="phone"/>
        <button type="submit">提交</button>
        <div class="spacer"></div>
    </form>
</div>
</body>
</html>