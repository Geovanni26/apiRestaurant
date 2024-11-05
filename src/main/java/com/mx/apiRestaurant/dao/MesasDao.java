package com.mx.apiRestaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.apiRestaurant.model.Mesas;

public interface MesasDao extends JpaRepository<Mesas, Long> {

}
