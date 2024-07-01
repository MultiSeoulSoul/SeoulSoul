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
.content2 {
    width: 90%;
    margin: 0 auto;
    overflow-y: auto; /* 스크롤 가능 */
    margin-bottom: 100px; /* 입력창 공간 확보 */
}
.message-container {
    max-width: 400px;
    margin: 0 auto;
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sockjs-0.3.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/stomp.js"></script>

<script type="text/javascript">
    var stompClient = null;
    
    function setConnected(connected) {
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('response').innerHTML = '';
        if (connected) {
            showMessageOutput({ menu: "안녕하세요! 무엇이 궁금하신가요?\n\n[1] 서울소울 소개: 서울소울 프로젝트가 뭐야?\n[2] 서비스 안내: 핵심 서비스들을 설명해줘\n[3] 트러블슈팅: 서비스 이용 중에 문제가 생겼어" });
        }
    }
    
    function connect() {
        var socket = new SockJS('${pageContext.request.contextPath}/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            stompClient.subscribe('/topic/messages', function(messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body));
            });
        });
    }
    
    function sendMessage() {
        var from = "guest";
        var text = document.getElementById('text').value;
        if (text.trim() !== "") { // 입력 값이 비어있지 않은 경우에만 메시지 전송
            // 사용자 입력 메시지 출력
            var response = document.getElementById('response');
            var userMessage = document.createElement('div');
            userMessage.style.wordWrap = 'break-word';
            userMessage.className = 'message user';
            userMessage.innerHTML = text.replace(/\n/g, '<br>');
            response.appendChild(userMessage);
            
            // 서버로 메시지 전송
            stompClient.send("/app/chat", {}, JSON.stringify({ 'from': from, 'text': text }));
            document.getElementById('text').value = ''; // 입력 필드 초기화
            
            // 스크롤을 맨 아래로
            response.scrollTop = response.scrollHeight;
        }
    }
    
    function showMessageOutput(messageOutput) {
        var response = document.getElementById('response');
        var p = document.createElement('div');
        p.style.wordWrap = 'break-word';
        p.className = 'message chatbot';
        p.innerHTML = messageOutput.menu.replace(/\n/g, '<br>'); // 줄바꿈 처리
        response.appendChild(p);
        
        // 스크롤을 맨 아래로
        response.scrollTop = response.scrollHeight;
    }
    
    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }
    
    $(document).ready(function() {
        $('#sendMessage').click(function() {
            sendMessage();
        });

        $('#text').on('keydown', function(event) {
            if (event.key === 'Enter' && !event.shiftKey) {
                event.preventDefault();
                $('#sendMessage').click();
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
        <p>서울소울 서비스에 대한 자주 묻는 질문을 안내해드릴게요!</p>
        <p>챗봇이 대답할 수 없는 질문은 문의글 작성하기 페이지에서 질문해주세요.</p>
    </div>
    <div class="content2">            
        <!-- 대화창 -->
        <div class="message-container" id="response"></div>
    
    </div>
    
    <!-- 사용자 메시지 입력창 -->
    <div class="form-group">
        <div class="floating" id="conversationDiv">
            <table>
                <tr>
                    <td><textarea id="text" style="width: 320px; background: white; height: 50px;" placeholder="숫자만 입력해주세요"></textarea></td>
                    <td><button id="sendMessage" onclick="sendMessage();">전송</button></td>
                </tr>
            </table>
        </div>
    </div>
        
</body>
</html>
