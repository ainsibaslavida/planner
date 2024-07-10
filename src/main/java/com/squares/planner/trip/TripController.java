package com.squares.planner.trip;

import com.squares.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload tripRequestPayload) {
        Trip newTrip = new Trip(tripRequestPayload);
        Trip createdTrip = this.tripRepository.save(newTrip);
        this.participantService.registerParticipantsToEvent(tripRequestPayload.emails_to_invite(), newTrip.getId());

        return new ResponseEntity<>(new TripCreateResponse(String.valueOf(createdTrip.getId())), HttpStatus.OK);
    }
}
