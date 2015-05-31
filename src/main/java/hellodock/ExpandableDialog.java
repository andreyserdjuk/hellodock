package hellodock;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ExpandableDialog {

    private String title = "Exception Dialog";
    private String headerText = "Look, an Exception Dialog";
    private String stackTraceTitle = "Stacktrace: ";
    private Alert alert;

    public ExpandableDialog(Alert.AlertType type, String contentText, String expandableContent) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Label label = new Label(stackTraceTitle);

        TextArea textArea = new TextArea(expandableContent);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setContent(expContent);
    }

    public void showAndWait() {
        alert.showAndWait();
    }
}