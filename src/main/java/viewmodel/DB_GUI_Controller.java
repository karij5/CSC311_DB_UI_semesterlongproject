package viewmodel;

import dao.DbConnectivityClass;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Person;
import service.MyLogger;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class DB_GUI_Controller implements Initializable {

    @FXML
    MenuItem editItem, deleteItem;

    @FXML
    MenuItem importCSV, exportCSV, exportPDF;
    @FXML
    Button deleteBtn, editBtn, addBtn;
    @FXML
    TextField first_name, last_name, department, email, imageURL;
    @FXML
    ComboBox major;
    @FXML
    ImageView img_view;
    @FXML
    MenuBar menuBar;
    @FXML
    private TableView<Person> tv;
    @FXML
    private TableColumn<Person, Integer> tv_id;
    @FXML
    private TableColumn<Person, String> tv_fn, tv_ln, tv_department, tv_major, tv_email;
    private final DbConnectivityClass cnUtil = new DbConnectivityClass();
    private final ObservableList<Person> data = cnUtil.getData();
    private boolean flag = false;
    @FXML
    Text updateText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            major.setItems( FXCollections.observableArrayList(Major.values()));
            editBtn.setDisable(true);
            deleteBtn.setDisable(true);
            editItem.setDisable(true);
            deleteItem.setDisable(true);
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tv_department.setCellValueFactory(new PropertyValueFactory<>("department"));
            tv_major.setCellValueFactory(new PropertyValueFactory<>("major"));
            tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            tv.setItems(data);

            first_name.setOnKeyPressed(event -> {

                if (event.getCode() != KeyCode.TAB && flag){
                    first_name.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
                    //updateText.setText("");
                    flag=false;
                }
            });
            first_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    //System.out.println("first name text is focused");
                } else {
                    if(first_name.getText().matches("^.[a-zA-Z]{2,25}$"))

                    {
                        first_name.setEditable(true);
                        first_name.setBorder(null);
                    }
                    else
                    {
                        //if regex is not met, the border goes red and a message appears at the update window.
                        first_name.setStyle("-fx-border-color: red ; -fx-border-width: 4px ;");
                        first_name.setVisible(true);
                        first_name.requestFocus();
                        updateText.setText(first_name.getText()+ " is not valid first name.");
                        flag=true;
                    }
                }
            });

            last_name.setOnKeyPressed(event -> {

                if (event.getCode() != KeyCode.TAB && flag){
                    last_name.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
                    //updateText.setText("");
                    flag=false;
                }
            });
            last_name.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    //System.out.println("first name text is focused");
                } else {
                    if(last_name.getText().matches("^.[a-zA-Z]{2,25}$"))

                    {
                        last_name.setEditable(true);
                        last_name.setBorder(null);
                    }
                    else
                    {
                        //if regex is not met, the border goes red and a message appears at the update window.
                        last_name.setStyle("-fx-border-color: red ; -fx-border-width: 4px ;");
                        last_name.setVisible(true);
                        last_name.requestFocus();
                        updateText.setText(last_name.getText()+ " is not valid last name.");
                        flag=true;
                    }
                }
            });

            department.setOnKeyPressed(event -> {

                if (event.getCode() != KeyCode.TAB && flag){
                    department.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
                    //updateText.setText("");
                    flag=false;
                }
            });
            department.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    //System.out.println("first name text is focused");
                } else {
                    if(department.getText().matches("^.[a-zA-Z]{2,25}$"))

                    {
                        department.setEditable(true);
                        department.setBorder(null);
                    }
                    else
                    {
                        //if regex is not met, the border goes red and a message appears at the update window.
                        department.setStyle("-fx-border-color: red ; -fx-border-width: 4px ;");
                        department.setVisible(true);
                        department.requestFocus();
                        updateText.setText(department.getText()+ " is not valid department.");
                        flag=true;
                    }
                }
            });

            email.setOnKeyPressed(event -> {

                if (event.getCode() != KeyCode.TAB && flag){
                    email.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");
                    //updateText.setText("");
                    flag=false;
                }
            });
            email.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    //System.out.println("first name text is focused");
                } else {
                    if(email.getText().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$"))

                    {
                        email.setEditable(true);
                        email.setBorder(null);
                    }
                    else
                    {
                        //if regex is not met, the border goes red and a message appears at the update window.
                        email.setStyle("-fx-border-color: red ; -fx-border-width: 4px ;");
                        email.setVisible(true);
                        email.requestFocus();
                        updateText.setText(email.getText()+ " is not valid email.");
                        flag=true;
                    }
                }
            });
            //Below is code to set a listener for the TVselectionmodel, it enables and disables the edit and delete
            //buttons as necessary when a selection is in focus or not in focus.
            tv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection == null) {
                    editBtn.setDisable(true);
                    deleteBtn.setDisable(true);
                    editItem.setDisable(true);
                    deleteItem.setDisable(true);
                } else {
                    editBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                    editItem.setDisable(false);
                    deleteItem.setDisable(false);
                }


            });

            } catch (Exception e) {
            throw new RuntimeException(e);
        }

        addBtn.setDisable(true);

        // Add listeners to the text properties of the fields
        first_name.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsNotEmpty());
        last_name.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsNotEmpty());
        department.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsNotEmpty());
        email.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsNotEmpty());
        imageURL.textProperty().addListener((observable, oldValue, newValue) -> checkFieldsNotEmpty());
    }

    private void checkFieldsNotEmpty() {
        // Enable the addBtn only if all fields have text
        boolean allFieldsNotEmpty = !first_name.getText().isEmpty() &&
                !last_name.getText().isEmpty() &&
                !department.getText().isEmpty() &&
                !email.getText().isEmpty() &&
                !imageURL.getText().isEmpty();

        addBtn.setDisable(!allFieldsNotEmpty);



    }

    @FXML
    protected void importCSV(){
        try {
            //file chooser for selecting
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                // reader
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                reader.readLine();

                // split each line, making each one a different entry
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    // Create a new Person object using the CSV data
                    Person person = new Person(data[1], data[2], data[3], data[4], data[5], data[6]);

                    // Add the Person to the database
                    cnUtil.insertUser(person);
                    cnUtil.retrieveId(person);
                    person.setId(cnUtil.retrieveId(person));
                    //data.add(person);
                }


                reader.close();

                // Show a success message
                System.out.println("CSV file imported successfully.");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void exportCSV() throws IOException {
        try {
            // Create a file on the desktop
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            File file = new File(desktopPath + "/table_data.csv");

            // Create a BufferedWriter to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Write the column headers to the file
            writer.write("ID,First Name,Last Name,Department,Major,Email\n");

            // Write each row of data to the file
            for (Person person : data) {
                writer.write(person.getId() + "," + person.getFirstName() + "," + person.getLastName() + ","
                        + person.getDepartment() + "," + person.getMajor() + "," + person.getEmail() + "\n");
            }

            // Close the writer
            writer.close();

            // Show a success message
            updateText.setText("Table data exported to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void exportPDF() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Stream for the PDF, I attempted to follow an online tutorial for this bit.
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set the font and font size for the content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);

            float startX = 50;
            float startY = page.getMediaBox().getHeight() - 50;

            float rowHeight = 20;
            float columnWidth = page.getMediaBox().getWidth() - 100;
            String[] headers = {"ID", "First Name", "Last Name", "Department", "Major", "Email"};
            drawTableRow(contentStream, startX, startY, headers, rowHeight, columnWidth, true);

            float currentY = startY - rowHeight;
            for (Person person : data) {
                String[] rowData = {String.valueOf(person.getId()), person.getFirstName(), person.getLastName(),
                        person.getDepartment(), person.getMajor(), person.getEmail()};
                drawTableRow(contentStream, startX, currentY, rowData, rowHeight, columnWidth, false);
                currentY -= rowHeight;
            }

            // Close the content stream
            contentStream.close();

            // Save the PDF file to the desktop
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String filePath = desktopPath + "/table_data.pdf";
            document.save(filePath);
            document.close();

            // Show a success message
            updateText.setText("Contents exported to PDF successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method works with the PDF exporter in order to format the PDF and the columns from the table view
    private void drawTableRow(PDPageContentStream contentStream, float startX, float startY, String[] rowData,
                              float rowHeight, float columnWidth, boolean isHeader) throws IOException {
        float currentX = startX;

        if (isHeader) {
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
        } else {
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
        }

        // Draw each cell in the row
        for (int i = 0; i < rowData.length; i++) {
            String cellData = rowData[i];

            // Calculate the width of the cell based on the column width
            float cellWidth = columnWidth / rowData.length;

            // Draw the cell rectangle
            contentStream.setLineWidth(1f);
            contentStream.setStrokingColor(0, 0, 0);
            contentStream.setNonStrokingColor(255, 255, 255);
            contentStream.addRect(currentX, startY, cellWidth, rowHeight);
            contentStream.fill();

            // Write the cell data to the PDF
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.beginText();
            contentStream.newLineAtOffset(currentX + 5, startY - 15);
            contentStream.showText(cellData);
            contentStream.endText();

            // Move to the next cell position
            currentX += cellWidth;
        }
    }

    @FXML
    protected void addNewRecord() {
        //was going to do something with an if statement here but decided to do it in the initializable method
        //instead.
            if(last_name.getText() != null && last_name.getText() != null && department.getText() != null &&
                    major.getSelectionModel() != null && email.getText() != null && imageURL.getText() != null) {
                Person p = new Person(last_name.getText(), last_name.getText(), department.getText(),
                        major.getSelectionModel().toString(), email.getText(), imageURL.getText());
                cnUtil.insertUser(p);
                cnUtil.retrieveId(p);
                p.setId(cnUtil.retrieveId(p));
                data.add(p);
                clearForm();
            } else {

            }

        updateText.setText("Added record to the database successfuly.");

    }

    @FXML
    protected void clearForm() {
        first_name.setText("");
        last_name.setText("");
        department.setText("");
        major.getSelectionModel().clearSelection();
        email.setText("");
        imageURL.setText("");
        updateText.setText("Cleared form successfully");
    }

    @FXML
    protected void logOut(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/lightTheme.css").getFile());
            Stage window = (Stage) menuBar.getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

    @FXML
    protected void displayAbout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/about.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void editRecord() {
        Person p = tv.getSelectionModel().getSelectedItem();
        if(p!=null){
            editBtn.setDisable(false);
            editItem.setDisable(false);
        }

        int index = data.indexOf(p);
        Person p2 = new Person(index + 1, last_name.getText(), last_name.getText(), department.getText(),
                major.getSelectionModel().toString(), email.getText(),  imageURL.getText());
        cnUtil.editUser(p.getId(), p2);
        data.remove(p);
        data.add(index, p2);
        tv.getSelectionModel().select(index);
        updateText.setText("Record successfully edited.");
    }

    @FXML
    protected void deleteRecord() {
        Person p = tv.getSelectionModel().getSelectedItem();
        if(p!=null){
            deleteBtn.setDisable(false);
            deleteItem.setDisable(false);
        }
        int index = data.indexOf(p);
        cnUtil.deleteRecord(p);
        data.remove(index);
        tv.getSelectionModel().select(index);
        updateText.setText("Record deleted successfully.");
    }

    @FXML
    protected void showImage() {
        File file = (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if (file != null) {
            img_view.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    protected void addRecord() {
        showSomeone();
    }

    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        Person p = tv.getSelectionModel().getSelectedItem();
        last_name.setText(p.getFirstName());
        last_name.setText(p.getLastName());
        department.setText(p.getDepartment());
        major.setSelectionModel(null);
        email.setText(p.getEmail());
        imageURL.setText(p.getImageURL());
        updateText.setText(p.getFirstName() + p.getLastName() + " is now selected.");
    }

    public void lightTheme(ActionEvent actionEvent) {
        try {
            Scene scene = menuBar.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.getScene().getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/css/lightTheme.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
            System.out.println("light " + scene.getStylesheets());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void darkTheme(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSomeone() {
        Dialog<Results> dialog = new Dialog<>();
        dialog.setTitle("New User");
        dialog.setHeaderText("Please specify…");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField textField1 = new TextField("Name");
        TextField textField2 = new TextField("Last Name");
        TextField textField3 = new TextField("Email ");
        ObservableList<Major> options =
                FXCollections.observableArrayList(Major.values());
        ComboBox<Major> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
        dialogPane.setContent(new VBox(8, textField1, textField2,textField3, comboBox));
        Platform.runLater(textField1::requestFocus);
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new Results(textField1.getText(),
                        textField2.getText(), comboBox.getValue());
            }
            return null;
        });
        Optional<Results> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Results results) -> {
            MyLogger.makeLog(
                    results.fname + " " + results.lname + " " + results.major);
        });
    }

    private static enum Major {Business, CSC, CPIS}

    private static class Results {

        String fname;
        String lname;
        Major major;

        public Results(String name, String date, Major venue) {
            this.fname = name;
            this.lname = date;
            this.major = venue;
        }
    }

}