<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="utf-8">
<title>企业注册</title>
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
			<td><b>公司名称</b></td>
			<td><b>状态</b></td>
			<td><b>操作</b></td>
		</tr>
		<#list boothInfos as booth>
		<tr>
			<td>${booth.id}</td>
			<td>${booth.companyName}</td>
			<td><#if (booth.state=='2')>可购买<#else>已被占用</#if></td>
			<td><input type="button" class="btn" value="占用" />
			<input type="button" class="btn" value="撤销" /></td>
		</tr>
		</#list>
	</table>
</body>
</html>