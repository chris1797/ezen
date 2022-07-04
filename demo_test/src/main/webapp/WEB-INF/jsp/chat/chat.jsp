<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹소켓 테스트 페이지</title>
<script type="text/javascript">
var g_webSocket = null;
window.onload = function() 
{
	//on~는 시스템함수로, 꼭 써줘야 하는것이고, 그 안에 사용자핸들러는 개발자가 작성
	
    //host = "152.70.92.222";   /* 배포시에 호스트 주소로 변경 */
   host = "localhost";
    g_webSocket = new WebSocket("ws://"+host+"/websocket");
    
    
    /* 웹소켓 접속 성공시 실행 */
    g_webSocket.onopen = function(message) {
        addLineToChatBox("Server is connected.");
    };
    
    
    /* 웹소켓 서버로부터 메시지 수신시 실행 */
    g_webSocket.onmessage = function(message) {
    	
    	var obj = JSON.parse(message.data);
    	if(obj.uid) {
    		uid = obj.uid;
    	}
    	
        addLineToChatBox(message.data);
    };

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행 */
    g_webSocket.onclose = function(message) {
        addLineToChatBox("Server is disconnected.");
    };

    /* 웹소켓 에러 발생시 실행 */
    g_webSocket.onerror = function(message) {
        addLineToChatBox("Error!");
    };
}

/* ============================================================================================================= */

/* 채팅 메시지를 화면에 표시 */
function addLineToChatBox(_line) 
{
    if (_line == null) {
        _line = "";
    }
    
    var chatBoxArea = document.getElementById("chatBoxArea");
    chatBoxArea.value += _line + "\n";
    chatBoxArea.scrollTop = chatBoxArea.scrollHeight;    
    //스크롤바를 조정, 자동으로 ScrollHeight를 조정
}

/* ============================================================================================================= */

/* Send 버튼 클릭하면 서버로 메시지 전송 */
function sendButton_onclick() {
    var inputMsgBox = document.getElementById("inputMsgBox"); //제이쿼리로 한다면 $('#inputMsgBox')
    
    //box가 없거나, 값이 없을 때 false
    if (inputMsgBox == null || inputMsgBox.value == null || inputMsgBox.value.length == 0) {
        return false;
    }
    
    var jsStr = JSON.stringify(obj);
    var chatBoxArea = document.getElementById("chatBoxArea"); //채팅 메세지가 돌아가는 곳
    
    //소켓이 쓸 준비가 되지 않았으면, false
    if (g_webSocket == null || g_webSocket.readyState == 3) {
        chatBoxArea.value += "Server is disconnected.\n";
        return false;
    }
    
    // 서버로 메시지 전송
    // g_webSocket은 서버
    g_webSocket.send(inputMsgBox.value); // 서버로 값을 보내고
    inputMsgBox.value = ""; // 값을 다시 비우고
    inputMsgBox.focus(); // 다시 입력할 수 있도록 focus
    
    return true;
}

/* ============================================================================================================= */

/* Disconnect 버튼 클릭하는 경우 호출 */
function disconnectButton_onclick() {
	// Socket Object가 정상적으로 있다면 close
    if (g_webSocket != null) {
        g_webSocket.close();    
    }
}

/* ============================================================================================================= */

/* inputMsgBox 키 입력하는 경우 호출 */
// 입력하고 바로 Enter쳤을 때 send되는걸 가능하게 하는 function
function inputMsgBox_onkeypress() {
    if (event == null) {
        return false;
    }
    
    // 엔터키 누를 경우 서버로 메시지 전송
    var keyCode = event.keyCode || event.which; 
    //브라우저에 따라서 keyCode를 쓰는 브라우저도 있고, which를 쓰는 곳도 있기 때문에 둘 다 충족하기 위해
    // ex) Chrome이든 Edge든 간에 작동되도록
    if (keyCode == 13) { // 13이 Enter key
        sendButton_onclick();
    }
}

/* ============================================================================================================= */
</script>
</head>
<body>
    <input id="inputMsgBox" style="width: 250px;" type="text" onkeypress="inputMsgBox_onkeypress()">
    <input id="sendButton" value="Send" type="button" onclick="sendButton_onclick()">
    <input id="disconnectButton" value="Disconnect" type="button" onclick="disconnectButton_onclick()">
    <br/>
    <textarea id="chatBoxArea" style="width: 100%;" rows="10" cols="50" readonly="readonly"></textarea>
</body>
</html>