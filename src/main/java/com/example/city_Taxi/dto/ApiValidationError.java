package com.example.city_Taxi.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class ApiValidationError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1781211130961571205L;
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiValidationError that = (ApiValidationError) o;
        return object.equals(that.object) && field.equals(that.field) && rejectedValue.equals(that.rejectedValue) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, field, rejectedValue, message);
    }
}
