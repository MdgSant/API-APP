package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Aluno;
import br.com.opala.EstudeX.entity.Utilizador;
import br.com.opala.EstudeX.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alunos")
public class AlunoController
{
    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listar()
    {
        return repository.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Aluno> buscarPorId(@PathVariable("id") Integer id)
    {
        var aluno = repository.findById(id);
        if(aluno.isPresent())
            return aluno;
        return null;
    }

    @GetMapping("/serie/{idSerie}")
    public List<Aluno> listarPorSerie(@PathVariable Integer idSerie)
    {
        return repository.buscarPorSerie(idSerie);
    }

    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno)
    {
        return repository.save(aluno);
    }//falar com o ronqui

    @PutMapping("/{id}")
    public Aluno alterar(@RequestBody Aluno aluno, @PathVariable Integer id)
    {
        if(id.equals(aluno.getIdUtilizador()) && buscarPorId(id).isPresent())
        {
            return repository.save(aluno);
        }
        return null;
    }


}
