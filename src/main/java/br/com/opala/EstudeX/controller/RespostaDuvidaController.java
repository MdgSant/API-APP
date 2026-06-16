package br.com.opala.EstudeX.controller;

import br.com.opala.EstudeX.entity.RespostaDuvida;
import br.com.opala.EstudeX.entity.Utilizador;
import br.com.opala.EstudeX.repository.RespostaDuvidaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/respostasDuvidas")
public class RespostaDuvidaController
{
    @Autowired
    private RespostaDuvidaRepository repository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public List<RespostaDuvida> listar()
    {
        return repository.findAll();
    }

    @GetMapping("/duvida/{idDuvida}")
    public List<RespostaDuvida> listarPorDuvida(@PathVariable Integer idDuvida) {
        return repository.findByIdDuvida(idDuvida);
    }

    @GetMapping("/verificar/{idDuvida}")
    public ResponseEntity<Boolean> verificarRespondida(@PathVariable Integer idDuvida)
    {
        return ResponseEntity.ok(repository.existeRespostaPorDuvida(idDuvida) > 0);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody RespostaDuvida respostaDuvida)
    {
        try {
            Integer idDuvida = null;
            if (respostaDuvida.getDuvida() != null)
                idDuvida = respostaDuvida.getDuvida().getIdDuvida();

            // Bloqueia resposta duplicada
            if (idDuvida != null && repository.existeRespostaPorDuvida(idDuvida) > 0)
                return ResponseEntity.badRequest().body("Dúvida já foi respondida.");

            Integer idUtilizador = null;
            if (respostaDuvida.getUtilizador() != null)
                idUtilizador = respostaDuvida.getUtilizador().getIdUtilizador();

            entityManager.createNativeQuery(
                            "INSERT INTO TBL_RESPOSTADUVIDA (idDuvida, ConteudoResposta, Momento, idUtilizador) " +
                                    "VALUES (?1, ?2, ?3, ?4)")
                    .setParameter(1, idDuvida)
                    .setParameter(2, respostaDuvida.getConteudoResposta())
                    .setParameter(3, respostaDuvida.getMomento())
                    .setParameter(4, idUtilizador)
                    .executeUpdate();

            // Atualiza status da dúvida para Respondida
            entityManager.createNativeQuery(
                            "UPDATE TBL_DUVIDA SET StatusDuvida = 'Respondida' WHERE idDuvida = ?1")
                    .setParameter(1, idDuvida)
                    .executeUpdate();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}