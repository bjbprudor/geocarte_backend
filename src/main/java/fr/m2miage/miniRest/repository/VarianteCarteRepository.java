package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VarianteCarteRepository extends JpaRepository<VarianteCarte, VarianteCarteId>
{

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.commune.nom like %?1")
    List<VarianteCarte> findAllByCommuneName(String nom);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.monuments.type.libelle like %?1")
    List<VarianteCarte> findAllByTypeMonument(String nom);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.legende like %?1")
    List<VarianteCarte> findAllByLegende(String nom);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.editeur.nom like %?1")
    List<VarianteCarte> findAllByEditeurName(String nom);

    @Query("select vc from VarianteCarte vc where vc.carteUtilisateurs.id.utilisateur.email like %?1 or vc.carteUtilisateurs.id.utilisateur.nom like %?1")
    List<VarianteCarte> findAllByUsername(String nom);

}