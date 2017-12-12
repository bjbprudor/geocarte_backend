package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>
{
}