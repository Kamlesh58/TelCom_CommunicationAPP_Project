package com.telcom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telcom.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
