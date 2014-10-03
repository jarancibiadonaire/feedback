var FeedForm = function () {

    return {
        
        //Review Form
        initFeedForm: function () {
	        // Validation
	        $("#feed-form").validate({
	            // Rules for form validation
	            rules:
	            {
	            	title:
	                {
	                    required: true
	                }
	            },
	                                
	            // Messages for form validation
	            messages:
	            {
	            	title:
		                {
		                    required: 'Por favor ingrese t&iacutetulo'
		                }
	            },                  
	            
	            // Do not change code below
	            errorPlacement: function(error, element)
	            {
	                error.insertAfter(element.parent());
	            }
	        });
        }

    };

}();