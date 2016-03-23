$('#addcomputer').validate({
	rules : {
		computerName : {
			required : true,
			minlength : "2",
		},
		introduced : {
			dateISO : true,
			notBefore70 : true
		},
		discontinued : {
			dateISO : true,
			notBefore70 : true,
			greaterThan : "#introduced"
		}
	},
	messages : {
		computerName : {
			required : "Name is required.",
			minlength : "At least two chars."
		},
		introduced : {
			dateISO : "Invalid date format.",
			notBefore70 : "Must be after 1970-01-02."
		},
		discontinued : {
			dateISO : "Invalid date format.",
			notBefore70 : "Must be after 1970-01-02.",
			greaterThan : "Discontinued date must be after introduced date."
		}
	},
	error : function(label) {
		$(this).addClass("error");
	}
});

jQuery.validator.addMethod("greaterThan", function(value, element, param) {
	if (value == null) {
		return true;
	}
	if (value == "") {
		return true;
	}
	if ($(param).val() == "") {
		return true;
	}
	return new Date(value) >= new Date($(param).val());
});

jQuery.validator.addMethod("notBefore70", function(value, element) {
	if (value == null) {
		return true;
	}
	if (value == "") {
		return true;
	}
	return new Date(value) >= new Date("1970-01-02");
});