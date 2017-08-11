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

        $.post(
            "http://127.0.0.1:8080/getmyticket",
            {
                UserID: getCookie("UserID")
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
                            "</tr>";
                        $("tbody").append(ticket);
                    }
                }
            }
        )

        $("#table_content").click(function () {
            var td = event.srcElement;
            var TicketID = ticketArray[td.parentElement.rowIndex-1].TicketID;
            $.post(
                "http://127.0.0.1:8080/refundsTicket",
                {
                    UserID: getCookie("UserID"),
                    TicketID: TicketID
                },
                function (data, status) {
                    if(status == "success"){
                        if (data == "true"){
                            alert("退票成功！")
                            window.location.href = "index.html";
                        }
                    }
                }
            )
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