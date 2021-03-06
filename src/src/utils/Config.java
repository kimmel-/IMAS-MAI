package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.Serializable;

public class Config implements Serializable {

    private String action;
    private String title;
    private String algorithm;
    private String classifiers;
    private String trainingSettings;
    private String classifierInstances;
    private String file;

    public Config(String action, String file_path) {
        this.setAction(action);
        if (action.equals("T")) {
            File xmlFile = new File(file_path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = dbFactory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();

                NodeList nList = document.getElementsByTagName("SimulationSettings");
                // Get root element
                Node nNode = nList.item(0);
                Element rootElement = (Element) nNode;

                //myLogger.log(Logger.INFO, "Node " + rootElement.getAttributes().getLength());
                this.setTitle(rootElement.getElementsByTagName("title").item(0).getTextContent());
                this.setAlgorithm(rootElement.getElementsByTagName("algorithm").item(0).getTextContent());
                this.setClassifiers(rootElement.getElementsByTagName("classifiers").item(0).getTextContent());
                this.setTrainingSettings(rootElement.getElementsByTagName("trainingSettings").item(0).getTextContent());
                this.setClassifierInstances(rootElement.getElementsByTagName("classifyInstances").item(0).getTextContent());
                this.setFile(rootElement.getElementsByTagName("file").item(0).getTextContent());
                //myLogger.log(Logger.INFO, "Simulation " + configReader.getTitle() + " config loaded!");
            } catch (Exception e) {
                System.err.println("Parsing of xml configuration file error: \n" + e.getMessage());
            }
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(String classifiers) {
        this.classifiers = classifiers;
    }

    public String getTrainingSettings() {
        return trainingSettings;
    }

    public void setTrainingSettings(String trainingSettings) {
        this.trainingSettings = trainingSettings;
    }

    public String getClassifierInstances() {
        return classifierInstances;
    }

    public void setClassifierInstances(String classifierInstances) {
        this.classifierInstances = classifierInstances;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String toString(){
        return "\n - Title: " + this.getTitle() + "\n" +
               " - Algorithm: " + this.getAlgorithm() + "\n" +
               " - File (Dataset): " + this.getFile() + "\n" +
               " - Classifiers: " + this.getClassifiers() + "\n" +
               " - Classifier Instances:  " + this.getClassifierInstances() + "\n" +
               " - Training Settings: " + this.getTrainingSettings() + "\n";
    }
}
