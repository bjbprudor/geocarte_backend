package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>
{
}