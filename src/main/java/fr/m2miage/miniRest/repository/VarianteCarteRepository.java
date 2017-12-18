package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.ListResourceBundle;

public interface VarianteCarteRepository extends JpaRepository<VarianteCarte, VarianteCarteId>
{

    @Query("select vc from VarianteCarte vc where vc.legende like concat(?1,'%')")
    List<VarianteCarte> findAllByLegendeLike(String legende);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.commune.insee = ?1")
    List<VarianteCarte> findAllByIdCartePostaleCommuneInsee(String insee);
    //List<VarianteCarte> findAllByIdCartePostaleCommuneInsee(String insee);

    @Query("select vc from VarianteCarte vc where vc.legende like concat(?1,'%') and vc.id.cartePostale.commune.insee = ?2")
    List<VarianteCarte> findAllByLegendeLikeAndIdCartePostaleCommuneInsee(String legende, String insee);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.editeur.id = ?1")
    List<VarianteCarte> findAllByIdCartePostaleEditeurId(Integer editeur);

    @Query("select vc from VarianteCarte vc where vc.legende like concat(?1,'%') and vc.id.cartePostale.editeur.id = ?2")
    List<VarianteCarte> findAllByLegendeLikeAndIdCartePostaleEditeurId(String legende, Integer editeur);

    @Query("select vc from VarianteCarte vc where vc.id.cartePostale.commune.insee=?1 and vc.id.cartePostale.editeur.id=?2")
    List<VarianteCarte> findAllByIdCartePostaleCommuneInseeAndAndIdCartePostaleEditeurId(String insee, Integer editeur);

    @Query("select vc from VarianteCarte vc where vc.legende like concat(?1,'%') and vc.id.cartePostale.commune.insee=?2 and vc.id.cartePostale.editeur.id=?3")
    List<VarianteCarte> findAllByLegendeLikeAndIdCartePostaleCommuneInseeAndIdCartePostaleEditeurId(String legende, String insee, Integer editeur);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonument(Integer typeMonument);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and vc.legende like concat(?2,'%')",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndLegende(Integer typeMonument, String legende);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and cp.commune_insee=?2",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndCommune(Integer typeMonument, String insee);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and cp.commune_insee=?2 and vc.legende like concat(?3,'%')",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndCommuneAndLegende(Integer typeMonument, String insee, String legende);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and cp.editeur_id=?2",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndEditeur(Integer typeMonument, Integer editeur);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and cp.editeur_id=?2 and vc.legende like concat(?3,'%')",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndEditeurAndLegende(Integer typeMonument, Integer editeur, String legende);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and cp.editeur_id=?2 and cp.commune_insee=?3",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndEditeurAndCommune(Integer typeMonument, Integer editeur, String insee);

    @Query(value = "SELECT * FROM variantecarte vc JOIN cartepostale cp ON vc.cartePostale_id = cp.id JOIN monumentcarte mc ON mc.cartePostale = cp.id JOIN monument m ON mc.monument = m.id WHERE m.type_id = ?1 and cp.editeur_id=?2 and cp.commune_insee=?3 and vc.legende like concat(?4,'%')",nativeQuery = true)
    List<VarianteCarte> findAllByTypeMonumentAndEditeurAndCommuneAndLegende(Integer typeMonument, Integer editeur, String insee, String legende);

    @Query(value = "SELECT * FROM variantecarte vc JOIN carteutilisateur cu ON cu.varianteCarte_cartePostale_id = vc.cartePostale_id AND cu.varianteCarte_id = vc.id WHERE cu.utilisateur_id=?1",nativeQuery = true)
    List<VarianteCarte> findAllByUsername(Integer id);

    @Query("select distinct(legende) from VarianteCarte vc")
    List<String> findAllLegendes();

    @Query("select distinct(legende) from VarianteCarte vc where vc.legende like concat(?1,'%')")
    List<String> findAllLegendesBy(String legende);

}