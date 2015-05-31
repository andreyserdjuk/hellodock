package hellodock;

import javafx.beans.property.SimpleStringProperty;

public class DockerContainer {

    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty image = new SimpleStringProperty("");
    private final SimpleStringProperty command = new SimpleStringProperty("");
    private final SimpleStringProperty names = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");


    public DockerContainer() {
        this("", "", "", "", "");
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public DockerContainer(String id, String image, String command, String names, String status) {
        setId(id);
        setImage(image);
        setCommand(command);
        setNames(names);
        setStatus(status);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getCommand() {
        return command.get();
    }

    public SimpleStringProperty commandProperty() {
        return command;
    }

    public void setCommand(String command) {
        this.command.set(command);
    }

    public String getNames() {
        return names.get();
    }

    public SimpleStringProperty namesProperty() {
        return names;
    }

    public void setNames(String names) {
        this.names.set(names);
    }
}
