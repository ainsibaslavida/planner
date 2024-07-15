package com.squares.planner.participant;

import com.squares.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public void registerParticipantsToEvent(List<String> participants, Trip trip) {
        List<Participant> result = participants.stream().map(email -> new Participant(email, trip)).toList();

        this.participantRepository.saveAll(result);

        System.out.println(result.get(0).getId());
    }

    public void triggerConfirmationEmailToParticipants(UUID tripId) {

    }
}
