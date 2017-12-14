package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.CartePostale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartePostaleRepository extends JpaRepository<CartePostale, Integer>
{

    @Query("select distinct(cp.commune.nom) from CartePostale cp")
    List<String> findAllCommune();

    @Query("select cp.commune.nom from CartePostale cp where cp.commune.nom like concat(?1,'%')")
    List<String> findAllCommuneBy(String nom);
}