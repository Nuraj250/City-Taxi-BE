package com.example.city_Taxi.util;

public class ResponseMessage {
        private int code;
        private String message;
        private Object object;

        public ResponseMessage(int code, String message, Object object) {
            this.code = code;
            this.message = message;
            this.object = object;
        }

    public void setCode(int code) {
            this.code = code;
        }

    public void setMessage(String message) {
            this.message = message;
        }

    public void setObject(Object object) {
            this.object = object;
        }

        @Override
        public String toString() {
            return "StandardResponse{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", object=" + object +
                    '}';    }
    }