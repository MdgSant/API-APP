package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.Aluno;
import br.com.opala.EstudeX.entity.Disciplina;
import br.com.opala.EstudeX.entity.Duvida;
import br.com.opala.EstudeX.entity.Utilizador;
import br.com.opala.EstudeX.repository.DisciplinaRepository;
import br.com.opala.EstudeX.repository.DuvidaRepository;
import br.com.opala.EstudeX.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/duvidas")
public class DuvidaController {

    @Autowired
    private DuvidaRepository repository;
    @Autowired
    private UtilizadorRepository utilizadorRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @GetMapping
    public ResponseEntity<List<Duvida>> listar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();                    // aparece no console do IntelliJ
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Duvida> buscarPorId(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Duvida> cadastrar(@RequestBody Duvida duvida)
    {
        // Busca o Utilizador real do banco pelo ID
        if (duvida.getUtilizador() != null && duvida.getUtilizador().getIdUtilizador() != null)
        {
            Utilizador u = utilizadorRepository
                    .findById(duvida.getUtilizador().getIdUtilizador())
                    .orElseThrow();
            duvida.setUtilizador(u);
        }

        // Busca a Disciplina real do banco pelo ID
        if (duvida.getDisciplina() != null && duvida.getDisciplina().getIdDisciplina() != null)
        {
            Disciplina d = disciplinaRepository
                    .findById(duvida.getDisciplina().getIdDisciplina())
                    .orElseThrow();
            duvida.setDisciplina(d);
        }

        duvida.setIdDuvida(null); // força INSERT
        return ResponseEntity.ok(repository.save(duvida));
    }

    @PutMapping("/{id}")
    public Duvida alterar(@RequestBody Duvida duvida, @PathVariable Integer id)
    {
        if(id.equals(duvida.getIdDuvida()) && repository.findById(id).isPresent())
        {
            return repository.save(duvida);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id)
    {
        var duvida = repository.findById(id);

        if(duvida.isPresent())
        {
            repository.deleteById(id);
        }
    }
}