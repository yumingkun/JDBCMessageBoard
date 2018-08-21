<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">


	</head>
	<body>
		<div class="login">
			<div class="header">
				<h1>
					<a href="/login.do">登录</a>
					<a href="/regPrompt.do">注册</a>
				</h1>
				<button></button>
			</div>

			<form action="${pageContext.request.contextPath}/login.do" method="post">
				<div class="name">
					<input type="text" id="name" name="username" placeholder="请输入登录用户名">
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" id="pwd" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
					<p></p>
				</div>

				<div class="autoLogin">
					<label for="">
						<input type="checkbox" checked="checked">
						下次自动登录
					</label>
					<a href="">忘记密码</a>
				</div>
				<div class="btn-red">
					<input  type="submit" value="登录" id="login-btn">
				</div>
			</form>

		</div>
	</body>
</html>