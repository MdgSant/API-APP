package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.entity.AtividadePergunta;
import br.com.opala.EstudeX.entity.PerguntasOpcoes;
import br.com.opala.EstudeX.repository.AtividadePerguntaRepository;
import br.com.opala.EstudeX.repository.PerguntaOpcoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atividadesPergunta")
public class AtividadePerguntaController
{
    @Autowired
    private AtividadePerguntaRepository repository;

    @Autowired
    private PerguntaOpcoesRepository opcaoRepository;

    @GetMapping
    public List<AtividadePergunta> listar() { return repository.findAll(); }

    @PostMapping
    public AtividadePergunta cadastrar(@RequestBody AtividadePergunta atividadePergunta) {
        atividadePergunta.setIdPergunta(null);

        if (atividadePergunta.getAtividade() != null &&
                atividadePergunta.getAtividade().getIdAtividade() != null) {
            Atividade a = new Atividade();
            a.setIdAtividade(atividadePergunta.getAtividade().getIdAtividade());
            atividadePergunta.setAtividade(a);
        }

        List<PerguntasOpcoes> opcoes = atividadePergunta.getOpcoes();
        atividadePergunta.setOpcoes(null);

        AtividadePergunta perguntaSalva = repository.save(atividadePergunta);

        if (opcoes != null) {
            opcoes.forEach(o -> {
                o.setIdOpcao(null);
                o.setAtividadePergunta(perguntaSalva);
            });
            opcaoRepository.saveAll(opcoes);
            perguntaSalva.setOpcoes(opcoes);
        }

        return perguntaSalva;
    }
}