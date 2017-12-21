package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Departement;
import fr.m2miage.miniRest.model.TypeToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTokenRepository extends JpaRepository<TypeToken, Integer>
{
}