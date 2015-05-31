package hellodock;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import javax.ws.rs.ProcessingException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Controller implements Initializable {
    DockerClient dockerClient;

    @FXML private PasswordField rootPass;

    @FXML private Text rootPassMsg;

    @FXML
    private TableView<DockerContainer> containersTable;

    @FXML
    private TableView<DockerImage> imagesTable;

    final ContextMenu contextMenu = new ContextMenu();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dockerClient = DockerClientBuilder.getInstance("unix:///var/run/docker.sock").build();
        } catch (Exception e) {
            new ExpandableDialog(Alert.AlertType.ERROR, "error loadDockerContainers", e.getMessage()).showAndWait();
        }

//        MenuItem menuItem = new MenuItem("Start container");
//
//        menuItem.setOnAction(event -> {
//            System.out.println(event.getSource());
//        });
//
//        contextMenu.setOnShowing(event -> System.out.println(event.getTarget()));
//        contextMenu.setOnShown(event -> System.out.println("menu is shown"));
//        contextMenu.getItems().addAll(menuItem);
//        containersTable.setContextMenu(contextMenu);
    }

    @FXML
    public void loadDockerContainers() {
        ObservableList<DockerContainer> items = containersTable.getItems();
        try {
            items.setAll(getDockerContainers());
            containersTable.setItems(items);
        } catch (ProcessingException e) {
            new ExpandableDialog(Alert.AlertType.ERROR, "error loadDockerContainers", e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void loadDockerImages() {
        ObservableList<DockerImage> items = imagesTable.getItems();
        try {
            items.setAll(getDockerImages());
            imagesTable.setItems(items);
        } catch (ProcessingException e) {
            new ExpandableDialog(Alert.AlertType.ERROR, "error loadDockerImages", e.getMessage()).showAndWait();
        }
    }

    private List<DockerImage> getDockerImages() {
        List<Image> images = dockerClient.listImagesCmd().exec();
        ArrayList<DockerImage> dockerImages = new ArrayList<>();
        for (Image i : images) {
            dockerImages.add(
                new DockerImage(
                    i.getRepoTags()[0],
                    i.getId(),
                    new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(i.getCreated())),
                    String.valueOf(i.getVirtualSize())
                )
            );
        }
        return dockerImages;
    }

    private List<DockerContainer> getDockerContainers() {
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        ArrayList<DockerContainer> dockerContainers = new ArrayList<>();
        for(Container c : containers) {
            dockerContainers.add(new DockerContainer(c.getId(), c.getImage(), c.getCommand(), c.getNames()[0], c.getStatus()));
        }
        return dockerContainers;
    }

    @FXML
    private void loadConfigAction(ActionEvent e) {

    }
}
