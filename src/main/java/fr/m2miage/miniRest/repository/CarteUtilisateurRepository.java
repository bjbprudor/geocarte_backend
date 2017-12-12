package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.CarteUtilisateur;
import fr.m2miage.miniRest.model.CarteUtilisateurId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteUtilisateurRepository extends JpaRepository<CarteUtilisateur, CarteUtilisateurId>
{
}