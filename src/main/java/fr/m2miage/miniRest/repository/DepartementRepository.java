package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, String>
{
}