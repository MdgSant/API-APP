package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Atividade;
import br.com.opala.EstudeX.entity.PerguntasOpcoes;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import br.com.opala.EstudeX.repository.PerguntaOpcoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perguntasopcoes")
public class PerguntaOpcoesController
{
    @Autowired
    private PerguntaOpcoesRepository repository;

    @GetMapping
    public List<PerguntasOpcoes> listar() {return repository.findAll();}

    @PostMapping
    public PerguntasOpcoes cadastrar(@RequestBody PerguntasOpcoes perguntasOpcoes)
    {
        return repository.save(perguntasOpcoes);
    }
}
