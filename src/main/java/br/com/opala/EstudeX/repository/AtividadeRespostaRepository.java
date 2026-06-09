package br.com.opala.EstudeX.repository;

import br.com.opala.EstudeX.entity.AtividadeResposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AtividadeRespostaRepository extends JpaRepository<AtividadeResposta, Integer>
{
    Optional<AtividadeResposta> findByAlunoIdUtilizadorAndAtividadeIdAtividade(
            Integer idAluno, Integer idAtividade);

    List<AtividadeResposta> findByAlunoIdUtilizador(Integer idAluno);

    List<AtividadeResposta> findByAtividadeIdAtividade(Integer idAtividade);
}
