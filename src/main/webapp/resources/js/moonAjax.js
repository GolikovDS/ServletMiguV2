

function tick(){
    $.ajax({
        url : 'GetUserServlet',

        success : function(responseText) {
            $('#ajaxGetUserServletResponse').text(responseText);
        }
    });
    setTimeout(tick, 1500);
}
function tickImg(){
    $.ajax({
        url : 'GetUserServletImage',

        success : function(responseText) {
            $('#ajaxGetUserServletResponseImg').text(responseText);
        }
    });
    setTimeout(tickImg, 1500);
}
function tickErrEvent(){
    $.ajax({
        url : 'GetUserServletErrorAndEvent',

        success : function(responseText) {
            $('#ajaxGetUserServletResponseErrAndEvent').text(responseText);
        }
    });
    setTimeout(tickErrEvent, 1500);
}
