<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <title>所有简历信息</title>
    <style >
        td{
            height:50px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;margin: 0 auto;">招聘会小程序入场简历全列表</h1>
<h4 style="text-align: center;margin: 0 auto;color: #DB7093;">本次招聘会入场求职者全部简历，企业如有需要将会联络智诚人员提供特定求职者的电话号码</h4>
<table style="text-align: center; font-size: 11pt; width: 1200px; font-family: 宋体; border-collapse: collapse"
       borderColor=#000000 cellSpacing=0 cellPadding=0 align=center border=3>
    <tr style="border: 3px;">
        <td><b>序号</b></td>
        <td><b>姓名</b></td>
        <td><b>年龄</b></td>
        <td><b>性别</b></td>
        <td><b>手机号</b></td>
        <td><b>行业</b></td>
        <td><b>学历</b></td>
        <td><b>专业</b></td>
        <td><b>工作时间(年)</b></td>
        <td><b>期望工作地点</b></td>
        <td><b>期望工作类型</b></td>
        <td><b>是否接受外地</b></td>
    </tr>
<#list resumes as resume>
    <tr style="border: 3px;">
        <td><b>${resume.id?c}</b></td>
        <td><b>${resume.name}</b></td>
        <td><b>${resume.age}</b></td>
        <td><b><#if (resume.sex=='1')>女<#else>男</#if></b></td>
        <td><b>${resume.phone}</b></td>
        <td><b>${resume.industry}</b></td>
        <td><b>${resume.education}</b></td>
        <td><b>${resume.major}</b></td>
        <td><b>${resume.workTime}</b></td>
        <td><b>${resume.expectedBase}</b></td>
        <td><b>${resume.expectedJob}</b></td>
        <td><b><#if (resume.acceptOut=='1')>否<#else>是</#if></b></td>
    </tr>
</#list>
</table>
</body>
</html>