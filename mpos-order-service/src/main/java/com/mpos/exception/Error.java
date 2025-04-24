package com.mpos.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Error {

    private LocalDateTime timestamp;
    private String message;
    private String description;

    public Error(LocalDateTime timestamp, String message, String description) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
