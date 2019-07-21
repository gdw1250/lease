$(document).ready(function(){
	
	getApply=function(){
		$('#goodsApply').load('/goodsApply');
	}
	//获取已审核物品
	getAleadyApply = function(){
		$('#goodsAlready').load('/goodsAlreadyList');
	}
	
	/* 审核不通过 */
	reject=function(goodid){
		var notify = prompt("审核不通过原因")
		if(notify){
			$.ajax({
				url:'/refuse',
				type:'post',
				data:{goodid:goodid,notify:notify},
				success:function(responseTxt){
					alert(responseTxt);
					$('#goodsApply').load('/goodsApply');
				}
			});
		}else{
			alert("已取消审核");
		};
	};
	
	
	
	//改变列表的符号
	$('#listA').click(function(){
		if($('#signList').attr('class')=='glyphicon glyphicon-chevron-up pull-right'){
			$('#signList').attr('class','glyphicon glyphicon-chevron-down pull-right');
		}else{
			$('#signList').attr('class','glyphicon glyphicon-chevron-up pull-right')
		}
	});
	
	
	/* 通过审核 */
	adopt = function(goodid){
		if(confirm("确定同意该商品上架？")){
//			$('#goodsApply').load('/agree',{goodid:goodid},function(responseTxt){
//				alert(responseTxt);
//			}
			
			$.ajax({
				url:'/agree',
				type:'post',
				data:{goodid:goodid},
				success:function(responseTxt){
					alert(responseTxt);
					$('#goodsApply').load('/goodsApply');
				}
			});
			
		}else{
			
		}
	}
	//表格操作
	window.operateEvents = {
         'click .RoleOfEdit': function (e, value, row, index) {
				$('#username').val(row.username);
				$('#sex').val(row.sex);
				$('#address').val(row.address);
				$('#email').val(row.email);
				$('#tel').val(row.tel);
				$('#power').val(row.power);
				$('#userid').val(row.userid);
                $('#userEditModel').modal('show');           
         }
	};
	//保存用户
	saveUser=function(){
		$.ajax({
			url:'/updataEdituser',
			type:'post',
			data:$('#userEditForm').serialize(),
			success:function(data){
				alert(data);
				 $('#userEditModel').modal('hide');      
				 $.get('/getUserList',function(json){
					 $('#UserInfoTable').bootstrapTable('load',json);
				 });
			}
		});
	}
	//添加用户
	addUserbtn = function(){
		$.ajax({
			url:'/adminAddUser',
			type:'post',
			data:$('#addUserForm').serialize(),
			success:function(data){
				alert(data);
				 $('#addUserModal').modal('hide');      
				 $.get('/getUserList',function(json){
					 $('#UserInfoTable').bootstrapTable('load',json);
				 });
			}
		});
	}
	$('#UserInfoTable').bootstrapTable({
		url : 'getUserList', // 请求后台的URL（*）			
		method : 'get', // 请求方式（*）
		toolbar : '#toolbar', // 工具按钮用哪个容器
		classes :'table table-condensed table-hover',//表格样式
		editable:true,
 		 
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	//	sortable : true, //用 是否启排序
	//	sortOrder : "asc", // 排序方式
		sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
		pagination : true, // 是否显示分页（*）
		pageNumber: 1,    //如果设置了分页，首页页码
		pageSize: 5,                       //每页的记录行数（*）
	    pageList: [5,10,15],        //可供选择的每页的行数（*）
	//	onlyInfoPagination:false, //设置为 true 只显示总数据数，而不显示分页
		showRefresh : true, // 是否显示刷新按钮
//		queryParamsType:'',
		clickToSelect : true, // 是否启用点击选中行
	//	uniqueId : "fileid", // 每一行的唯一标识，一般为主键列
	 	showToggle : true, // 是否显示详细视图和列表视图的切换按钮
	//	cardView : false, // 是否显示详细视图
	//	detailView : false, // 是否显示父子表
	 	showColumns: true,
		search:true,   //是否启用搜索框
	
		columns : [ {
			checkbox: true
		},{
			field : 'username',
			title : '用户名',
			 align: 'center',
             valign: 'middle'
		},
		{
			field : 'power',
			title : '权限',
			 align: 'center',
             valign: 'middle'
		},{
			field : 'sex',
			title : '性别',
			 align: 'center',
             valign: 'middle'
			 
		}, {
			field : 'email',
			title : '邮箱',
			 align: 'center',
             valign: 'middle'
		 
		},{
			field : 'tel',
			title : '电话',
			 align: 'center',
             valign: 'middle'
		 
		},{
			field : 'address',
			title : '地址',
			 align: 'center',
             valign: 'middle'
		 
		},{
	       field: 'operate',
	       title: '操作',
	       align: 'center',
	       events: 'operateEvents',
	       formatter: operateFormatter
	        }],
		silent : true, // 刷新事件必须设置
		checkboxHeader: true,
       
        theadClasses:'thead-light'
		 	 
	});
	
	function operateFormatter(value, row, index) {
            return [
               
				'<a class="RoleOfEdit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 编辑</a>'
            ].join('');
	}
	
	
//	var operateEvents = {
//		    'click #textbtn': function (e, value, row, index) {
//		    	alert('Ok');
//		    }
//	}
	

	
//用户表批量删除
	
	deleteRow=function($table){
		let rowdata=$table.bootstrapTable('getSelections');
//		 console.log($table.bootstrapTable('getSelections',function(row){
//			return  row; 
//		 }));
//		 console.log($table.bootstrapTable('getSelections'));
		 var useridArray =new Array();
		 for(var i=0;i<rowdata.length;i++){
			 useridArray[i] = rowdata[i].userid;
		 }
		 $.ajax({
			 url:"/deleteUsers",
			 type:"post",
			 data:{userid:useridArray},
			 success:function(data){
				 alert(data);
				 $.get('/getUserList',function(json){
					 $('#UserInfoTable').bootstrapTable('load',json);
				 });
				 
//				 $('#UserInfoTable').bootstrapTable('refresh);
			 }
		 });
	}
	
	//添加用户
	addUserModalShow=function(){
		$('#addUserModal').modal('show');
	}
	
	//添加用户表单验证
	$('#addUserForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 8,
                        message: '用户名由3-8位字符组成'
                    },
                    remote: {
                        type: 'POST',
                        url: '/validatename',
                        deay:2000,
                        message: '该名字已存在'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入密码'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能与用户名相同'
                    }
                }
            },
            password2: {
                validators: {
                    notEmpty: {
                        message: '请确认密码'
                    },
                    identical: {
                        field: 'password',
                        message: '两次输入密码不相同'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能与用户名相同'
                    }
                }
            },
            email: {
                validators: {
                    emailAddress: {
                        message: '输入正确的邮箱地址'
                    }
                }
            },
            tel: {
                message: 'The phone is not valid',
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    }
                }
            },
            power: {
                validators: {
                	regexp: {
                        regexp: /^[0-1]{1}$/,
                        message: '权限0为普通用户，1为管理员'
                    }
                }
            }
        }
    });
	
	//评论表格
	$('#CommentInfoTable').bootstrapTable({
		url : 'selectAllComment', // 请求后台的URL（*）			
		method : 'get', // 请求方式（*）
		toolbar : '#commentToolbar', // 工具按钮用哪个容器
		classes :'table table-condensed table-hover',//表格样式
		editable:true,
 		 
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	//	sortable : true, //用 是否启排序
	//	sortOrder : "asc", // 排序方式
		sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
		pagination : true, // 是否显示分页（*）
		pageNumber: 1,    //如果设置了分页，首页页码
		pageSize: 5,                       //每页的记录行数（*）
	    pageList: [5,10,15],        //可供选择的每页的行数（*）
	//	onlyInfoPagination:false, //设置为 true 只显示总数据数，而不显示分页
		showRefresh : true, // 是否显示刷新按钮
//		queryParamsType:'',
		clickToSelect : true, // 是否启用点击选中行
	//	uniqueId : "fileid", // 每一行的唯一标识，一般为主键列
	 	showToggle : true, // 是否显示详细视图和列表视图的切换按钮
	//	cardView : false, // 是否显示详细视图
	//	detailView : false, // 是否显示父子表
	 	showColumns: true,
		search:true,   //是否启用搜索框
	
		columns : [ {
			checkbox: true
		},{
			field : 'goodid',
			title : '评论物品ID',
			 align: 'center',
             valign: 'middle'
		},
		{
			field : 'username',
			title : '评论者',
			 align: 'center',
             valign: 'middle'
		},{
			field : 'comment',
			title : '评论内容',
			 align: 'center',
             valign: 'middle'
			 
		}, {
			field : 'commenttime',
			title : '评论时间',
			 align: 'center',
             valign: 'middle'
		 
		},{
			field : 'nicenumber',
			title : '获赞数',
			 align: 'center',
             valign: 'middle'
		 
		},{
			field : 'replyCounted',
			title : '回复数量',
			 align: 'center',
             valign: 'middle'
		 
		}],
		silent : true, // 刷新事件必须设置
		checkboxHeader: true,
       
        theadClasses:'thead-light'
		 	 
	});
	//查看评论信息
	getComments=function(){
		 $.get('/selectAllComment',function(json){
			 $('#CommentInfoTable').bootstrapTable('load',json);
		 });
	}
	
	//批量删除评论
	deleteCommentRow=function($table){
		let rowdata=$table.bootstrapTable('getSelections');
//		 console.log($table.bootstrapTable('getSelections',function(row){
//			return  row; 
//		 }));
//		 console.log($table.bootstrapTable('getSelections'));
		 var commentidArray =new Array();
		 for(var i=0;i<rowdata.length;i++){
			 commentidArray[i] = rowdata[i].commentid;
		 }
		 $.ajax({
			 url:"/deleteCommentMore",
			 type:"post",
			 data:{commentids:commentidArray},
			 success:function(data){
				 alert(data);
				 $.get('/selectAllComment',function(json){
					 $('#CommentInfoTable').bootstrapTable('load',json);
				 });
				 
//				 $('#UserInfoTable').bootstrapTable('refresh);
			 }
		 });
	}
})