var SearchForm = function () {

    return {
        
        //Review Form
        initSearchForm: function () {
	        // Validation
	        $("#search-form").validate({
	            // Rules for form validation
	            rules:
	            {
	            	q:
	                {
	                    required: true
	                }
	            },
	                                
	            // Messages for form validation
	            messages:
	            {
	            	q:
		                {
		                    required: 'Por favor ingrese lo que desea buscar'
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