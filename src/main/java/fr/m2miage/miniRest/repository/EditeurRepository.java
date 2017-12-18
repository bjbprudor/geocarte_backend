package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EditeurRepository extends JpaRepository<Editeur, Integer>
{

    @Query(value = "SELECT * FROM editeur e JOIN cartepostale cp ON cp.editeur_id = e.id GROUP BY e.id",nativeQuery = true)
    List<Editeur> findAllUsedEditeurs();

    @Query(value = "SELECT * FROM editeur e JOIN cartepostale cp ON cp.editeur_id = e.id GROUP BY e.id HAVING e.nom like concat(?1,'%')",nativeQuery = true)
    List<Editeur> findAllUsedEditeursByName(String name);

}