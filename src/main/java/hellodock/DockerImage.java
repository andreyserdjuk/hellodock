package hellodock;

import javafx.beans.property.SimpleStringProperty;

public class DockerImage {

    private final SimpleStringProperty repository = new SimpleStringProperty();
    private final SimpleStringProperty imageId = new SimpleStringProperty();
    private final SimpleStringProperty created = new SimpleStringProperty();
    private final SimpleStringProperty virtualSize = new SimpleStringProperty();


    public DockerImage() {
        this("","","","");
    }

    public DockerImage(String repository, String imageId, String created, String virtualSize) {
        setRepository(repository);
        setImageId(imageId);
        setCreated(created);
        setVirtualSize(virtualSize);
    }

    public String getRepository() {
        return repository.get();
    }

    public SimpleStringProperty repositoryProperty() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository.set(repository);
    }

    public String getImageId() {
        return imageId.get();
    }

    public SimpleStringProperty imageIdProperty() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId.set(imageId);
    }

    public String getCreated() {
        return created.get();
    }

    public SimpleStringProperty createdProperty() {
        return created;
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public String getVirtualSize() {
        return virtualSize.get();
    }

    public SimpleStringProperty virtualSizeProperty() {
        return virtualSize;
    }

    public void setVirtualSize(String virtualSize) {
        this.virtualSize.set(virtualSize);
    }
}
