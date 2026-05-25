package com.project.airbnbApp.airbnbApp.repository;

import com.project.airbnbApp.airbnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
