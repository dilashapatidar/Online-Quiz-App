<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<style type="text/css">
body {
	background-color: #e0e5ec;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.login-form form {
	background: #e0e5ez;
	padding: 2rem;
	border-radius: 20px;
	box-shadow: -8px -8px 15px #ffffff, 8px 8px 15px #a3b1c6;
}

input[type="text"], input[type="password"] {
	display: block;
	margin: 1rem 0;
	background: #e0e5ec;
	border: none;
	padding: 10px;
	border-radius: 10px;
	box-shadow: inset 8px 8px 8px #a3b1c6, inset -8px -8px 8px #ffffff;
}

input:focus {
	outline: none;
}

button {
	width: 100%;
	padding: 10px;
	border: none;
	background-color: #e0e5ec;
	border-radius: 10px;
	box-shadow: -2px -2px 5px #ffffff, 2px 2px 5px #a3b1c6;
}

button:hover {
	background-color: #cbd3da;
}
</style>
</head>
<body>
	<div class="login-form">
		<form method="post" action="user-login">
			<input type="text" name="username" placeholder="Enter Username" id="username"/>
			<input type="password" name="password" placeholder="Password" id="password"/>
			<button type="submit">Login</button>
			<a href="/register-user" 
			class="button">Create Account</a>
		</form>
	</div>
</body>
</html>