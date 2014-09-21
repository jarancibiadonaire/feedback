var RegistrationForm = function () {

    return {
        
        //Review Form
        initRegistrationForm: function () {
	        // Validation
	        $("#registration-form").validate({
	            // Rules for form validation
	            rules:
	            {
	            	firstName:
	                {
	                    required: true
	                },
	                lastName:
	                {
	                    required: true
	                },
	            	userName:
	                {
	                    required: true
	                },
	                email:
	                {
	                    required: true,
	                    email: true
	                },
	                password:
	                {
	                	 required: true,
		                 minlength: 3,
		                 maxlength: 20
	                },
	                repassword:
	                {
	                	required: true,
		                 minlength: 3,
		                 maxlength: 20
	                },
	                terms:{
	                	required:true,
	                }
	            },
	                                
	            // Messages for form validation
	            messages:
	            {
	            	firstName:
		                {
		                    required: 'Por favor ingrese su nombre'
		                }, 
	            	lastName:
		                {
		                    required: 'Por favor ingrese su apellido'
		                },
	            	userName:
	                {
	                    required: 'Por favor ingrese su nombre de usuario'
	                },
	                email:
	                {
	                    required: 'Por favor ingrese un mail',
	                    email: 'Por favor ingrese un mail v&aacute;lido'
	                },
	                password:
	                {
	                    required: 'Por favor ingrese una contrase&ntilde;a'
	                },
	                repassword:
	                {
	                    required: 'Por favor re ingrese la contrase&ntilde;a'
	                },
	                terms:{
	                	required: 'Acepte los t&eacute;rminos y condiciones'
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