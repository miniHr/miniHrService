<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<meta charset="utf-8">
<title>企业注册</title>
<script type="text/javascript">
	function check() {
		var file = document.getElementById("uploadFile").value;
		if (file == "") {
			alert("请选择需要上传的文件！");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>
	<form enctype="multipart/form-data" method="post"
		action="/admin/insertCompany" onsubmit="return check()">
		<input id="uploadFile" type="file" name="companyInfo" /><br /> <input
			type="submit" value="提交">
	</form>
</body>
</html>