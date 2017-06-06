(function (window) {
	
	function findAll(){
	    $.ajax({
            url:'./api/todos/hello',
            success:function(data){
                $('#onetodo').append(data);
            }
	    });
	}

	function save(){
		alert('ok!');
	}

	findAll();
	save();

})(window);


function create(){
	if(event.keyCode == 13){
        $.ajax({
            url:'./api/todos',
            dataType:'json',
            type:'post',
            data:$('form').serialize(),
            success:function(data){
                $('#onetodo').text(data);
            }
        })
    }
}

function active(){
	alert("active");
}

function completed(){
	alert("completed");
}