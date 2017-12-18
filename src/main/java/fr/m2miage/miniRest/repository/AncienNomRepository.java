package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.AncienNom;
import fr.m2miage.miniRest.model.AncienNomId;
import fr.m2miage.miniRest.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AncienNomRepository extends JpaRepository<AncienNom, AncienNomId>
{
    @Query("select max(id) as id from AncienNom an where an.id.commune like %?1")
    int findLastId(Commune commune);

    List<AncienNom> findAllByIdCommuneInsee(String insee);

}