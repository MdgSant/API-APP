package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.NivelDificuldade;
import br.com.opala.EstudeX.repository.NivelDificuldadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/niveldificuldade")
public class NivelDificuldadeController
{
    @Autowired
    private NivelDificuldadeRepository repository;

    @GetMapping
    public List<NivelDificuldade> listar() {return repository.findAll();}

    @PostMapping
    public NivelDificuldade cadastrar(@RequestBody NivelDificuldade nivelDificuldade)//colocar dentro de atividade
    {
        return repository.save(nivelDificuldade);
    }
}
