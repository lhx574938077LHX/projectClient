<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<script  type="text/javascript"src="ws://cdn.bootcss.com/sockjs-client/1.1.0/sockjs.min.js"></script>
 <body>

	<h2>Hello World!</h2>
     <form action="http://cbaofashop.cn/3g/91data.php" method="post" dir="ltr">
         <input name="userID" type="text"><br>
         <input value="查询" type="submit"><br>
     </form>
</body>
   <script>
       var websocket;
       if ('WebSocket' in window) {
           websocket = new WebSocket("ws://localhost:9080/jyzxclient_web/webSocketServer");
       } else if ('MozWebSocket' in window) {
           websocket = new MozWebSocket("ws://localhost:9080/jyzxclient_web/webSocketServer");
       } else {
           websocket = new SockJS("ws://localhost:9080/jyzxclient_web/sockjs/webSocketServer");
       }
       websocket.onopen = function (evnt) {
       };
       websocket.onmessage = function (evnt) {
           $("#msgcount").html("(<font color='red'>"+evnt.data+"</font>)")
       };
       websocket.onerror = function (evnt) {
       };
       websocket.onclose = function (evnt) {
       }

   </script>
</html>
