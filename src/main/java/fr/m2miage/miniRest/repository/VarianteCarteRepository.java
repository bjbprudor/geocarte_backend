package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.CartePostale;
import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VarianteCarteRepository extends JpaRepository<VarianteCarte, VarianteCarteId>
{
    @Query("select max(id) as id from VarianteCarte v where v.id.cartePostale like %?1")
    int findLastId(CartePostale cartePostale);
}