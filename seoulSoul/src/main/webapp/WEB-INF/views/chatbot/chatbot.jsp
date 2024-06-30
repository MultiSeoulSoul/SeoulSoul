<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainDesign.css">
<title>서울소울 고객센터 챗봇</title>

<style type="text/css">
h1 {
	padding-bottom: 10px;
	text-align: center;
}
.content {
    width: 90%;
    margin: 0 auto;
}
.icon-container {
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center;
}
img {
	width: 60%;
}
.message-container {
    max-width: 400px;
    margin: 0 auto;
    margin-bottom: 50px; /* 입력창 공간 확보 */
}
.message {
    width: fit-content;
    max-width: 80%;
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 10px;
}
.chatbot {
    text-align: left;
    background-color: #e0f7fa;
    float: left;
    clear: both;
}
.user {
    text-align: right;
    background-color: #fff9c4;
    float: right;
    clear: both;
}
.floating {
    position: fixed;
    bottom: 0;
    width: 100%;
    background-color: white;
    padding: 10px 0;
    border-top: 1px solid #ccc;
}
.floating table {
    width: 100%;
    max-width: 400px;
    margin: 0 auto;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function connect() {
        // 초기 연결 설정
    }

    function sendMessage() {
        var message = $('#text').val();
        if(message) {
            // 사용자 메시지를 화면에 표시
            $('.message-container').append('<div class="message user">' + message + '</div>');

            // 서버로 메시지 전송
            $.ajax({
                url: '${pageContext.request.contextPath}/chatbot/sendMessage',
                type: 'POST',
                data: { message: message },
                success: function(response) {
                    // 챗봇 응답을 화면에 표시
                    $('.message-container').append('<div class="message chatbot">' + response.response + '</div>');
                }
            });

            // 입력창 초기화
            $('#text').val('');
        }
    }
    //JavaScript에서 이벤트 리스너: 키보드 이벤트 처리
    $(document).ready(function() {
        $('#text').on('keydown', function(event) { //textarea는 기본적으로 Enter 키를 눌렀을 때 줄바꿈이 됨
            if (event.key === 'Enter' && !event.shiftKey) { //Enter 키 했을 경우: 기본 Enter 키 동작(줄바꿈)을 방지 + 메시지를 전송
                event.preventDefault(); //기본 Enter 키 동작(줄바꿈)을 방지
                sendMessage(); //메시지를 전송
            } else if (event.key === 'Enter' && event.shiftKey) {  //Shift 키 + Enter 키 했을 경우: 기본 Enter 키 동작 유지(줄바꿈)
               
            }
        });
    });
</script>
</head>
<body onload="connect();">

    <div class="content">
    
        <!-- 챗봇 로고 -->
        <div class="icon-container">
            <img src="${pageContext.request.contextPath}/resources/img/csChatbotIcon.png" alt="Chatbot Icon">
        </div>
        
        <h1>서울소울 고객센터 챗봇</h1>
        <p>설명글</p>
        
        <!-- 대화창 -->
        <div class="message-container"></div>        
    
    </div>
    
    <!-- 사용자 메시지 입력창 -->
     <div class="form-group">
	     <div class="floating">
		     <table>
                <tr>
                    <td><textarea id="text" style="width: 320px; background: white; height: 50px;"></textarea></td>
                    <td><button id="sendMessage" onclick="sendMessage();">전송</button></td>
                </tr>
             </table>
		 </div>
	</div>

</body>
</html>
