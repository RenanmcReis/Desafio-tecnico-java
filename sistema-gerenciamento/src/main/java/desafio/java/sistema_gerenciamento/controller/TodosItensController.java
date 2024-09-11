package desafio.java.sistema_gerenciamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import desafio.java.sistema_gerenciamento.models.Lista;
import desafio.java.sistema_gerenciamento.models.Itens;
import desafio.java.sistema_gerenciamento.repositorios.ListaRepositorios;
import desafio.java.sistema_gerenciamento.repositorios.ItensRepositorios;

import java.util.List;

@Controller
public class TodosItensController {

    @Autowired
    private ListaRepositorios listaRepositorios;

    @Autowired
    private ItensRepositorios itensRepositorios;

    @GetMapping("/itens/{id}")
    public String listarItens(@PathVariable Long id, Model model) {
        Lista lista = listaRepositorios.findById(id).orElse(null);

        if (lista == null) {
            model.addAttribute("errorMessage", "Lista não encontrada.");
            return "error";
        }

        List<Itens> itens = itensRepositorios.findByListaId(id);

        model.addAttribute("lista", lista);
        model.addAttribute("itens", itens);

        return "cadastro/todosItens"; // Nome do arquivo HTML na pasta cadastro
    }
    @GetMapping("/editarItem/{id}")
    public String editarItem(@PathVariable Long id, Model model) {
        Itens item = itensRepositorios.findById(id).orElse(null);
        if (item == null) {
            model.addAttribute("errorMessage", "Item não encontrado.");
            return "error";
        }
        model.addAttribute("item", item);
        model.addAttribute("listas", listaRepositorios.findAll());
        return "cadastro/editarItem";
    }
    
    @PostMapping("/salvarItem")
    public String salvarItem(Itens item, @RequestParam Long listaId, Model model) {
        if (item.getId() == null) {
            // Criar um novo item
            item.setLista(listaRepositorios.findById(listaId).orElse(null));
        }
        itensRepositorios.save(item);
        return "redirect:/itens/" + listaId; // Redireciona para a página de itens da lista
    }
    

    @GetMapping("/excluirItem/{id}")
    public String excluirItem(@PathVariable Long id, Model model) {
        Itens item = itensRepositorios.findById(id).orElse(null);
        if (item != null) {
            Long listaId = item.getLista().getId();
            itensRepositorios.delete(item);
            return "redirect:/itens/" + listaId; // Redireciona para a página de itens da lista
        }
        model.addAttribute("errorMessage", "Item não encontrado.");
        return "error";
    }
    
    
    
}
