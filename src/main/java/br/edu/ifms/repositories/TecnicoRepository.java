package br.edu.ifms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.entities.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
