$(document).ready(function(){
    //用户登录验证
	$('#personal').bootstrapValidator({
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
                    },
                    regexp: {
                        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    }
                }
            }, 
            email: {
                validators: {
                    emailAddress: {
                        message: '输入正确的邮箱地址'
                    }
                }
            }
        }
    });
       
        
   
        //修改信息保存
        $('#savebtn').click(function(){
        	$("#personal").data('bootstrapValidator').validate();
        	if($("#personal").data("bootstrapValidator").isValid()){
        		$.ajax({
                     cache: true,
                     type: "POST",
                     url:'/updatauser',
                     data:$('#personal').serialize(),// 你的formid//自动收集你的表单数据
                     error: function(request) {
                         alert("请求失败");
                     },
                     success: function(data) {
                           alert(data);
         				$('#personalhead').load('/perrefurbish',{elementid:"personalhead",userid:$("#userid").val()});
                     }
            	});
        	}
        });
        
        //查询订单信息
      getborrows=function(username){
    	
    	   $("#historyborrow").load("/selectBorrow",{username:username});
       };
       
       //退还租借物品
       var depositMoney=0.0;
       returnborrow=function(borrow){
    	   depositMoney = borrow.depositMoney;
    	   $("#subject").val(borrow.subject);
    	   $("#out_trade_no").val(borrow.out_trade_no);
    	   $("#username").val(borrow.username);
    	   $("#borrowtime").val(borrow.borrowtime);
    	   $("#repey").val(getDate());
    	   $("#amount").val(borrow.amount);
    	   $("#deposit2").val(borrow.deposit);
    	   $("#depositMoney").val(borrow.depositMoney);
    	   $('#amount').val(compute());
    	   $('#returngoodid').val(borrow.goodid);
    	   $('#returnmodel').modal('show');
 
       }
       /**
        * 退还
        */
       $('#paybtn').click(function(){
    	   $.ajax({
             type: "POST",
             url:"/backborrow",
             data:$('#returnform').serialize(),
             success:function(data){         
                 alert(data);
                 $('#returnmodel').modal('hide');
                 getborrows($('#username').val());
                 $('#burse').load('/perrefurbish',{userid:$('#userid').val(),elementid:'usermoney'})
             }
    	   });
       });
       
       //退还验证
       $('#returnform').bootstrapValidator({
           message: 'This value is not valid',
           feedbackIcons: {
               valid: 'glyphicon glyphicon-ok',
               invalid: 'glyphicon glyphicon-remove',
               validating: 'glyphicon glyphicon-refresh'
           },
           fields: {
              
        	   repey: {
                   validators: {
                	   notEmpty: {
                           message: '日期不能为空'
                       },
                	   date : {
	   						format : 'YYYY-MM-DD HH:MM:SS',
	   						message : '请输入正确格式YYYY-MM-DD hh:mm:ss'
   						},
   						callback: {
   	                        message: '时间不能小于当前时间',
   	                        callback: function (value, validator, $field) {   	 
   	                            let start = new Date();
   	                            let end = new Date($("#repey").val());
   	 
   	                            if (start <= end) {
   	                                return true;
   	                            }
   	                            return false;
   	                        }

   						}

                   }
               }
           }
       });
