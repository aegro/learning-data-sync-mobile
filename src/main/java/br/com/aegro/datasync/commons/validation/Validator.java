package br.com.aegro.datasync.commons.validation;

public interface Validator<T> {

    /**
     * For success cases, it should return normally.
     * For error cases, it must throw a {@link ValidationException}.
     */
    void validate(T candidate);

}
