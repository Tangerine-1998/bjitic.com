
//同意实名认证协议
$(function() {

	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});

	var realNameFlag = true;
	var phoneFlag = true;
	var messageCodeFlag = true;
	var idCardFlag = true;
	var bankCardNoFlag = true;

$("#realName").on("blur",function () {

	var realName = $.trim($("#realName").val());

	if (!realName){


		$("#realNameErr").html("请输入真实姓名");

		realNameFlag = false;
	}

});

	$("#phone").on("blur",function () {
		var phone = $.trim($("#phone").val());

		if (!phone){

			$("#phoneErr").html("请输入手机号");
			phoneFlag = false;
		}
	});

	$("#messageCode").on("blur",function () {

		var messageCode = $.trim($("#messageCode").val());

		if (!messageCode){

			$("#messageCodeErr").html("请输入验证码");
			messageCodeFlag = false;
		}
	});

	$("#idCard").on("blur",function () {
		var idCard = $.trim($("#idCard").val());

		if (!idCard){

			$("#idCardErr").html("请输入身份证号");
			idCardFlag = false;
		}
	});

	$("#bankCardNo").on("blur",function () {
		var bankCardNo = $.trim($("#bankCardNo").val());

		if (!bankCardNo){

			$("#bankCardNoErr").html("请输入银行卡");

			bankCardNoFlag = false;
		}
	});



	$("#dateBtn1").on("click",function () {

		var phone = $.trim($("#phone").val());

		if (!$("#dateBtn1").hasClass("on")) {

			$("#realName").blur();

			if (realNameFlag) {

				$("#phone").blur();

				if (phoneFlag){

					$("#idCard").blur();

					if (idCardFlag){

						$("#bankCardNo").blur();

						if (bankCardNoFlag){

							$.ajax({
								url :"loan/phoneMessageCode",

								type:"get",

								data:"phone="+phone,

								success:function (data) {

									if (data.code == "10000") {

										alert("验证码" + data.datas);
										$.leftTime(60,function (d) {

											if (d.status) {

												$("#dateBtn1").addClass("on");

												$("#dateBtn1").html((d.s == "00"?"60":d.s) + "s重发");
											} else {

												$("#dateBtn1").removeClass("on");

												$("#dateBtn1").html("获取验证码");
											}
										});
									} else {
										$("#showId").html(data.message);
									}
								},
								error:function () {
									$("#showId").html("短信发送异常");

								}
							});
						}
					}
				}

			}
		}
	});

	$("#btnRegist").on("click",function () {

		var realName = $.trim($("#realName").val());
		var phone = $.trim($("#phone").val());
		var messageCode = $.trim($("#messageCode").val());
		var idCard = $.trim($("#idCard").val());
		var bankCardNo = $.trim($("#bankCardNo").val());

		$("#realName").blur();

		if (realNameFlag){

			$("#phone").blur();

			if (phoneFlag){

				$("#messageCode").blur();

				if (messageCodeFlag){

					$("#idCard").blur();

					if (idCardFlag){

						$("#bankCardNo").blur();

						if (bankCardNoFlag){

							$.ajax({

								url : "loan/attestation",
								type : "post",
								data : {

									"realName" : realName,
									"phone":phone,
									"messageCode":messageCode,
									"idCard":idCard,
									"bankCardNo":bankCardNo
								},

								success : function (data) {
									if (data.code == "10000"){

										$("#showId").html("实名认证通过")
									} else {
										$("#showId").html(data.message)
									}
								},
								error : function () {

									$("#showId").html("实名认证异常")
								}
							})

						}
					}
				}
			}
		}


	})

});

//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}

//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}


















