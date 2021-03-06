package com.gbsdevelopers.gbdziennik.admin;

import com.gbsdevelopers.gbdziennik.Program;
import com.gbsdevelopers.gbssocket.GbsMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Controller for AddTeacher
 */
public class AddTeacherController {
    /**
     * Logger for log4j
     */
    private static final Logger logger = LogManager.getLogger(AddTeacherController.class);

    /**
     * Phone TextField
     */
    @FXML
    private TextField phoneTextField;

    /**
     * Name TextField
     */
    @FXML
    private TextField nameTextField;

    /**
     * Password TextField
     */
    @FXML
    private TextField passwordTextField;

    /**
     * Surname TextField
     */
    @FXML
    private TextField surnameTextField;

    /**
     * Handler for AddButton
     *
     * @param event Event that invoked action
     */
    @FXML
    void addButtonClicked(ActionEvent event) {
        logger.info("Clicked addButton");

        if (!(nameTextField.getText().isEmpty()) && !(surnameTextField.getText().isEmpty()) && !(passwordTextField.getText().isEmpty()) && !(phoneTextField.getText().isEmpty())) {

            GbsMessage message = new GbsMessage();

            message.header = "_addTeacher";

            message.arguments.add(nameTextField.getText());
            message.arguments.add(surnameTextField.getText());
            message.arguments.add(GbsMessage.MD5(passwordTextField.getText()));
            message.arguments.add(phoneTextField.getText());

            try {
                Program.socket.executeRequest(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
        }
    }

}