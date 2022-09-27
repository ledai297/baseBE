package vn.sapo.domain.abstractions.event.payloads;

import vn.sapo.domain.abstractions.event.payloads.BaseEventPayload;

public class FieldChangedPayload extends BaseEventPayload {
    private String reference;
    private String fieldName;
    private String oldValue;
    private String newValue;

    public FieldChangedPayload(){

    }

    public FieldChangedPayload(String fieldName, Object oldValue, Object newValue){
        this.fieldName = fieldName;
        if(oldValue != null)
            this.oldValue = oldValue.toString();
        if(newValue != null)
            this.newValue = newValue.toString();
    }

    public FieldChangedPayload(Object reference, String fieldName, Object oldValue, Object newValue) {
        this.fieldName = fieldName;
        if(oldValue != null)
            this.oldValue = oldValue.toString();
        if(newValue != null)
            this.newValue = newValue.toString();
        if (reference != null)
            this.reference = reference.toString();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
