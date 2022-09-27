package vn.sapo.api.sapocore.model;

public abstract class SpBasePagingFilterResponse {
    private SpFilterMetadata metadata;

    public SpFilterMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(SpFilterMetadata metadata) {
        this.metadata = metadata;
    }
}
