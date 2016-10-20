<html>
<body>
<h2>  Hello World! </h2>

<div id ="show"></div>
</body>

<script src="js/jquery-3.1.1.min.js"></script>
<script>

    $.ajax({
        type: 'POST',
        url: 'mvc/getajax' ,
        data: {name:'mms',pwd:'123456'} ,
        success: function(data){
            console.log('---ok----'+data);
        } ,
        error:function(){

        }

    });
</script>


</html>
