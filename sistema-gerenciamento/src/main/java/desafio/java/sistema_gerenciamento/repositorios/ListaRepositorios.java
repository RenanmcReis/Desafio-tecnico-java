
package desafio.java.sistema_gerenciamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.java.sistema_gerenciamento.models.Lista;

public interface ListaRepositorios extends JpaRepository<Lista, Long> {

}