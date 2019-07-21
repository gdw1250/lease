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
                        url: '/validatelogin',                      
                        message: '用户名或密码不正确'
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
        
        $('#registerform').bootstrapValidator({
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
                }
            }
        });
        
        //注册提交
        $('#registerbtn').click(function(){
             $.ajax({
                     cache: true,
                     type: "POST",
                     url:'/register',
                     data:$('#registerform').serialize(),// 你的formid//自动收集你的表单数据
                     error: function(request) {
                         alert("请求失败");
                     },
                     success: function(data) {
                           alert(data);
         				   window.location.reload();
                     }
            	});
            
        });
    
    });