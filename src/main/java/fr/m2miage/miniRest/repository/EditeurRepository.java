package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurRepository extends JpaRepository<Editeur, Integer>
{
}