package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.CartePostale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartePostaleRepository extends JpaRepository<CartePostale, Integer>
{
}