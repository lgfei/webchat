/**
 * 聊天室初始化
 */
var room;
var guestList;
var guest;
var socket;
var act = "init";
$(function () {
	// 打开聊天室窗口
    var modalChatroom = new ModalChatroom();
    modalChatroom.init();
    // 初始化房间和房客数据
    initData();
    // 绑定发送消息按钮事件
    var btnSendMsg = new BtnSendMsg();
    btnSendMsg.init();
    // 绑定关闭按钮事件
    var btnX = new BtnX();
    btnX.init();
});

/**
 * 聊天室弹出框
 */
var ModalChatroom = function(){
	var oModal = new Object();
	
	oModal.init = function(){
		$('#modalChatroom').modal({
	        keyboard: true
	    });
	}
	return oModal;
};

var initData = function(){
	// 查询房间信息
	var roomId = new webchat.util.page().getUrlParam("roomId");
	$.ajax({
		url: "../service/getroom",
		type: "GET",
		async: false,
		data: {"roomId":roomId},
		contentType: "application/x-www-form-urlencoded",
		success: function(resp){
			room = JSON.parse(resp);
			if(room){
				$("#spanRoomName").text(room.roomName);
			}
		}
	});
	// 查询本人信息
	var guestId = new webchat.util.page().getUrlParam("guestId");
	$.ajax({
		url: "../service/getguest",
		type: "GET",
		async: false,
		data: {"guestId":guestId},
		contentType: "application/x-www-form-urlencoded",
		success: function(resp){
			guest = JSON.parse(resp);
		}
	});
	// 加载房间的历史消息
	$.ajax({
		url: "../service/getmsglist",
		type: "POST",
		async: false,
		data: {"roomId":roomId},
		contentType: "application/x-www-form-urlencoded",
		success: function(resp){
			var msgs = JSON.parse(resp);
			if(msgs && msgs.length > 0){
				$.each(msgs,function(i,item){
					var msg = buildMsg(item);
					$("#tableMsg tbody").append(msg);
				});
			}
		}
	});
	// 打开socket连接
	socket = new WebSocket("ws://"+room.roomIp+":"+room.roomPort+"/webchat/chat?roomId="+roomId+"&guestId="+guestId);
	//接收服务器的消息
    socket.onmessage=function(resp){
    	if(resp.data == "CMD~ONLINE"){
    		refeshGuestList();
    	}else if(resp.data == "CMD~OFFLINE"){
    		refeshGuestList();
    	}else{
    		var msgObj = JSON.parse(resp.data);
    		var msg = buildMsg(msgObj);
			$("#tableMsg tbody").append(msg);
			act = "send";
    	}
    }
    // 关闭连接
    socket.onclose=function(resp){  
    	window.opener=null;
    	window.open('','_self');
    	window.close(); 
	};  
}

/**
 * 发送消息按钮
 */
var BtnSendMsg = function () {
    var oBtn = new Object();

    oBtn.init = function () {
        $("#btnSendMsg").click(function(event){
        	sendMsg();
        });
        $("#txtMsgContent").keydown(function(event) {    
            if (event.keyCode == 13) {    
            	sendMsg();  
            	event.returnValue = false;
            	return false;
            }    
        });
        /**
         * 发送消息
         */
        function sendMsg(){
        	var msgContent = $("#txtMsgContent").val();
        	if (msgContent != undefined && msgContent !='') {
        		var obj = JSON.stringify({
        			nickName: guest.guestName,
    	    		msgContent: msgContent
    	    	});
                // 发送消息
                socket.send(obj);
                // 发送完成后的相关处理
                $("#txtMsgContent").val('');
                $("#txtMsgContent").focus();
                act = "send";
            }
        }
        /**
         * 滚动到最底部
         */
        function gotoMsEnd(){
        	var scrollTop = 0;
        	if(act === "send"){
        		scrollTop = $('#tableMsg').height();
        	}else if(act === "scroll"){
        		scrollTop = $('#divMsg').scrollTop();
        	}else{
        		scrollTop = $('#divMsg')[0].scrollHeight;
        	}
        	$('#divMsg').scrollTop(scrollTop);
        }
        setInterval(() => {
        	gotoMsEnd();
		}, 500);
        // 手动调整滑动条
        $("#divMsg").scroll(function(event){
        	act = "scroll";
        });
    };

    return oBtn;
};

/**
 * 关闭按钮事件
 */
var BtnX = function(){
    var oBtn = new Object();

    oBtn.init = function () {
        $("#btnX").click(function(){
        	socket.close();
        });
    };

    return oBtn;	
}

/**
 * 刷新房客列表
 * @returns
 */
function refeshGuestList(){
	$("#ulGuestList").empty();
	$.ajax({
		url: "../service/getguestlist",
		type: "POST",
		data: {"roomId":room.roomId},
		contentType: "application/x-www-form-urlencoded",
		success: function(resp){
			guestList = JSON.parse(resp);
			if(guestList && guestList.length > 0){
				$.each(guestList,function(i,item){
					var li = [];
					if(item.guestStatus == 1){
						li.push('<li class="li-type-none">');
						li.push('<a href="#">');
						li.push('<span class="glyphicon glyphicon-user"></span>');
						li.push('</a>');
						li.push(item.guestName);
						li.push('</li>');
					}else{
						li.push('<li class="li-type-none">');
						li.push('<span class="glyphicon glyphicon-user"></span>');
						li.push(item.guestName);
						li.push('</li>');
					}
					$("#ulGuestList").append(li.join(" "));
				});
			}
		}
	});	
}

function buildMsg(msgObj){
	var tr = [];
	var msgHead = "";
	var msgBody = "";
    if(msgObj.guestId == guest.guestId){
    	tr.push('<tr><td class="algin-r">');
    	msgHead = new Date(msgObj.createTime).format('yyyy-MM-dd hh:mm:ss') + ":" + msgObj.nickName;
    	msgBody = '<div class="msg msg-send"><div class="algin-l">'+msgObj.msgContent+'</div><div class="arrow arrow-send"></div></div>';
	}else{
		tr.push('<tr><td class="algin-l">');
		msgHead = msgObj.nickName + ":" + new Date(msgObj.createTime).format('yyyy-MM-dd hh:mm:ss');
		msgBody = '<div class="msg msg-receive"><div class="arrow arrow-receive"></div>'+msgObj.msgContent+'</div></div>';
	}
    tr.push('<ul>');
    tr.push('<li class="li-type-none">');
    tr.push(msgHead);
    tr.push('</li>');
    tr.push('<li class="li-type-none">');
    tr.push(msgBody);
    tr.push('</li>');
    tr.push('</ul>');
    tr.push('</td></tr>');
    
    return tr.join(" ");
}
