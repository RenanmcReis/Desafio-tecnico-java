package desafio.java.sistema_gerenciamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import desafio.java.sistema_gerenciamento.models.Lista;
import desafio.java.sistema_gerenciamento.repositorios.ListaRepositorios;

@Controller
public class ListaController {
	
	@Autowired
	private ListaRepositorios listaRepositorios;
	
	@GetMapping("/cadastroLista")
	public ModelAndView cadastrar(Lista lista) {
		ModelAndView mv = new ModelAndView("cadastro/lista");
		mv.addObject("lista", lista);
		return mv;
	}
	
	@GetMapping("/listas")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cadastro/listas");
		mv.addObject("listas", listaRepositorios.findAll());
		return mv;
	}
	
	@GetMapping("/editarLista/{id}")
	public ModelAndView editarLista(@PathVariable("id") Long id) {
		Optional<Lista> lista = listaRepositorios.findById(id);
		return cadastrar(lista.get());
	}
	
	@PostMapping("/salvarLista")
	public ModelAndView salvar(Lista lista, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(lista);
		}
		listaRepositorios.saveAndFlush(lista);
		return listar(); // Redireciona para a listagem após salvar
	}
	
	@GetMapping("/excluirLista/{id}")
	public ModelAndView excluirLista(@PathVariable("id") Long id) {
		listaRepositorios.deleteById(id);
		return listar(); // Redireciona para a listagem após a exclusão
	}
	
    @GetMapping("/listastotal")
    public String listarListas(Model model) {
        List<Lista> listas = listaRepositorios.findAll();
        model.addAttribute("listas", listas);
        return "listas";
    }
}
