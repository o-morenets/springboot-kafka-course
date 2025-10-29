package net.javaguides.springboot.payload;

import java.time.Instant;

public record User(int id, String firstName, String lastName, Instant createdAt) {
}
