package com.project.airbnbApp.airbnbApp.repository;

import com.project.airbnbApp.airbnbApp.entity.Guest;
import com.project.airbnbApp.airbnbApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    List<Guest> findByUser(User user);

}
