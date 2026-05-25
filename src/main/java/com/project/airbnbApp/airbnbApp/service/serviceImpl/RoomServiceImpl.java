package com.project.airbnbApp.airbnbApp.service.serviceImpl;

import com.project.airbnbApp.airbnbApp.dto.RoomDto;
import com.project.airbnbApp.airbnbApp.entity.Hotel;
import com.project.airbnbApp.airbnbApp.entity.Room;
import com.project.airbnbApp.airbnbApp.entity.User;
import com.project.airbnbApp.airbnbApp.exception.ResourceNotFoundException;
import com.project.airbnbApp.airbnbApp.exception.UnAuthorisedException;
import com.project.airbnbApp.airbnbApp.repository.HotelRepository;
import com.project.airbnbApp.airbnbApp.repository.RoomRepository;
import com.project.airbnbApp.airbnbApp.service.InventoryService;
import com.project.airbnbApp.airbnbApp.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.airbnbApp.airbnbApp.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+ hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+hotelId);
        }

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()) {
            inventoryService.initializeRoomForAYear(room);
        }
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with Id : {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with id: "+hotelId);
        }

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long hotelId, Long roomId) {
        log.info("Getting the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new ResourceNotFoundException("Room " + roomId + " does not belong to hotel " + hotelId);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Transactional
    @Override
    public void deleteRoomById(Long hotelId, Long roomId) {
        log.info("Deleting the room with ID: {}", roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new ResourceNotFoundException("Room " + roomId + " does not belong to hotel " + hotelId);
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(room.getHotel().getOwner())) {
            throw new UnAuthorisedException("This user does not own this room with id: "+roomId);
        }

        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto) {
        log.info("Updating the room with ID: {}", roomId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        User user = getCurrentUser();
        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorisedException("This user does not own this hotel with ID: "+hotelId);
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));

        if (!room.getHotel().getId().equals(hotelId)) {
            throw new ResourceNotFoundException("Room " + roomId + " does not belong to hotel " + hotelId);
        }

        modelMapper.map(roomDto, room);
        room.setId(roomId);

//        TODO: if price or inventory is updated, then update the inventory for this room
        room = roomRepository.save(room);

        return modelMapper.map(room, RoomDto.class);
    }
}