//       $("#returnbtn").click(function(){
//    	  
////    	   var borrowid=$("#borrowid").val();
////    	   $.post("demo_ajax_gethint.asp",{suggest:txt},function(result){
////    		    
////    		  });
//       });
       //计算价格
       function compute(){
    	   let start = new Date($('#borrowtime').val()); 
    	   let end = new Date($('#repey').val());
    	   let time = end-start;
    	   let hour = time/(1000*3600);
    	   return (hour*depositMoney).toFixed(2);
       }
       
       $("#repey").change(function(){
    	   if($('#returnform').data('bootstrapValidator').isValid()){  
    		  $('#amount').val(compute());
    		  
    		} 
    	  
    	 });
       
       //获取当前时间
       function getNow(s) {
   		return s < 10 ? '0' + s: s;
   		}

	   	function getDate(){
	   		var nowdate = new Date();
	   		var myDate = new Date(nowdate.getTime()+(60*1000));   //加一分钟         
	   		    //加一分钟
	   		var year=myDate.getFullYear();        //获取当前年
	   		var month=myDate.getMonth()+1;   //获取当前月
	   		var date=myDate.getDate();            //获取当前日
	
	
	   		var h=myDate.getHours();              //获取当前小时数(0-23)
	   		var m=myDate.getMinutes();          //获取当前分钟数(0-59)
	   		var s=myDate.getSeconds();
	
	   		var now=year+'-'+getNow(month)+"-"+getNow(date)+" "+getNow(h)+':'+getNow(m)+":"+getNow(s);
	   		return now;
	   	}
	   	//计算时间差
	   		
	   	
	   	//图片裁剪上传
	   
	    
	   

	    function CropAvatar(){
	        this.$inputimg = $('#InputFile');
	        this.$cropimg=$('#TrimImage');
	        this.stat = false;
	        this.$preview = $('.preview');
	        this.imgBlod;
	        this.$Ratio;
	        this.$saveBtn = $('#saveimg');
	    }
	    CropAvatar.prototype = {
	        startCrop:function(Ratio){
	            this.$cropimg.cropper({
	                aspectRatio: this.$Ratio,
	                viewMode:1,
	                preview: this.$preview,
	                movable:true,
	                crop: function(data) {
	                    // Output the result data for cropping image.
	                }
	        });
	            this.stat = true;
	        },
	        stopCrop:function(){
	            this.$cropimg.cropper('destroy');
	            this.stat = false;
	        },
	        changeUrl:function(Ratio){

	            if(this.stat){
	                this.$cropimg.cropper('replace',URL.createObjectURL(this.$inputimg[0].files[0]),true );
	            }else{
	                this.$cropimg.attr('src',URL.createObjectURL(this.$inputimg[0].files[0]));
	                this.startCrop(Ratio);
	            }
	        },
	        setImgBlod:function(width,height){
	        	this.imgBlod=$('#TrimImage').cropper('getCroppedCanvas',{
		    		width: width,
		    		height: height
		    	});
	        },
	        getImgBlod:function(){
	        	return this.imgBlod;
	        },
	        showmodel:function($Ratio){
	        	this.$Ratio=$Ratio;
	        	$('#imgcrop').modal('show');
	        },
	        changeButtonAttr:function(btnListen){
	        	this.$saveBtn.attr('onClick',btnListen);
	        },
	        changePreviewAttr:function(width,height){
	        	this.$preview.css({'width':width,'height':height})
	        }
	    };

	    var createCrop = new CropAvatar();
	    
	    //上传头像
		 updataheadModalShow=function(){
			 //修改预览框大小
			 createCrop.changePreviewAttr(150,150);
			 //修改按钮绑定事件
			 createCrop.changeButtonAttr('updataHeadImg()');
			 createCrop.showmodel(1);
			 
		 }
		 
		 updataHeadImg= function(){
			 createCrop.setImgBlod(150,150);
	    	 createCrop.getImgBlod().toBlob(function (blob) {
		            var formData = new FormData();

		            formData.append('croppedImage', blob);//	  获取图片数据
		            formData.append('userid', $('#userid').val());
		            
		            $.ajax({
		              method: "POST",
		              url:"/updataHeadImg?time="+(new Date()).getTime(),
		              data: formData,
		              processData: false,
		              contentType: false,
		              success: function (data) {
		            	  var img= $('#personalhead').find('img');
		            	  var imgsrc =img.attr('src');
		            	  img.attr('src',imgsrc+'?123');
//		            	  console.log($('#personalhead').find('img').attr('src'));
		            	  window.location.reload();  
//		            	  $('#personalhead').load('/returnpage',{'elementid':'personalhead'});
		              }
		          
		            });
	    	 });    
//			 $('#headImgDiv').load();
		 }
	    
	    
	    //物品上传
		 showmodal=function(){
	    	//修改预览框大小
			 createCrop.changePreviewAttr(160,120 );
			 //修改按钮绑定事件
			 createCrop.changeButtonAttr('saveImg()');
	    	createCrop.showmodel(4/3);
	    	
	    };  
	    //图片地址更改重新Cropper
	    $('#InputFile').on('change',function(){
	         createCrop.changeUrl();
	        //  $('#TrimImage').attr('src',URL.createObjectURL($(this)[0].files[0]));

	    });
	    //modal隐藏触发
	    $('#imgcrop').on('hidden.bs.modal', function (e) {
	        $('#TrimImage').removeAttr('src');
	        createCrop.stopCrop();
	      })
	      
	      
	    
	      
