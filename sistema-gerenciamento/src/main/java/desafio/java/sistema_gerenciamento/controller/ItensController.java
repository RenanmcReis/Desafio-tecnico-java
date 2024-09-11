package desafio.java.sistema_gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import desafio.java.sistema_gerenciamento.models.Itens;
import desafio.java.sistema_gerenciamento.repositorios.ItensRepositorios;
import desafio.java.sistema_gerenciamento.repositorios.ListaRepositorios;

@Controller
public class ItensController {

    @Autowired
    private ItensRepositorios itensRepositorios;

    @Autowired
    private ListaRepositorios listaRepositorios;

    @GetMapping("/cadastroItens")
    public ModelAndView cadastrar(Itens itens) {
        ModelAndView mv = new ModelAndView("cadastro/itens");
        mv.addObject("itens", itens);
        mv.addObject("listas", listaRepositorios.findAll()); // Adiciona listas disponíveis para seleção
        return mv;
    }

    @PostMapping("/salvarItens")
    public ModelAndView salvar(Itens itens, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(itens);
        }
        itensRepositorios.saveAndFlush(itens);
        return cadastrar(new Itens());
    }
}
