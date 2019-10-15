//var url='ws://'+window.location.host+'/webSocket/hello';
//var url='http://'+window.location.host+'/webSocket/hello';
var url='hello';
//var socket=new WebSocket(url);
var socket=new SockJS(url);
socket.onopen=function(){
	console.log('Opening');
	sayHello();
};
//处理消息
socket.onmessage=function(e){
	console.log('Received message:',e.data);
	setTimeout(function(){sayHello()},2000);
};
//处理关闭事件
socket.onclose=function(){
	console.log('Closing');
};
function sayHello(){
	console.log('Sending hello');
	//发送消息
	socket.send('Hello');
}