<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <title>展位信息</title>
    <style>
        .btn {
            width: 50px;
            height: 20px;
            line-height: 18px;
            font-size: 15px;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
    <table
            style="text-align: center; FONT-SIZE: 11pt; WIDTH: 600px; FONT-FAMILY: 宋体; BORDER-COLLAPSE: collapse"
            borderColor=#3399ff cellSpacing=0 cellPadding=0 align=center border=1>
        <tr>
            <td><b>展位号</b></td>
            <td><b>公司ID</b></td>
            <td><b>公司名称</b></td>
            <td><b>状态</b></td>
            <td><b>联系人</b></td>
            <td><b>联系电话</b></td>
            <td><b>登陆码</b></td>
            <td><b>操作</b></td>
        </tr>
    <#list boothInfos as booth>
        <tr>
            <td>${booth.id}</td>
            <td>${booth.companyId?if_exists}</td>
            <td>${booth.companyName?if_exists}</td>
            <td><#if (booth.state=='1')>可购买<#else>已被占用</#if></td>
            <td>${booth.name?if_exists}</td>
            <td>${booth.phone?if_exists}</td>
            <td>${booth.authCode?if_exists}</td>
            <td><input type="submit" name="operation" class="btn" value="占用"
                       <#if (booth.state!='1')>disabled="disabled"</#if> onclick="window.location.href='occupy?id=${booth.id}'"/>
                <input type="submit" name="operation" class="btn" value="撤销"
                       <#if (booth.state=='1')>disabled="disabled"</#if> onclick="window.location.href='revert?id=${booth.id}&companyId=${booth.companyId?if_exists}'"/></td>
        </tr>
    </#list>
    </table>
</body>
</html>