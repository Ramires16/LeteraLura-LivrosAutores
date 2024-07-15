package com.challengerliteralura.challengerliteralura.repository;

import java.util.List;

import com.challengerliteralura.challengerliteralura.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    @Query("SELECT l FROM LivroEntity l WHERE l.linguagem >= :idioma")
    List<LivroEntity> findForLinguagem(String idioma);

}
