/**
 * index页面初始化
 */
$(function () {
    //初始化Table
    var tableRoom = new TableRoom();
    tableRoom.init();

    //查询按钮点击事件
    var btnQuery = new BtnQuery();
    btnQuery.init();
    
    //创建聊天室点击事件
    var btnCreateRoom = new BtnCreateRoom();
    btnCreateRoom.init();
    
    // 绑定进入房间按钮事件
    $(document).on("click",".btn-enter-room",function(e){
        var roomId = $(this).attr("roomid");
        var data = {};
        data["roomId"] = roomId;
        $.ajax({
            type: "POST",
            url: "../service/enterroom",
            data: {"data":JSON.stringify(data)},
            contentType: "application/x-www-form-urlencoded",
            success: function(resp){
                var respObj = JSON.parse(resp);
                var act = respObj.flag;
                if(act == "open"){
                    // 直接打开房间
                    var guestId = respObj.guest.guestId;
                    if(guestId){
                        window.open("../page/chatroom?roomId="+roomId+"&guestId="+guestId);
                    }
                }else{
                    // 打开输入昵称弹出框
                    var modalInputNickname = new ModalInputNickname();
                    modalInputNickname.init();
                    // 绑定打开聊天室按钮事件
                    var btnOpenChatRoom = new BtnOpenChatRoom(roomId);
                    btnOpenChatRoom.init();
                }
            }
        });
    }); 
});

/**
 * 房间列表表格
 */
var TableRoom = function () {
    var oTable = new Object();
    //初始化Table
    oTable.init = function () {
        $('#tableRoom').bootstrapTable({
            url: '../service/getroompagelist',
            method: 'POST',
            contentType: "application/x-www-form-urlencoded",
            toolbar: '#toolbar',
            striped: true,
            cache: false,
            pagination: true,
            queryParams: oTable.queryParams,
            sidePagination: "server",
            pageNumber:1,
            pageSize: 5,
            pageList: [5, 10, 50, 100],
            showColumns: true,
            showRefresh: true,
            cardView: false, 
            columns: [{
                field: 'roomId',
                title: '房间ID',
                visible: false,
            }, {
                field: 'roomName',
                title: '房间名'
            }, {
                field: 'roomLimit',
                title: '可容纳人数'
            }, {
                field: 'roomGuestNum',
                title: '已加入的人数'
            }, {
                field: 'roomOnlineNum',
                title: '在线人数'
            }, {
                field: 'roomDesc',
                title: '描述'
            }, {
                field: 'createTime',
                title: '创建时间',
                formatter : function(value,row,index){
                    if(value){
                        return  new Date(value).format('yyyy-MM-dd hh:mm:ss');
                    }
                }
            }, {
                field: 'operate',
                title: '操作',
                formatter : function(value,row,index){
                    var roomId = row.roomId;
                    return '<a href="#" class="btn-enter-room" roomid="'+roomId+'"><span class="glyphicon glyphicon-share-alt">进入房间</span></a>';
                }
            }]
        });        
    };

    //得到查询的参数
    oTable.queryParams = function (params) {
        var paramObj = {
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            roomId: $("#inputSearchRoomId").val(),
            roomName: $("#inputSearchRoomName").val()
        };
        return paramObj;
    };
    return oTable;
};

/**
 * 查询房间按钮
 */
var BtnQuery = function () {
    var oBtn = new Object();

    oBtn.init = function () {
        $("#btnQuery").bind("click",function(e){
            $('#tableRoom').bootstrapTable('refresh');
        });
    };

    return oBtn;
};

/**
 * 创建聊天室按钮
 */
var BtnCreateRoom = function(){
    var oBtn = new Object();
    
    oBtn.init = function(){
        $("#btnCreateRoom").bind("click",function(e){
            // 打开创建聊天室的弹出框
            var modalCreateRoom = new ModalCreateRoom();
            modalCreateRoom.init();
            // 绑定保存按钮事件
            var btnSaveRoom = new BtnSaveRoom();
            btnSaveRoom.init();
        });
    }
    return oBtn;
};

/**
 * 创建聊天室弹出框
 */
var ModalCreateRoom = function(){
    var oModal = new Object();
    
    oModal.init = function(){
        // 加载端口数据
        $.ajax({
            url: "../service/getports",
            type: "GET",
            contentType: "application/json",
            success:function(resp) {
                // 先清理旧数据
                $("#selectRoomPort").html("");
                var firstOption = $('<option selected="selected" value="-1">请选择端口号</option>');
                firstOption.appendTo($("#selectRoomPort"));
                // 重新渲染
                var ports = JSON.parse(resp);
                if(ports && ports.length > 0){
                    $.each(ports,function(i,item) {
                        var optionEle = $("<option></option>").append(item).attr("value",item);
                        optionEle.appendTo($("#selectRoomPort"));
                    });
                    // 打开弹框
                    $('#modalCreateRoom').modal({
                        keyboard: true
                    });
                }else{
                    bootbox.alert("房间资源已经耗尽");
                }
            }

    });
    }
    return oModal;
}

/**
 * 保存房间按钮
 */
var BtnSaveRoom = function(){
    var oBtn = new Object();
    
    oBtn.init = function(){
        $("#btnSaveRoom").bind("click",function(e){
            var data = new webchat.util.form().getParam("formCreateRoom");
            $.ajax({
                type: "POST",
                url: "../service/createroom",
                data: {"data":JSON.stringify(data)},
                contentType: "application/x-www-form-urlencoded",
                success: function(resp){
                }
            });
        });
    }
    return oBtn;
};

/**
 * 输入昵称弹出框
 */
var ModalInputNickname = function(){
    var oModal = new Object();
    
    oModal.init = function(){
        $('#modalInputNickname').modal({
            keyboard: true
        });
    }
    return oModal;
}

/**
 * 房客打开房间按钮
 */
var BtnOpenChatRoom = function(roomId){
    var oBtn = new Object();
    oBtn.roomId = roomId;
    
    oBtn.init = function(){
        $("#btnOpenChatRoom").bind("click",function(e){
            var data = new webchat.util.form().getParam("formInputNickname");
            data["roomId"] = oBtn.roomId;
            $.ajax({
                type: "POST",
                url: "../service/enterroom",
                data: {"data":JSON.stringify(data)},
                contentType: "application/x-www-form-urlencoded",
                success: function(resp){
                    var respObj = JSON.parse(resp);
                    if(respObj.guest && respObj.guest.guestId){
                        window.open("../page/chatroom?roomId="+roomId+"&guestId="+respObj.guest.guestId);
                    }else{
                        alert("错误码=" + respObj.resultCode);
                    }
                }
            });
        });
    }
    return oBtn;
};