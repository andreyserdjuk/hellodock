package hellodock;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    DockerClient dockerClient;

    @FXML private PasswordField rootPass;

    @FXML private Text rootPassMsg;

    @FXML
    private TableView<DockerContainer> containersTable;

    @FXML
    private TableView<DockerImage> imagesTable;

    private ArrayList<DockerContainer> getDockerContainersCmd() {
        String pass = rootPass.getText();
        if(pass.isEmpty()) {
            System.out.println(rootPass.getText());
            throw new RuntimeException("Enter root password");
        }

        String[] cmd = {"/bin/bash","-c","echo " + pass + "| sudo -S docker ps -a"};
        ArrayList<DockerContainer> list = new ArrayList<>();

        try {
            Process pb = Runtime.getRuntime().exec(cmd);
            String line;
            BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
            input.readLine();
            while ((line = input.readLine()) != null) {
                String[] args = line.split("\\s{3,}+\\s*");
                DockerContainer d = new DockerContainer(args[0], args[1], args[2], args[6]);
                list.add(d);
            }
            input.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DockerClient dockerClient = DockerClientBuilder.getInstance("unix:///var/run/docker.sock").build();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                loadDockerContainers();
            }
        }, 0L, 1000L);
    }

    private void loadDockerContainers() {
        ObservableList<DockerContainer> items = containersTable.getItems();
        try {
            items.setAll(getDockerContainers());
            containersTable.setItems(items);
        } catch (RuntimeException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private void loadDockerImages() {
        ObservableList<DockerImage> items = imagesTable.getItems();
        try {
//            items.setAll(getD)
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private List<DockerImage> getDockerImages() {
        List<Image> images = dockerClient.listImagesCmd().exec();
        ArrayList<DockerImage> dockerImages = new ArrayList<>();
        for (Image i : images) {
//            dockerImages.add(new DockerImage(i.getRepoTags()[0], i.get));
        }
        return dockerImages;
    }

    private List<DockerContainer> getDockerContainers() {
//        Info info = dockerClient.infoCmd().exec();
        List<Container> containers = dockerClient.listContainersCmd().exec();
        ArrayList<DockerContainer> dockerContainers = new ArrayList<>();
        for(Container c : containers) {
            dockerContainers.add(new DockerContainer(c.getId(), c.getImage(), c.getCommand(), c.getNames()[0]));
        }
        return dockerContainers;
    }

    @FXML
    private void loadConfigAction(ActionEvent e) {

    }
}
