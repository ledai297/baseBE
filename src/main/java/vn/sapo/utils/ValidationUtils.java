package vn.sapo.utils;

import vn.sapo.exception.DomainValidateException;
import vn.sapo.exception.FormValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class ValidationUtils {
    public static <T> void validateObject(T entity) {
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errors.add(constraintViolation.getPropertyPath().toString() + ": " + constraintViolation.getMessage());
            }
            throw new DomainValidateException(errors.toArray(String[]::new));
        }
    }

    public static <T> Map<String, String> getValidationErrors(T entity) {
        HashMap<String, String> errors = new HashMap<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }

        return errors;
    }
}
