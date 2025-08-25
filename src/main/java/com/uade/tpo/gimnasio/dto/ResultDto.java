package com.uade.tpo.gimnasio.dto;

import java.util.Objects;

public class ResultDto {
    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResultDto resultDto = (ResultDto) o;
        return Objects.equals(status, resultDto.status) && Objects.equals(message, resultDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}
