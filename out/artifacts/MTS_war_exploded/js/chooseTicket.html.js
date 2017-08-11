/**
 * Created by rick- on 2017/7/12.
 */
$(document).ready(
    function () {
        var ticketArray = null;
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

        $.post(
            "http://127.0.0.1:8080/getTicketUseName",
            {
                TicketName: getCookie("searchName"),
                Status: 1,
            },
            function (data, status) {
                if(status == "success"){
                    ticketArray = eval('('+data+')');
                    for(var i = 0; i<ticketArray.length; i++){
                        var ticket = "<tr> " +
                            "<td>"+ ticketArray[i].TicketName +"</td> " +
                            "<td>"+ ticketArray[i].Location +"</td> " +
                            "<td>"+ ticketArray[i].TicketTime +"</td> " +
                            "<td>"+ ticketArray[i].Director +"</td> " +
                            "<td>"+ ticketArray[i].Actor +"</td> " +
                            "<td>"+ ticketArray[i].Price +"</td> " +
                            "<td>"+ ticketArray[i].RestNum +"</td> " +
                            "</tr>";
                        $("tbody").append(ticket);
                    }
                }
            }
        )
        $("#table_content").click(function () {
            var td = event.srcElement;
            var TicketID = ticketArray[td.parentElement.rowIndex-1].TicketID;
            document.cookie = "chooseTicket = "+ TicketID;
            window.location.href = "Settlement.html";
        });

        $("#search").click(function () {
            document.cookie = "searchName = "+ $("#mname").val();
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
