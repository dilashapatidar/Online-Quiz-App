<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Quiz</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light mb-3 p-1">
		<a class="navbar-brand m-1" href="#">Quizzo</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="/admin-dashboard">Dashboard</a></li>
			</ul>
		</div>
	</nav>
	<div class=container>
		<form:form method="post" modelAttribute="quiz">

			<fieldset class="mb-5">
				<form:label path="quizTitle" placeholder="Enter Quiz Title">Enter Quiz Title</form:label>
				<form:input type="text" path="quizTitle" required="required" />
				<form:errors path="quizTitle" cssClass="text-warning" />
			</fieldset>

			<c:forEach var="question" items="${quiz.questions}" varStatus="status">
            <fieldset class="mb-5">
                <form:label class="mb-3" path="questions[${status.index}].question">Question ${status.index + 1}</form:label>
                <form:textarea style="width:50%" path="questions[${status.index}].question" placeholder="Enter Question" required="required" />
                <br>Enter Options and Mark the correct one <form:radiobutton path="questions[${status.index}].correctAnswer" value='1' required="required"/>
                <form:input path="questions[${status.index}].option1" placeholder="Option 1" required="required" />
                <form:radiobutton path="questions[${status.index}].correctAnswer" value='2' required="required"/>
                <form:input path="questions[${status.index}].option2" placeholder="Option 2" required="required" />
                <form:radiobutton path="questions[${status.index}].correctAnswer" value='3' required="required"/>
                <form:input path="questions[${status.index}].option3" placeholder="Option 3" required="required" />
                <form:radiobutton path="questions[${status.index}].correctAnswer" value='4' required="required"/>
                <form:input path="questions[${status.index}].option4" placeholder="Option 4" required="required" />
                <form:errors path="questions[${status.index}]" cssClass="text-warning" />
            </fieldset>
        </c:forEach>

			<input type="submit" class="btn btn-success" />

		</form:form>

	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>