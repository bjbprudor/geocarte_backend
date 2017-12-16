package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.CarteUtilisateur;
import fr.m2miage.miniRest.model.CarteUtilisateurId;
import fr.m2miage.miniRest.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteUtilisateurRepository extends JpaRepository<CarteUtilisateur, CarteUtilisateurId>
{

    List<CarteUtilisateur> findAllById_Utilisateur_Id(Integer id);

}