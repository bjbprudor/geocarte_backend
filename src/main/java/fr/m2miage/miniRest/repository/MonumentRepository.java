package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentRepository extends JpaRepository<Monument, Integer>
{
}