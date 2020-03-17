

var referrer = "";

referrer = document.referrer
if (!referrer) {
	try {
		if (window.opener) {                

			referrer = window.opener.location.href;
		}  
	} catch (e) {
	}
}


$(document).keyup(function(event){
	if(event.keyCode == 13){
		login();
	}
});


$(function () {
	loadStat();

	var phoneFlag = true;
    var loginPasswordFlag = true;
    var messageCodeFlag = true;

	$("#phone").on("blur",function () {
	    var phone = $.trim($("#phone").val());

        if (!phone) {
            $("#showId").html("请输入手机号码");
            phoneFlag = false;
        } else if (!/^1[1-9]\d{9}$/.test(phone)) {
            $("#showId").html("请输入正确的手机号码");
            phoneFlag = false;
        } else {
            $("#showId").html("");

            phoneFlag = true;
        }
    });


	$("#loginPassword").on("blur",function () {


	    var loginPassword = $.trim($("#loginPassword").val());

        if (!loginPassword) {


            $("#showId").html("请输入登录密码");
            loginPasswordFlag = false;
            return;
        } else {

            $("#showId").html("");
            loginPasswordFlag = true;
        }

    });


	$("#messageCode").on("blur",function () {
        var messageCode = $.trim($("#messageCode").val());

        if (!messageCode) {

            $("#showId").html("请输入短信验证码");

            messageCodeFlag = false;
        } else {

            $("#showId").html("");

            messageCodeFlag = true;
        }
    });

    $("#loginBtn").on("click",function () {

        var phone = $.trim($("#phone").val());

        var loginPassword = $.trim($("#loginPassword").val());

        var messageCode = $.trim($("#messageCode").val());

        $("#phone").blur();

        if (phoneFlag) {

            $("#loginPassword").blur();

            if (loginPasswordFlag) {

                $("#messageCode").blur();

                if (messageCodeFlag) {

                    $("#loginPassword").val($.md5(loginPassword));


                    $.ajax({

                        url:"loan/login",

                        type:"post",

                        data:{
                            "phone":phone,

                            "loginPassword":$.md5(loginPassword),

                            "messageCode":messageCode
                        },
                        success:function (data) {

                            if (data.code == "10000") {

                                window.location.href = referrer;

                            } else {

                                $("#loginPassword").val("");

                                $("#showId").html(data.message);
                            }
                        },

                        error:function () {

                            $("#loginPassword").val("");

                            $("#showId").html("系统异常");
                        }
                    });

                }
            }
        }
    });



});

function loadStat() {
	$.ajax({
		url:"loan/loadStat",
		type:"get",
		success:function (data) {
			$(".historyAverageYearRate").html(data.historyAverageYearRate);
			$("#allUserCount").html(data.allUserCount);
			$("#allBidMoney").html(data.allBidMoney);
		}
	});

}





















