package com.squares.planner.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) throws Exception {
        Optional<Participant> requestedParticipant = this.participantRepository.findById(id);

        if (requestedParticipant.isEmpty()) {
            throw new Exception("The requested participant does not exist.");
        }

        Participant updateParticipant = requestedParticipant.get();

        updateParticipant.setIsConfirmed(true);
        updateParticipant.setName(payload.name());

        this.participantRepository.save(updateParticipant);

        return new ResponseEntity<>(updateParticipant, HttpStatus.ACCEPTED);
    }
}
