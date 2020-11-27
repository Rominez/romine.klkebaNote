$("code").dblclick(function(){
    navigator.clipboard.writeText($(this).text());
})
$("a.back").click(function(){
    window.history.back();
})