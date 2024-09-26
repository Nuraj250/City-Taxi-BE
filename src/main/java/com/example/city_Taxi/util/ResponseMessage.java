package com.example.city_Taxi.util;

import com.example.city_Taxi.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
        private int code;
        private String message;
        private Object object;
        private Object token;

        public ResponseMessage(int code, String message, Object object) {
            this.code = code;
            this.message = message;
            this.object = object;
        }
    public ResponseMessage(int code, String message, Object object, Object object1) {
        this.code = code;
        this.message = message;
        this.object = object;
        this.token = object1;
    }

    public ResponseMessage() {

    }
        @Override
        public String toString() {
            return "StandardResponse{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", object=" + object +
                    '}';    }
    }