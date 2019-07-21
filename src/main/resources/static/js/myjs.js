$(document).ready(function(){

	var gid=null;
    //查看评论
	showcomment = function (username,goodid){
		gid=goodid;
		$('#commentdiv').load("/getComments",{username:username,goodid: goodid},function(){
			$('#comments').modal('show');
		});

	}
	//添加评论
	upcomment=function(username){
		
		var comment=$("#commenttext").val();
		if(comment==''){
			alert('请输入评论');
		}else{
			$('#commentdiv').load("/upComment",{goodid: gid,username:username,comment:comment},function(){
				alert('提交成功');
			});
		}
	}
	//订单信息
	GetDateNow = function (good,user) {
		var vNow = new Date();
		var sNow = "";
		sNow += String(vNow.getFullYear());
		sNow += String(vNow.getMonth() + 1);
		sNow += String(vNow.getDate());
		sNow += String(vNow.getHours());
		sNow += String(vNow.getMinutes());
		sNow += String(vNow.getSeconds());
		sNow += String(vNow.getMilliseconds());
		$("#out_trade_no").val(sNow) ;
		//添加订单名称
		$("#subject").val(good.goodname) ;
		//添加计费方式
		$("#depositMoney").val(good.price);
		//添加物品id
		$("#goodid").val(good.goodid);
		
		//添加用户
		$("#username").val(user.username);
		//添加当前时间
		$("#borrowtime").val(getDate());
		$("#deposit").val(good.deposit) ;
		$("#body").val(good.detail) ;
	}
	//表单验证
    $('#alipayfrom').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
           
        	borrowtime: {
                validators: {
             	   notEmpty: {
                        message: '日期不能为空'
                    },
             	   date : {
	   						format : 'YYYY-MM-DD HH:MM:SS',
	   						message : '请输入正确格式YYYY-MM-DD hh:mm:ss'
						}

                }
            }
        }
    });
	
	//获取当前时间
	function getNow(s) {
		return s < 10 ? '0' + s: s;
		}

	function getDate(){
		var myDate = new Date();             

		var year=myDate.getFullYear();        //获取当前年
		var month=myDate.getMonth()+1;   //获取当前月
		var date=myDate.getDate();            //获取当前日


		var h=myDate.getHours();              //获取当前小时数(0-23)
		var m=myDate.getMinutes();          //获取当前分钟数(0-59)
		var s=myDate.getSeconds();

		var now=year+'-'+getNow(month)+"-"+getNow(date)+" "+getNow(h)+':'+getNow(m)+":"+getNow(s);
		return now;
	}
	
	//提交订单
	$("#paybtn").click(function(){
		$("#alipayfrom").data('bootstrapValidator').validate();
		if($("#alipayfrom").data("bootstrapValidator").isValid()){
			$.ajax({
		         type: "post",
		         url: "/payByWallet",
		         data:$("#alipayfrom").serialize(),
		         dataType: "text",
		         success: function(data){
		        	$("#alipay").modal('hide');
		            alert(data);
		            $('#goodsList').load('/updataGoodsList',{sign:"uptime DESC"});
		         }
		     });
		}else{
			alert('请输入完善信息');
		}
	});
	//选择排序
	 $("#selectOrder li a").click(function(){
		 var sign='';
		 var selectval=$(this).html();
		 $("#dropdown").html($(this).html()+'  <span class="caret"></span>');
		 switch (selectval) {
	         case ("按时间排序"):
	             sign='uptime DESC';
	             break;
	         case ("按价格排序"):
	            sign='price ASC'
	             break;
	         case ("按人气排序"):
	        	 sign='commentnumber DESC'     
			 }
		 updataGoodsList(sign);
	 });
	 
	 //更新商品列表数据
	 function updataGoodsList(sign){
		 $('#goodsList').load('/updataGoodsList',{sign:sign});
	 }
	 
	 //查找商品数据
	 $('#selectGoods').change(function(){
		 $('#goodsList').load('/selectGoodsList',{selectStr:$(this).val()});
	 })
	 
	 //点赞
	 clickGood = function(commentid,username,goodid){
		 $('#commentdiv').load('/insertgood',{commentid:commentid,username:username,goodid:goodid});
	 }
	 
	 //查看回复选择显示
	 showReply = function(event,commentid){
		
		 var replyDiv=$(event).parents('.clearfix').next('.replyDiv');
		 var replyLi = replyDiv.find('.replyLi');
		 var i = $(event).find('i');
		 console.log(i);
		 replyLi.load("/selectReply",{commentid:commentid});
		 //显示
		 if(replyDiv.attr('class')=='replyDiv hide'){
			 replyDiv.removeClass('hide');
			 i.removeClass('glyphicon-menu-up');
			 i.addClass('glyphicon-menu-down');
		 }else{
			 i.removeClass('glyphicon-menu-down');
			 i.addClass('glyphicon-menu-up');
			 replyDiv.addClass('hide');
		 }
		 
	 }
	 
	 
	 //回复的回复
	 replyFromShow = function(event){
		 var clearfix = $(event).parents('.clearfix')[0];
		 var replyFrom=$(clearfix).next('.input-group');
		 var i = $(event).find('i');
		 console.log(replyFrom);
		 if(replyFrom.attr('class')=='input-group hide'){
			 i.removeClass('glyphicon-menu-up');
			 i.addClass('glyphicon-menu-down');
			 replyFrom.removeClass('hide');			
		 }else{	
			 i.removeClass('glyphicon-menu-down');
			 i.addClass('glyphicon-menu-up');
			 replyFrom.addClass('hide');
		 }
	 }
	
	 //添加回复
	 insertReply=function(event,commentid,username,replyUser){
		 var replyinput = $(event).parent('span').prev('input');
		 var replyLi = $(event).parents('.replyDiv').find('.replyLi');
		 replyLi.load("/insertReply",{username:username,commentid:commentid,replyUser:replyUser,replyText:replyinput.val()},function(){
			 alert('评论成功');
		 });
		 
	 }
	 
	 
	 // 回复选择
	 
	 
	 
	 
});