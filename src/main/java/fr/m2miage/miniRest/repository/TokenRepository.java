package fr.m2miage.miniRest.repository;

import fr.m2miage.miniRest.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer>
{

    Token findByToken(String token);

}