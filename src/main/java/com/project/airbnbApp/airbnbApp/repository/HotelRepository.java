package com.project.airbnbApp.airbnbApp.repository;

import com.project.airbnbApp.airbnbApp.entity.Hotel;
import com.project.airbnbApp.airbnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByOwner(User user);

}
