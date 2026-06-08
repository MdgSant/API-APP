package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Aluno;
import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.entity.AtividadeResposta;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import br.com.opala.EstudeX.repository.AtividadeRespostaRepository;
import br.com.opala.EstudeX.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atividadesrespostas")
public class AtividadeRespostaController
{
    @Autowired
    private AtividadeRespostaRepository repository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<AtividadeResposta> listar() { return repository.findAll(); }

    @GetMapping("/verificar/{idAluno}/{idAtividade}")
    public ResponseEntity<Boolean> verificarResposta(
            @PathVariable Integer idAluno,
            @PathVariable Integer idAtividade)
    {
        boolean jaRespondeu = repository
                .findByAlunoIdUtilizadorAndAtividadeIdAtividade(idAluno, idAtividade)
                .isPresent();
        return ResponseEntity.ok(jaRespondeu);
    }

    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<AtividadeResposta>> listarPorAluno(@PathVariable Integer idAluno)
    {
        return ResponseEntity.ok(repository.findByAlunoIdUtilizador(idAluno));
    }

    @PostMapping
    public AtividadeResposta cadastrar(@RequestBody AtividadeResposta atividadeResposta)
    {
        atividadeResposta.setIdAtividadeAluno(null);

        if (atividadeResposta.getAtividade() != null
                && atividadeResposta.getAtividade().getIdAtividade() != null)
        {
            Atividade atividade = atividadeRepository
                    .findById(atividadeResposta.getAtividade().getIdAtividade())
                    .orElseThrow();
            atividadeResposta.setAtividade(atividade);
        }

        if (atividadeResposta.getAluno() != null
                && atividadeResposta.getAluno().getIdUtilizador() != null)
        {
            Aluno aluno = alunoRepository
                    .findById(atividadeResposta.getAluno().getIdUtilizador())
                    .orElseThrow();
            atividadeResposta.setAluno(aluno);
        }

        return repository.save(atividadeResposta);
    }
}