//	    $("#saveimg").click(function(){
//	    	
//	    	$('#TrimImage').cropper('getCroppedCanvas',{
//	    		width: 200,
//	    		height: 150
//	    	}).toBlob(function (blob) {
//	            var formData = new FormData();
//	            formData.append('croppedImage', blob);
//	            $.ajax({
//	              method: "POST",
//	              url:"/saveimg",
//	              data: formData,
//	              processData: false,
//	              contentType: false,
//	              success: function () {
//	                console.log('Upload success');
//	              },
//	              error: function () {
//	                console.log('Upload error');
//	              }
//	            });
//	          });
//	    });
	      
	    //图片选择
	    saveImg=function(){
	    	createCrop.setImgBlod(200,150);
	    	 $("#cropimg").html(createCrop.getImgBlod());
	    	 $('#imgcrop').modal('hide');
	    };
	   	//图片裁剪上传
	    
	    
	 
	    
	    //物品上传
	    $("#upgoodsbtn").click(function(){
	    	//开启表单验证
	    	$("#upgoodsform").data('bootstrapValidator').validate();
	    	if( $("#cropimg").html()==''){
	    		alert('请选择图片');
	    	}else if(!$("#upgoodsform").data("bootstrapValidator").isValid()){
	    		alert('请填写完整信息');
	    	}else{
	    		 createCrop.getImgBlod().toBlob(function (blob) {
			            var formData = new FormData();

			            formData.append('croppedImage', blob);//	  获取图片数据
			            formData.append('username',$("#upgoodsusername").val());
			            formData.append('goodname',$("#upgoodsname").val());
			            formData.append('price',$("#upprice").val());
			            formData.append('goodsnumber',$("#upgoodsnumber").val());
			            formData.append('detail',$("#updetail").val());
			            formData.append('userid',$("#upgoodsuserid").val());
			            formData.append('deposit',$("#deposit").val());
			            
			            $.ajax({
			              method: "POST",
			              url:"/upgoods",
			              data: formData,
			              processData: false,
			              contentType: false,
			              success: function (data) {
			                alert(data);
			              }
			          
			            });
			  });
	    	}
	    
	    });
	   
	    
	    //获取已上架物品
	    getGoods= function(username){
	    	$("#historyGoods").load("/getUserGoods",{username:username});
	    }
	    //删除
	    deleteusergoods= function(goodid,username){
	    	if(confirm("确定要删除数据吗")){
	    		$("#historyGoods").load("/deleteUserGoods",{goodid:goodid,username:username});
	    	}else{
	    		
	    	}
	    	
	    } 
	    //修改
	    updatausergoods=function(username){
	    	$("#updatagoodsform").data('bootstrapValidator').validate();
	    	if($("#upgoodsform").data("bootstrapValidator").isValid()){
	    		if(confirm("修改信息需要重新审核，确认要修改？")){
    				$("#historyGoods").load("/updataUserGoods",$('#updatagoodsform').serialize(),function(){
		    			alert('修改成功');
		    			$("#updatagoodsmodal").modal('hide');
    				});
	    		}
	    	}else{
	    		alert('请输入正确信息');
	    	}
	    	
	    }
	    //showupdatemodal
	    showupdatemodal= function(goods){
	    	$('#updatagoodsform [name="goodname"]').val(goods.goodname);
	    	$('#updatagoodsform [name="goodsnumber"]').val(goods.goodsnumber);
	    	$('#updatagoodsform [name="price"]').val(goods.price);
	    	$('#updatagoodsform [name="detail"]').val(goods.detail);
	    	$('#updatagoodsform [name="goodid"]').val(goods.goodid);
	    	$('#updatagoodsform [name="deposit"]').val(goods.deposit);
	    	$("#updatagoodsmodal").modal('show');
	    }
	   
	    
	    
	    //物品上传修改表单验证
		$('#updatagoodsform,#upgoodsform').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	goodname: {
	                message: 'The username is not valid',
	                validators: {
	                    notEmpty: {
	                        message: '标题不能为空不能为空'
	                    },
	                    stringLength: {
	                        min: 3,
	                        max: 10,
	                        message: '输入由3-10位字符'
	                    }                  
	                }
	            },
	            price: {
	                message: 'The phone is not valid',
	                validators: {
	                    notEmpty: {
	                        message: '价格不能为空'
	                    },
	                   
	                    greaterThan: {
	                        value: 0,
	                        message:'输入正确的价格，价格不小于0'
	                    },
	                    lessThan: {
	                        value: 1000,
	                        message:'价格在0-1000'
	                    }
	                }
	            }, 
	            goodsnumber: {
	                message: 'The phone is not valid',
	                validators: {
	                	notEmpty: {
		                        message: '数量不能为空'
		                },
		                digits: {
	                        message: '请输入整数'
	                    }
	                    
	                }
	            }, 
	            deposit:{
	            	message: 'The phone is not valid',
	                validators: {
	                	notEmpty: {
		                        message: '数量不能为空'
		                },
		                greaterThan: {
	                        value: 0,
	                        message:'输入正确的押金，押金不小于0'
	                    },
	                    lessThan: {
	                        value: 1000,
	                        message:'押金在0-1000'
	                    }
	                    
	                }
	            },
	            detail: {
	                message: 'The detail is not valid',
	                validators: {
	                    
	                	 stringLength: {
		                      
		                        max: 50,
		                        message: '描述不多于50字'
		                    }     
	                }
	            }, 
	        }
	    });	
		//充值订单验证
		$('#chargeFrom').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	amount: {
	                message: 'The phone is not valid',
	                validators: {
	                    notEmpty: {
	                        message: '价格不能为空'
	                    },	                   
	                    greaterThan: {
	                        value: 0,
	                        message:'输入正确的价格，价格不小于0'
	                    }
	                    
	                }
	            }
	        }
	    });
		//充值ModalShow
		showChargeModal = function(){
			var vNow = new Date();
			var sNow = "";
			sNow += String(vNow.getFullYear());
			sNow += String(vNow.getMonth() + 1);
			sNow += String(vNow.getDate());
			sNow += String(vNow.getHours());
			sNow += String(vNow.getMinutes());
			sNow += String(vNow.getSeconds());
			sNow += String(vNow.getMilliseconds());
			$('#chargeFrom').find("input[name='billid']").val(sNow);
			$("#chargeFrom").data('bootstrapValidator').validate();
			$('#chargeModal').modal('show');
		}
		//充值
//		Charge=function(){
//			 $.ajax({
//	              method: "POST",
//	              url:"/charge",
//	              data: $('#chargeFrom').serialize(),
//	              success: function (data) {
//	                alert(data);
//	              }
//	          
//	            });
//		};
		
    
    });