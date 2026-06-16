package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.RespostaDuvida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RespostaDuvidaRepository extends JpaRepository<RespostaDuvida, Integer>
{
    @Query("SELECT r FROM RespostaDuvida r WHERE r.idDuvida = :idDuvida")
    List<RespostaDuvida> findByIdDuvida(@Param("idDuvida") Integer idDuvida);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM TBL_RESPOSTADUVIDA WHERE idDuvida = ?1", nativeQuery = true)
    int existeRespostaPorDuvida(Integer idDuvida);
}
