package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.CarteUtilisateur;
import fr.m2miage.miniRest.model.CarteUtilisateurId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarteUtilisateurRepository extends JpaRepository<CarteUtilisateur, CarteUtilisateurId>
{

    List<CarteUtilisateur> findAllById_Utilisateur_Id(Integer id);

    @Query(value = "SELECT cu.id.utilisateur.id FROM CarteUtilisateur cu WHERE cu.id.varianteCarte.id.cartePostale.id=?1")
    List<Integer> findUsersIdByCp(Integer carteId);


}