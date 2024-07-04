<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
		<a class="navbar-brand m-1" href="#">Quizzo</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/admin-dashboard">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="/leaderboard">Leaderboard</a></li>
			</ul>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/admin-logout">Logout</a></li>
		</ul>
	</nav>
	<div class="container">
		<h1>Your Quizzes</h1>
		<table class="table" style="width:75%; margin:0 auto">
			<thead>
				<tr>
					<th>Title</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${quizzes}" var="quiz">
					<tr>
						<td style="width:90%">${quiz.quizTitle}</td>
						<td><a href="delete-quiz?quizId=${quiz.quizId}" class="btn btn-warning">Delete</a></td>
						<td><a href="update-quiz?quizId=${quiz.quizId}" class="btn btn-success">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/add-quiz" class="btn btn-success">Add Quiz</a>
	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>