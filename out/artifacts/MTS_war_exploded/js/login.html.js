/**
 * Created by rick- on 2017/6/30.
 */
$(document).ready(
    function () {
        var userName = getCookie("UserName");
        if (userName != null){
            $("#refunds").attr("href", "Refunds.html");
        }else {
            $("#refunds").click(function () {
                alert("请先登录！");
            })
        }

        $("#login").click(
            function () {
                $.post("http://127.0.0.1:8080/login",
                    {
                        UserTel:$("#UserTel").val(),
                        password:$("#password").val()
                    },
                    function (data, status) {
                        if (status == "success"){
                            var user = eval('('+ data +')');
                            document.cookie = "UserID = "+ user.UserID;
                            document.cookie = "UserName = "+ user.UserName;
                            document.cookie = "UserTel = "+ user.UserTel;
                            document.cookie = "UserLevel = "+ user.UserLevel;
                            document.cookie = "RestMoney = "+ user.RestMoney;
                            alert("登录成功！");
                            window.location.href = "index.html";
                        }
                    }
                )
            }
        )
        $("#search").click(function() {
            document.cookie = "searchName = "+ $("#mname").val();
            window.location.href = "chooseTicket.html";
        });
    }
)

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}