package br.com.opala.EstudeX.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import br.com.opala.EstudeX.dto.AtividadeResumoDTO;
import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.entity.Disciplina;
import br.com.opala.EstudeX.entity.NivelDificuldade;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import br.com.opala.EstudeX.repository.NivelDificuldadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atividades")
public class AtividadeController
{
    @Autowired
    private AtividadeRepository repository;

    @Autowired
    private NivelDificuldadeRepository nivelDificuldadeRepository;

    @GetMapping
    public Page<AtividadeResumoDTO> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idAtividade").descending());

        return repository.findAll(pageable)
                .map(a -> new AtividadeResumoDTO(
                        a.getIdAtividade(),
                        a.getTitulo(),
                        a.getDataCriacao(),
                        a.getPontuacaoMaxima(),
                        a.getNivelDificuldade(),
                        a.getDisciplina()
                ));
    }

    @GetMapping("/{id}")
    public Atividade buscarPorId(@PathVariable Integer id) {
        Atividade atividade = repository.findById(id).orElseThrow();
        // Força o carregamento das perguntas e opções
        atividade.getPerguntas().forEach(p -> p.getOpcoes().size());
        return atividade;
    }

    @PostMapping
    public ResponseEntity<Atividade> cadastrar(@RequestBody Atividade atividade) {
        try {
            atividade.setIdAtividade(null); // só zera o id para forçar INSERT

            if (atividade.getIdDisciplina() != null) {
                Disciplina d = new Disciplina();
                d.setIdDisciplina(atividade.getIdDisciplina());
                atividade.setDisciplina(d);
            }

            if (atividade.getIdNivelDificuldade() != null) {
                NivelDificuldade n = new NivelDificuldade();
                n.setIdNivelDificuldade(atividade.getIdNivelDificuldade());
                atividade.setNivelDificuldade(n);
            }

            return ResponseEntity.ok(repository.save(atividade));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}/pontuacao")
    public Atividade atualizarPontuacao(@PathVariable Integer id, @RequestParam Integer pontuacaoMaxima) {
        Atividade atividade = repository.findById(id).orElseThrow();
        atividade.setPontuacaoMaxima(pontuacaoMaxima);
        return repository.save(atividade);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id)
    {
        var atividade = repository.findById(id);

        if(atividade.isPresent())
        {
            repository.deleteById(id);
        }
    }
}
