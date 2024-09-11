
package desafio.java.sistema_gerenciamento.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.java.sistema_gerenciamento.models.Itens;

public interface ItensRepositorios extends JpaRepository<Itens, Long>{

	List<Itens> findByListaId(Long id);

}
