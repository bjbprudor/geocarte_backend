package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommuneRepository extends JpaRepository<Commune, String>
{

    @Query(value = "SELECT * FROM commune c JOIN cartepostale cp ON cp.commune_insee = c.insee GROUP BY c.insee",nativeQuery = true)
    List<Commune> findAllUsedCommunes();

    @Query(value = "SELECT * FROM commune c JOIN cartepostale cp ON cp.commune_insee = c.insee GROUP BY c.insee HAVING c.nom like concat(?1,'%')",nativeQuery = true)
    List<Commune> findAllUsedCommuneByName(String name);

    @Query(value = "SELECT * FROM commune c  WHERE c.nom like concat(?1,'%')",nativeQuery = true)
    List<Commune> findAllCommuneByName(String name);

}