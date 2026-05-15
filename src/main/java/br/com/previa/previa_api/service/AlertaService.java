package br.com.previa.previa_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.previa.previa_api.model.Alerta;
import br.com.previa.previa_api.repository.AlertaRepository;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository repository;

    public Alerta salvar(Alerta alerta) {
        return repository.save(alerta);
    }

    public List<Alerta> listarTodos() {
        return repository.findAll();
    }

    public Optional<Alerta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Alerta atualizar(Long id, Alerta alertaAtualizado) {

        Optional<Alerta> alertaExistente =
                repository.findById(id);

        if (alertaExistente.isPresent()) {

            Alerta alerta = alertaExistente.get();

            alerta.setTipo(alertaAtualizado.getTipo());
            alerta.setLocalizacao(alertaAtualizado.getLocalizacao());
            alerta.setNivelRisco(alertaAtualizado.getNivelRisco());
            alerta.setDescricao(alertaAtualizado.getDescricao());

            return repository.save(alerta);
        }

        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}