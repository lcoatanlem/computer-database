$('#addcomputer').validate({
	rules : {
		computerName : {
			required : true,
			minlength : 2,
		},
		introduced : {
			dateISO : true
		},
		discontinued : {
			dateISO : true
		}
	},
	messages : {
		computerName : {
			required : "Name is required.",
			minlength : "At least two chars."
		},
		introduced : {
			dateISO : "Invalid date format."
		},
		discontinued : {
			dateISO : "Invalid date format."
		}
	},
	error : function(label) {
		$(this).addClass("error");
	}
});						
