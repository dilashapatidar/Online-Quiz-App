<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quiz Page</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.quiz-container {
	width: 60%;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.question {
	display: none;
}

.question.active {
	display: block;
}

.options button {
	display: block;
	width: 100%;
	padding: 15px;
	margin: 10px 0;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	font-size: 1em;
	transition: background-color 0.3s, transform 0.3s;
}

.options button:hover {
	transform: translateY(-3px);
}

.result {
	display: none;
	margin-top: 20px;
	text-align: center;
}

.result h2 {
	color: #4CAF50;
}

.popup {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	z-index: 1000;
}

.popup.active {
	display: block;
}

.popup .close {
	text-align: right;
}

.popup .close button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 5px 10px;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="quiz-container">
		<c:forEach var="question" items="${questions}" varStatus="status">
			<div id="question${status.index + 1}" class="question ${status.index == 0 ? 'active' : ''}">
				<h2>Question ${status.index + 1}: ${question.question}</h2>
				<div class="options">
					<button onclick="checkAnswer(${status.index + 1}, 1 == '${question.correctAnswer}')">${question.option1}</button>
					<button onclick="checkAnswer(${status.index + 1}, 2 == '${question.correctAnswer}')">${question.option2}</button>
					<button onclick="checkAnswer(${status.index + 1}, 3 == '${question.correctAnswer}')">${question.option3}</button>
					<button onclick="checkAnswer(${status.index + 1}, 4 == '${question.correctAnswer}')">${question.option4}</button>
				</div>
			</div>
		</c:forEach>
		<div class="result">
			<h2>Your Score: <span id="score">0</span>/100</h2>
			<form method="post" action="/submit-answers">
				<input type="hidden" name="quizId" value="${quizId}">
				<input type="hidden" name="score" id="final_score">
				<input class="btn btn-primary" type="submit">
			</form>
		</div>
		<div id="popup" class="popup">
			<div class="close">
				<button onclick="closePopup()">OK</button>
			</div>
			<div id="popupContent"></div>
		</div>
	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>

	<script>
    // Initialize variables
    let currentQuestion = 1;
    let score = 0;
    let quizCompleted = false; // Flag to track if quiz is completed

    // Check if localStorage has data and initialize quiz accordingly
    if (localStorage.getItem('currentQuestion') && localStorage.getItem('score')) {
        currentQuestion = parseInt(localStorage.getItem('currentQuestion'));
        score = parseInt(localStorage.getItem('score'));

        // Show the correct question based on localStorage
        showQuestion(currentQuestion);
    } else {
        // If localStorage is empty, show the first question
        showQuestion(1);
    }

    // Function to show a specific question and hide others
    function showQuestion(questionNumber) {
        // Hide all questions
        document.querySelectorAll('.question').forEach(question => {
            question.classList.remove('active');
        });

        // Show the specified question if quiz is not completed
        if (!quizCompleted) {
            document.getElementById('question' + questionNumber).classList.add('active');
        }
    }

    // Function to handle answering a question
    function checkAnswer(questionNumber, isCorrect) {
        if (quizCompleted) {
            return; // Exit function if quiz is already completed
        }

        let popupContent = document.getElementById('popupContent');
        if (isCorrect) {
            score += 10;
            popupContent.innerHTML = `<p>Correct Answer!</p>`;
        } else {
            popupContent.innerHTML = `<p>Incorrect Answer!</p>`;
        }
        document.getElementById('popup').classList.add('active');
        document.getElementById('score').innerText = score;
        document.getElementById('final_score').value = score;

        setTimeout(() => {
            // Move to the next question if not the last one
            if (questionNumber < 10) {
                showQuestion(questionNumber + 1);
                currentQuestion = questionNumber + 1;
                // Store current progress in localStorage
                localStorage.setItem('currentQuestion', currentQuestion);
                localStorage.setItem('score', score);
            } else {
                // Show the result if it's the last question
                document.querySelector('.result').style.display = 'block';
                quizCompleted = true; // Mark quiz as completed
                clearLocalStorage(); // Clear localStorage on quiz completion
            }
        }, 0);
    }

    function closePopup() {
        document.getElementById('popup').classList.remove('active');
    }

    // Function to clear localStorage on quiz completion or submission
    function clearLocalStorage() {
        localStorage.removeItem('currentQuestion');
        localStorage.removeItem('score');
    }

    // Call clearLocalStorage() on form submission
    document.querySelector('form').addEventListener('submit', clearLocalStorage);
</script>

	<script>
// Check if the page is loaded from cache and redirect if necessary
if (performance.navigation.type === 2) {
    // 2 is the value for TYPE_BACK_FORWARD navigation
    location.replace("/user-dashboard"); // Redirect to thank you page
}

//Disable back functionality
history.pushState(null, null, location.href);
window.onpopstate = function () {
    history.go(1);
};
</script>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	%>

</body>
</html>
