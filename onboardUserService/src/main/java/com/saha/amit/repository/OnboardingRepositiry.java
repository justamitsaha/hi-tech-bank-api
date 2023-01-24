package com.saha.amit.repository;

import com.saha.amit.model.OnboardUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepositiry extends CrudRepository<OnboardUser,String> {
}
