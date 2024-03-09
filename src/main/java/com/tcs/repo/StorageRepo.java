package com.tcs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.domain.ImageData;

public interface StorageRepo extends JpaRepository<ImageData, Long> {

	
	//fetch based on fileName
	Optional<ImageData> findByName(String name);

}
