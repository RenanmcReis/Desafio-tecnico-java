package desafio.java.sistema_gerenciamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

	@GetMapping("/Administrador")
	public String ola() {
		return "Administrador/home";
	}
}
