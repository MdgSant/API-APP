package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Utilizador;
import br.com.opala.EstudeX.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utilizadores")
public class UtilizadorController
{
    @Autowired
    private UtilizadorRepository repository;

    @GetMapping
    public List<Utilizador> listar()
    {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Utilizador> buscarPorId(@PathVariable("id") Integer id)
    {
        var utilizador = repository.findById(id);
        if(utilizador.isPresent())
            return utilizador;
        return null;
    }

    @PostMapping
    public Utilizador cadastrar(@RequestBody Utilizador utilizador)
    {
        return repository.save(utilizador);
    }

    @PutMapping("/{id}")
    public Utilizador alterar(@RequestBody Utilizador utilizador, @PathVariable Integer id)
    {
        if(id.equals(utilizador.getIdUtilizador()) && buscarPorId(id).isPresent())
        {
            return repository.save(utilizador);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id)
    {
        var utilizador = buscarPorId(id);
        if(utilizador.isPresent())
        {
            repository.deleteById(id);
        }
    }
}
