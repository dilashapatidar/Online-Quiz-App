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
		  	
		</div>
	</nav>
	<div class="container">
		<h1>Leaderboard</h1>
		<table class="table mb-3" style="width: 75%; margin: 0 auto">
			<thead>
				<tr>
					<th>Quiz</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${quizzes}" var="quiz">
					<tr>
						<td style="width: 85%">${quiz.quizTitle}</td>
						<td><a href="/quiz-leaderboard?quizId=${quiz.quizId}&quizTitle=${quiz.quizTitle}"
							class="btn btn-success">View Leaderboard</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h1>Overall</h1>
		<table class="table" style="width: 75%; margin: 0 auto">
			<thead>
				<tr>
					<th>Username</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lifetimeLeadScores}" var="record">
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