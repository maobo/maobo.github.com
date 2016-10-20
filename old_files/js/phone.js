$(function(){
$("#phone_number").focus(function(evt){
$(this).addClass("a");
if(this.value.length>0){
a(this);
d(this);
}
})
$("#phone_number").keyup(function(evt){
if(this.value.length==0){
e();
}else{
a(this);
}
d(this);
})
$("#phone_number").blur(function(evt){
$(this).removeClass("a");
e();
this.value=this.value
})
})
//计算div的left和top，显示div
function a(evt){
var y = 20;
y = $(evt).outerHeight();
$("#textMag").removeClass("fn-hide");
var t = $(evt).offset().top;
var l = $(evt).offset().left;
$("#textMag").css({
"top": (t+y-60) + "px",
"left":l + "px"
});
}

//隐藏div
function e(){
$("#textMag").addClass("fn-hide")
}

//控制div里显示的数字
function d(e){
var i = e.value;
i=$.trim(i);
var h=i.substring(0,3);
i=i.substring(3);
while(i&&i.length>0){
h+="-"+i.substring(0,4);
i=i.substring(4)
}
$("#mag-text").html(h);
}