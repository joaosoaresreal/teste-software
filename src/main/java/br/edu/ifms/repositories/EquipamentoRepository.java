package br.edu.ifms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.entities.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

}
