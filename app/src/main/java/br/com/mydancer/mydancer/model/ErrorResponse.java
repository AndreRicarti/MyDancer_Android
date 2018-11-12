package br.com.mydancer.mydancer.model;

public class ErrorResponse {
    private ErrorMessage error;

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }
}
