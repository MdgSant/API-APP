package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.entity.NivelDificuldade;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import br.com.opala.EstudeX.repository.NivelDificuldadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Atividade> listar() {return repository.findAll();}

    @GetMapping("/{id}")
    public Atividade buscarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Atividade cadastrar(@RequestBody Atividade atividade)
    {
        System.out.println("idOrientador recebido: " + atividade.getIdOrientador());

        atividade.setIdAtividade(null);

        if (atividade.getNivelDificuldade() != null && atividade.getNivelDificuldade().getIdNivelDificuldade() != null)
        {
            NivelDificuldade nivel = nivelDificuldadeRepository
                    .findById(atividade.getNivelDificuldade().getIdNivelDificuldade())
                    .orElseThrow();
            atividade.setNivelDificuldade(nivel);
        }

        return repository.save(atividade);
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
