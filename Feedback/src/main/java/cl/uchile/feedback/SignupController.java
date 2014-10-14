/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cl.uchile.feedback;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import cl.uchile.dcc.feedback.model.SexVO;
import cl.uchile.dcc.feedback.model.UserVO;
import cl.uchile.dcc.feedback.services.UserServiceRemote;
import cl.uchile.dcc.feedback.social.SignInUtils;

@Controller
public class SignupController {

	private final ProviderSignInUtils providerSignInUtils=new ProviderSignInUtils();
	
	@Autowired
	UserServiceRemote userService;

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {		
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if (connection != null) {
			request.setAttribute("message","Felicidades. Te has sincronizado con la cuenta de " + StringUtils.capitalize(connection.getKey().getProviderId()), WebRequest.SCOPE_REQUEST);
			request.setAttribute("sexs", getSexs(), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		if (form.getEmail() == null || form.getUsername() == null
				|| form.getPassword() == null || form.getRepassword() == null){
			request.setAttribute("error","Ingrese nombre de usuario, email y contraseña",WebRequest.SCOPE_REQUEST);
			request.setAttribute("sexs", getSexs(), WebRequest.SCOPE_REQUEST);
			return null;
		}
		if (form.getPassword().compareTo(form.getRepassword()) != 0){
			request.setAttribute("error","Contraseñas distintas. Vuelva a escribirlas",WebRequest.SCOPE_REQUEST);
			request.setAttribute("sexs", getSexs(), WebRequest.SCOPE_REQUEST);
			return null;
		}
		if (userService.findUserByEmail(form.getEmail()) != null){
			request.setAttribute("error","Email " + form.getEmail()
					+ " ya registrado. Pruebe con otro correo",WebRequest.SCOPE_REQUEST);
			request.setAttribute("sexs", getSexs(), WebRequest.SCOPE_REQUEST);
			return null;
		}
		if (userService.findUserByUserName(form.getUsername()) != null){
			request.setAttribute("error","Nombre de usuario " + form.getUsername()
					+ " ya registrado. Pruebe con otro nombre de usuario",WebRequest.SCOPE_REQUEST);
			request.setAttribute("sexs", getSexs(), WebRequest.SCOPE_REQUEST);
			return null;
		}
		UserVO u= SignupForm.userFromForm(form);
		userService.createUser(u);
		SignInUtils.signin(u.getUserName());
		providerSignInUtils.doPostSignUp(u.getUserName(), request);
		return "redirect:/";		
	}
	
	private Map<Integer, String> getSexs(){
		Map<Integer, String> sexs = new LinkedHashMap<Integer, String>();
		List<SexVO> vos = userService.getSexs();
		sexs.put(0, " -- Elija sexo -- ");
		for (SexVO v : vos)
			sexs.put(v.getId(), v.getSex());
		return sexs;
	}
}
