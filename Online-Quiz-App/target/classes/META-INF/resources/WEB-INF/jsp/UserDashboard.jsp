<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Dashboard</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
		<a class="navbar-brand m-1" href="#">Quizzo</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/user-dashboard">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="/history">History</a></li>
				<li class="nav-item"><a class="nav-link" href="/leaderboard">Leaderboard</a></li>
			</ul>
		</div>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
		</ul>
	</nav>
	<div class="container">
		<h1>Available Quizzes</h1>
		<table class="table" style="width: 75%; margin: 0 auto">
			<thead>
				<tr>
					<th>Title</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${quizzes}" var="quiz">
					<tr>
						<td style="width: 85%">${quiz.quizTitle}</td>
						<td><a href="/start-quiz?quizId=${quiz.quizId}"
							class="btn btn-success">Start Test</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	<script>
		// Check if the page is loaded from cache and redirect if necessary
		if (performance.navigation.type === 2) {
			// 2 is the value for TYPE_BACK_FORWARD navigation
			location.replace("/user-dashboard"); // Redirect to thank you page
		}
	</script>


	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>
</body>
</html>