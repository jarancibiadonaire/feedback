var CommentForm = function () {

    return {
        
        //Review Form
        initCommentForm: function () {
	        // Validation
	        $(".comment-form").validate({
	            // Rules for form validation
	            rules:
	            {
	            	comment:
	                {
	                    required: true
	                }
	            },
	                                
	            // Messages for form validation
	            messages:
	            {
	            	comment:
		                {
		                    required: 'Por favor ingrese comentario'
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