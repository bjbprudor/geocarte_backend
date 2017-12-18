package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.model.TypeMonument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeMonumentRepository extends JpaRepository<TypeMonument, Integer>
{

    @Query(value = "SELECT * FROM typemonument tm JOIN monument m ON m.type_id = tm.id JOIN monumentcarte mc ON mc.monument = m.id GROUP BY tm.id",nativeQuery = true)
    List<TypeMonument> findAllUsedTypeMonument();

    @Query(value = "SELECT * FROM typemonument tm JOIN monument m ON m.type_id = tm.id JOIN monumentcarte mc ON mc.monument = m.id GROUP BY tm.id HAVING tm.libelle like concat(?1,'%')",nativeQuery = true)
    List<TypeMonument> findAllUsedTypeMonumentByName(String name);

}