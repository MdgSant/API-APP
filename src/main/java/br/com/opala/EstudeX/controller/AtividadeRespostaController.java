package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.AtividadeResposta;
import br.com.opala.EstudeX.repository.AtividadeRepository;
import br.com.opala.EstudeX.repository.AtividadeRespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividadesrespostas")
public class AtividadeRespostaController
{
    @Autowired
    private AtividadeRespostaRepository repository;

    @GetMapping
    public List<AtividadeResposta> listar() {return repository.findAll();}

    @PostMapping
    public AtividadeResposta cadastrar(@RequestBody AtividadeResposta atividadeResposta)
    {
        return repository.save(atividadeResposta);
    }
}
