package br.com.previa.previa_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.previa.previa_api.model.Alerta;
import br.com.previa.previa_api.service.AlertaService;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @PostMapping
    public Alerta criar(@RequestBody Alerta alerta) {
        return service.salvar(alerta);
    }

    @GetMapping
    public List<Alerta> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Alerta> buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Alerta atualizar(
            @PathVariable Long id,
            @RequestBody Alerta alerta) {

        return service.atualizar(id, alerta);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}