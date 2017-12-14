package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VarianteCarteRepository extends JpaRepository<VarianteCarte, VarianteCarteId>
{

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.commune.nom like concat('%',?1,'%')")
    List<VarianteCarte> findAllByCommuneName(String nom);

    //List<VarianteCarte>findAllByLegendeLike(String legende);

    @Query("select vc from VarianteCarte vc where vc.legende like concat('%',?1,'%')")
    List<VarianteCarte> findAllByLegende(String nom);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.editeur.nom like concat('%',?1,'%')")
    List<VarianteCarte> findAllByEditeurName(String nom);

    //@Query("select  vc from VarianteCarte vc where vc.id.cartePostale.monuments.monument.type.nom like concat('%',?1,'%')")
    //List<VarianteCarte> findAllByTypeMonument(String nom);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id JOIN typemonument tm ON m.type_id = tm.id WHERE tm.libelle LIKE concat('%',?1,'%')",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonument(String nom);

    @Query(value = "SELECT * FROM variantecarte vc JOIN carteutilisateur cu ON cu.varianteCarte_cartePostale_id = vc.cartePostale_id AND cu.varianteCarte_id = vc.id WHERE cu.utilisateur_id=?1",nativeQuery = true)
    List<VarianteCarte> findAllByUsername(Integer id);

    @Query("select distinct(legende)from VarianteCarte vc")
    List<String> findAllLegendes();

    @Query("select legende from VarianteCarte vc where vc.legende like concat(?1,'%')")
    List<String> findAllLegendesBy(String legende);

}