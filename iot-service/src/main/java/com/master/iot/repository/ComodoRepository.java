package com.master.iot.repository;

import com.master.iot.model.Comodo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComodoRepository extends JpaRepository<Comodo, Long> {
}
