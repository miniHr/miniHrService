<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <title>所有简历信息</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <style>
        td {
            height: 50px;
        }
    </style>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/jquery-ui.js"></script>
    <script src="/js/jquery-table2excel.js"></script>
    <script>
        $(function () {
            var dateString = "${begin?if_exists}";
            $("#begin").datepicker({
                dateFormat: "yy-mm-dd",
                changeYear: true,
                changeMonth: true,
                monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            });
            $("#end").datepicker({
                dateFormat: "yy-mm-dd",
                changeYear: true,
                changeMonth: true,
                monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
            });
        })

        function selectResumes() {
            var begin = $("#begin").val();
            var end = $("#end").val();
            window.location.href = 'select?begin=' + begin + "&end=" + end;
        };

        function deleteResumes() {
            var begin = $("#begin").val();
            var end = $("#end").val();
            window.location.href = 'delete?begin=' + begin + "&end=" + end;
        };

        function exportResumes() {
            $("#resumeTable").table2excel({
                name: "简历信息",
                filename: "简历信息${begin?if_exists}-${end?if_exists}",
                exclude_img: true,
                exclude_links: true,
                exclude_inputs: true
            });
        }
    </script>
</head>
<body>
<h1 style="text-align: center;margin: 0 auto;">招聘会小程序入场简历全列表</h1>
<h4 style="text-align: center;margin: 0 auto;color: #DB7093;">本次招聘会入场求职者全部简历，企业如有需要将会联络智诚人员提供特定求职者的电话号码</h4>
<div style="margin-top: 3%;margin-bottom: 3%;margin-left: 10%">
    <label>筛选简历提交时间 开始：</label><input id="begin" type="text" class="datepicker" value="${begin?if_exists}"/>
    <label>结束：</label><input id="end" type="text" class="datepicker" value="${end?if_exists}"/>
    <input style="margin-left: 5%" type="submit" value="显示筛选" onclick="selectResumes()"/>
    <input style="margin-left: 5%" type="submit" value="删除筛选简历" onclick="deleteResumes()"/>
    <input style="margin-left: 5%" type="submit" value="导出筛选简历" onclick="exportResumes()"/>
</div>
<h3 style="text-align: center;margin: 0 auto;">查询总数：${count?c}</h3>
<table style="text-align: center; font-size: 11pt; width: 1250px; font-family: 宋体; border-collapse: collapse"
       borderColor=#000000 cellSpacing=0 cellPadding=0 align=center border=3 id="resumeTable">
    <tr style="border: 3px;">
        <td><b>序号</b></td>
        <td><b>提交时间</b></td>
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
        <td><b>${resume.createDt?string('yyyy-MM-dd')}</b></td>
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