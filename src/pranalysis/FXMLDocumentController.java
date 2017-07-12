/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pranalysis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.*;

/**
 *
 * @author Y50-70
 */

public class FXMLDocumentController implements Initializable {
    
    //defining the main arrays which keep the main data values to be displayed
    public int[] LRUData;
    public int[] OptimalData;
    public int[] secondchanceData;
    
    @FXML
    private RadioButton lruSelect;
    @FXML
    private RadioButton optimalSelect;
    @FXML
    private RadioButton secondChance;
    @FXML
    private Label warningBar;
    @FXML
    private RadioButton phaseOne;
    @FXML
    private RadioButton phaseTwo;
    
    @FXML
    private TextField requestCount;
    
    @FXML
    private TextField PageStart;
    
    @FXML
    private TextField PageEnd;
    
    @FXML
    private TextField Mean;
    
    @FXML
    private TextField Variance;
    
    @FXML
    private Label phaselabel;
    
    @FXML
    private Label algolabel;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
//    handleRunButton
    
    @FXML
    private void handleRunButton(ActionEvent event) throws IOException {
        warningBar.setText("");
        
        if((lruSelect.isSelected() || optimalSelect.isSelected() || secondChance.isSelected()) && (phaseOne.isSelected() || phaseTwo.isSelected()))
            {
                int phase=0;
                if(phaseOne.isSelected()) phase = 1;
                else phase = 2;
                MainHandler(lruSelect.isSelected(), optimalSelect.isSelected(), secondChance.isSelected(), phase );
            }
        else
            warningBar.setText("No Algorithm Selected or NO Phase Selected!");

        
        
    }
    
    @FXML
    private void handlePhaseOne(ActionEvent event) {
        if(phaseTwo.isSelected())
            phaseTwo.fire();
        phaseOne.arm();
    }
    
    @FXML
    private void handlePhaseTwo(ActionEvent event) {
       if(phaseOne.isSelected())
            phaseOne.fire();
        phaseTwo.arm();
    }
    
    public void MainHandler(boolean lru , boolean optimal , boolean secondchance , int phase) throws IOException{
    
        if(phase == 1){ //phase one dids
            int pageStart = 1;
            int pageEnd = 10;
            UniformNumberGenerator generator = new UniformNumberGenerator();
            int[] dataArray = generator.getNum();
            if(lru)
            {
                LRU lruAlgo = new LRU(dataArray);
                LRUData = lruAlgo.getFaultsNumber();
            }
            if(optimal)
            {
                Optimal optimalAlgo = new Optimal(dataArray);
                OptimalData = optimalAlgo.getFaultsNumber();
            }
            if(secondchance)
            {
                SecondChance secChance = new SecondChance(dataArray);
                secondchanceData = secChance.getFaultsNumber();
            }
            graphHandler(lru,optimal , secondchance , LRUData , OptimalData , secondchanceData, pageStart, pageEnd);
            
        }
        else
        {
            int reqCount = Integer.parseInt(requestCount.getText());
            int pageStart = Integer.parseInt(PageStart.getText());
            int pageEnd = Integer.parseInt(PageEnd.getText());
            double mean = Double.parseDouble(Mean.getText());
            double variance = Double.parseDouble(Variance.getText());
            
            Normal normalGenerator = new Normal(reqCount,pageStart, pageEnd, mean, variance);
            int[] dataArray = doubleToInt(normalGenerator.getNum());
            
            if(lru)
            {
                LRU lruAlgo = new LRU(dataArray , pageStart, pageEnd);
                LRUData = lruAlgo.getFaultsNumber();
            }
            if(optimal)
            {
                Optimal optimalAlgo = new Optimal(dataArray, pageStart, pageEnd);
                OptimalData = optimalAlgo.getFaultsNumber();
            }
            if(secondchance)
            {
                SecondChance secChance = new SecondChance(dataArray, pageStart, pageEnd);
                secondchanceData = secChance.getFaultsNumber();
            }
            graphHandler(lru,optimal , secondchance , LRUData , OptimalData , secondchanceData, pageStart, pageEnd);
        }
        
    }
    
    public int[] doubleToInt(double[] d){
        
        int[] temp = new int[d.length];
        for(int i=0 ;i<d.length ; i++)
            temp[i] = (int)d[i];
        return temp;
    }
    
    public void graphHandler(boolean lru , boolean optimal , boolean secondChance ,int[] lrudata , int[] optimaldata , int[] secchancedata ,   int minpage , int maxpage) throws IOException{
        
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Number of Pages");
    yAxis.setLabel("Number of Page Faults");
    final LineChart<String, Number> lineChart = new LineChart<String, Number>(
        xAxis, yAxis);

    lineChart.setTitle("Page Fault Chart");

    XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
    if(lru){
    
    series1.setName("LRU");
    
    for(int i=0;i<lrudata.length;i++)
    series1.getData().add(new XYChart.Data<String, Number>(String.valueOf(minpage+i) , lrudata[i]));
    
    }
    
    XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
    if(optimal){
    
    series2.setName("Optimal");
    
    for(int i=0;i<optimaldata.length;i++)
    series2.getData().add(new XYChart.Data<String, Number>(String.valueOf(minpage+i) , optimaldata[i]));

    }
    
    XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
    if(secondChance){
    
    series3.setName("Second Chance");
    
    for(int i=0;i<secchancedata.length;i++)
    series3.getData().add(new XYChart.Data<String, Number>(String.valueOf(minpage+i) , secchancedata[i]));
    }
    
    Stage stage = new Stage();
    Scene scene = new Scene(lineChart, 800, 600);
    if(lru) lineChart.getData().add(series1);
    if(optimal) lineChart.getData().add(series2);
    if(secondChance) lineChart.getData().add(series3);
    stage.setScene(scene);
    stage.show();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phaselabel.getStyleClass().add("algolabel");
        algolabel.getStyleClass().add("phaselabel");
        
    lruSelect.getStyleClass().add("radiobutton");
    optimalSelect.getStyleClass().add("radiobutton");
    secondChance.getStyleClass().add("radiobutton");
    warningBar.getStyleClass().add("warning");
    phaseOne.getStyleClass().add("radiobutton");
    phaseTwo.getStyleClass().add("radiobutton");
    }    
    
}
