<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Leaderboard</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
		<a class="navbar-brand m-1" href="#">Quizzo</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/leaderboard">Leaderboard</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h1>Leaderboard : "${quizTitle}"</h1>
		<table class="table" style="width: 75%; margin: 0 auto">
			<thead>
				<tr>
					<th>Username</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${quizLeadScores}" var="record">
					<tr>
						<td style="width: 85%">${record.getKey()}</td>
						<td>${record.getValue()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

</body>
</html>