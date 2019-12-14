package pl.pas.pas.model.seats;

import java.util.UUID;

public class Seat {

    private UUID id;
    private String seatPosition;
    private boolean isOccupied;

    public Seat(String seatPosition) {
        this.id = UUID.randomUUID();
        this.seatPosition = seatPosition;
        this.isOccupied = false;
    }

    public UUID getId() {
        return id;
    }

    public String getSeatPosition() {
        return seatPosition;
    }

    public void setSeatPosition(String seatPosition) {
        this.seatPosition = seatPosition;
    }

    public void changeSeatStatus() {
        this.isOccupied = !this.isOccupied;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }
}
