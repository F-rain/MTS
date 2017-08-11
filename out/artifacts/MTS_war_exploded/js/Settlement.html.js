/**
 * Created by rick- on 2017/7/12.
 */
$(document).ready(
    function () {
        var userName = getCookie("UserName");
        if (userName != null){
            $("#username").text(userName);
            $("#username").attr("href", "UserMessage.html");
        }

        if (userName != null){
            $("#refunds").attr("href", "Refunds.html");
        }else {
            $("#refunds").click(function () {
                alert("请先登录！");
            })
        }

        var ticketID = getCookie("chooseTicket");
        $.post(
            "http://127.0.0.1:8080/getTicket",
            {
                TicketID: ticketID,
            },
            function (data, status) {
                if(status == "success"){
                    var ticket = eval('('+data+')');
                    $("#Name_content").text(ticket.TicketName);
                    $("#location_content").text(ticket.Location);
                    $("#time_content").text(ticket.TicketTime);
                    $("#director").text(ticket.Director);
                    $("#actor").text(ticket.Actor);
                    $("#price").text(ticket.Price);
                }
            }
        )
        
        $("#buy_btn").click(function () {
            if (getCookie("UserID") != null){
                $.post(
                    "http://127.0.0.1:8080/addOrder",
                    {
                        UserID: getCookie("UserID"),
                        TicketID: ticketID,
                        num: $("#num").val()
                    },
                    function (data, status) {
                        if (status == "success"){
                            alert("订票成功");
                            window.location.href = "MyTicket.html"
                        }
                    }
                )
            }else {
                alert("请先登录！");
                window.location.href = "login.html";
            }
        })

        $("#search").click(function() {
            document.cookie = "searchName = "+ $("#mname").val();
            window.location.href = "chooseTicket.html";
        });
    }
);

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

