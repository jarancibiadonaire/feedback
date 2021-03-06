package cl.uchile.feedback;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.uchile.dcc.feedback.model.IndexSummaryVO;
import cl.uchile.dcc.feedback.model.SexVO;
import cl.uchile.dcc.feedback.model.UserVO;
import cl.uchile.dcc.feedback.services.StatisticsServiceRemote;
import cl.uchile.dcc.feedback.services.UserServiceRemote;
import cl.uchile.dcc.feedback.social.SignInUtils;

@Controller
public class IndexController {

	@Autowired
	UserServiceRemote userService;
	
	@Autowired
	StatisticsServiceRemote statService;

	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		IndexSummaryVO i=statService.getIndexSummary();
		model.addObject("indexSummary", i);
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			String success) {

		ModelAndView model = new ModelAndView();
		if (error != null)
			model.addObject("error",
					"Nombre de usuario y contraseña inválidos!");
		if (success != null)
			model.addObject("success", "Se ha registrado exitosamente.");
		model.setViewName("login");
		return model;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration(String error) {
		ModelAndView model = new ModelAndView("user", "command", new UserVO());
		Map<Integer, String> sexs = new LinkedHashMap<Integer, String>();
		List<SexVO> vos = userService.getSexs();
		sexs.put(0, " -- Elija sexo -- ");
		for (SexVO v : vos)
			sexs.put(v.getId(), v.getSex());
		model.addObject("sexs", sexs);
		if (error != null && error.compareTo("") != 0)
			model.addObject("error", error);
		model.setViewName("registration");
		return model;

	}

	@RequestMapping(value = "/check_registration", method = RequestMethod.POST)
	public ModelAndView checkRegistration(@ModelAttribute("user") UserVO user,
			BindingResult result) {
		if (user.getEmail() == null || user.getUserName() == null
				|| user.getPassword() == null || user.getRepassword() == null)
			return registration("Ingrese nombre de usuario, email y contraseña");
		if (user.getPassword().compareTo(user.getRepassword()) != 0)
			return registration("Contraseñas distintas. Vuelva a escribirlas");
		if (userService.findUserByEmail(user.getEmail()) != null)
			return registration("Email " + user.getEmail()
					+ " ya registrado. Pruebe con otro correo");
		if (userService.findUserByUserName(user.getUserName()) != null)
			return registration("Nombre de usuario " + user.getUserName()
					+ " ya registrado. Pruebe con otro nombre de usuario");
		userService.createUser(user);
		SignInUtils.signin(user.getUserName());
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/");
		return model;
	}
}
