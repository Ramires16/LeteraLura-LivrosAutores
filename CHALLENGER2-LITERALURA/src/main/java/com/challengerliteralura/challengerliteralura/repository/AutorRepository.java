package com.challengerliteralura.challengerliteralura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challengerliteralura.challengerliteralura.entity.AutorEntity;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

    @Query("SELECT a FROM AutorEntity a WHERE :ano between a.dataFalescimento AND a.dataNascimento")
    List<AutorEntity> findForYear(int ano);

}
