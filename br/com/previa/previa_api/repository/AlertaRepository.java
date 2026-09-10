package br.com.previa.previa_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.previa.previa_api.model.Alerta;

public interface AlertaRepository
        extends JpaRepository<Alerta, Long> {

}