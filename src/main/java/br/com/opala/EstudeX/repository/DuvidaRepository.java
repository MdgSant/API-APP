package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.Duvida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DuvidaRepository extends JpaRepository<Duvida, Integer>
{
    List<Duvida> findByUtilizadorIdUtilizador(Integer idUtilizador);
    boolean existsByIdDuvida(Integer idDuvida);
}