package com.hemodoador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hemodoador.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
	Optional<Candidato> findByCpf(String cpf);
	
	@Query("""
		    select e.estado, count(c)
		    from Candidato c
		    join c.enderecos e
		    where e.estado is not null
		    group by e.estado
	""")
	List<Object[]> countCandidatosPorEstado();

}