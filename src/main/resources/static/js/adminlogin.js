$(document).ready(function(){
    //用户登录验证
	$('#loginform').bootstrapValidator({
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
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入密码'
                    },
                    remote: {
                        type: 'POST',
                        data: {
                            username:function(){ 
                            	return $('#username').val();
                            },
                            password:function(){
                            	return $('#password').val();
                            }                           
                        },
                        deay:2000,
                        url: '/validateAdminlogin',                      
                        message: '账号或密码错误，或者不是管理员账号'
                    }
                    
                }
            }
     
        }
    });
       
        
    //登录
//        $("#loginbtn").click(function(){
//            $.ajax({
//                type: "POST",
//                url:"/login",
//                data:$('#loginform').serialize(),
//                dataType:'text',
//                success:function(result){
//                    
//                    alert(result);
//                    if(result=="登录成功"){
//                        window.location.reload();
//                        }
//                }
//            });      
//        });
//    注册表单校验
            
    });