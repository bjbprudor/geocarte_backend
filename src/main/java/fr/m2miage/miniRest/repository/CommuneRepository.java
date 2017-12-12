package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuneRepository extends JpaRepository<Commune, String>
{
}