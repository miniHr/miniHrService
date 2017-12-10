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

        .d1 {
            text-align: center;
            margin: 0 auto;
        }

        .d2 {
            text-align: center;
            margin: 0 auto;
        }

        .d3 {
            text-align: center;
            margin: 0 auto;
            color: #DB7093;
        }

        .d4 {
            text-align: center;
            margin: 0 auto;
        }

        td {
            height: 50px;
        }
    </style>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div class="d1">
    <h1>微信小程序智诚工作人员专用页面</h1>
</div>
<div class="d2">
    本页面可以实现智能招聘会小程序展位的占用和撤销功能，小程序中企业HR占用的展位会即时在此页面中显示。 
    点击登陆码栏的链接即可打开对应的公司收看简历页面
</div>
<div class="d3">
    注意： 每次操作占位前请刷新网页，避免因延迟造成的误占用
</div>
<br/>
<div class="d4">
    查看招聘会场内全部入场简历（有电话号码，自己使用，不可泄露）
    <br/><a href="https://561job.cn/resumeInfo/queryAll">https://561job.cn/resumeInfo/queryAll</a>
</div>
<input id="count" value="${dataCount}" type="hidden"/>
<input id="currentPage" value="${currentPage}" type="hidden"/>
<br/>
<table style="text-align: center; font-size: 11pt; width: 1000px; font-family: 宋体; border-collapse: collapse"
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
        <td>
            <a href="https://561job.cn/resumeInfo/resumeOfCompany?authCode=${booth.authCode?if_exists}">${booth.authCode?if_exists}</a>
        </td>
        <td><input type="submit" name="operation" class="btn" value="占用"
                   <#if (booth.state!='1')>disabled="disabled"</#if>
                   onclick="window.location.href='occupy?id=${booth.id}'"/>
            <input type="submit" name="operation" class="btn" value="撤销"
                   <#if (booth.state=='1')>disabled="disabled"</#if>
                   onclick="window.location.href='revert?id=${booth.id}&companyId=${booth.companyId?if_exists}'"/></td>
    </tr>
</#list>
</table>
<div id="box" class="M-box" style="text-align: center"></div>
</body>
</html>