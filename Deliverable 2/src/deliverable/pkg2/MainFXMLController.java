package deliverable.pkg2;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.*;
import eu.hansolo.medusa.GaugeBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Matouš Synek & Manuel Roselló
 */
public class MainFXMLController implements Initializable {
    
    @FXML
    private ToggleButton scene1;
    @FXML
    private ToggleGroup tabs;
    @FXML
    private ImageView s1img;
    @FXML
    private ToggleButton scene2;
    @FXML
    private ImageView s2img;
    @FXML
    private ToggleButton scene3;
    @FXML
    private ImageView s3img;
    @FXML
    private ToggleButton scene4;
    @FXML
    private ImageView s4img;
    @FXML
    private ToggleButton nightMode;
    @FXML
    private ImageView nModeimg;
    @FXML
    private GridPane grid;
    
    Scene scene;
    
    private Gauge HDG;
    private Gauge TEMP;
    private Gauge ROLL;
    private Gauge PITCH;
    private String LAT = new String();
    private String LON = new String();
    private Gauge COG;
    private Gauge SOG;
    private Gauge TWD;
    private Gauge TWS;
    private Gauge AWA;
    private Gauge AWS;
    private ArrayList<Double> windDirData;
    private ArrayList<Double> windIntData;
    private LineChart<String,Double> windDirChart;
    private LineChart<String,Double> windIntChart;
    private Slider dirChartSlider;
    private Label selM;
    private Circle clat;
    private Circle clon;
    
    @FXML
    private Label latL;
    @FXML
    private Label lonL;
    
    @FXML
    private AnchorPane pane;
    
    public Color selectedDay = Color.web("0x26c6da");
    public Color selectedNight = Color.web("0x03a9f4");
    
    private String themeDay = getClass().getResource("/styles/day.css").toExternalForm();
    private String themeNight = getClass().getResource("/styles/night.css").toExternalForm();
    
    public boolean isDay = true;
    public int selectedScene = 1;
    
    private Model model;
    @FXML
    private Label latLabel;
    @FXML
    private Label lonLabel;
    
    private void updateChart(LineChart chart, ArrayList list){
         XYChart.Series<String,Number> series = new XYChart.Series();
         
         if (list.size()==601) list.remove(0);
         int bound;
         bound = (list.size()>dirChartSlider.getValue()*60) ? (int)(dirChartSlider.getValue()*60) : list.size();    
         
         int aux = (list.size()-dirChartSlider.getValue()*60 > 0) ? list.size()-(int)dirChartSlider.getValue()*60 : 0;
         for (int i = aux; i < bound;i++){
            series.getData().add(new XYChart.Data<>((int)(i/60) +":"+ i%60,(double) list.get( i)));
        }
         chart.getData().clear();
         chart.getData().add(series);
    }
    
    private double toAngle(double value){
        return (value / 360) * 400;
    }
    
    public void init(Stage st, Scene sc){
        scene = sc;
        scene.getStylesheets().clear();
        scene.getStylesheets().add(themeDay);
        loadFile();
    }
    
    private void loadFile(){      
        File ficheroNMEA = new File("src\\resources\\Jul_20_2017_1871339_0183.NMEA");
        if (ficheroNMEA != null) {
            try{
                model.addSentenceReader(ficheroNMEA);
            }
            catch(FileNotFoundException e){System.out.println(e);}
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        HDG = GaugeBuilder.create()
                          .minValue(0)
                          .maxValue(359)
                          .startAngle(180)
                          .angleRange(360)
                          .autoScale(true)
                          .customTickLabelsEnabled(true)
                          .customTickLabels("N","","E","","S","","W","")
                          .customTickLabelFontSize(72)
                          .minorTickMarksVisible(false)
                          .mediumTickMarksVisible(true)
                          .majorTickMarksVisible(true)
                          .valueVisible(false)
                          .needleType(NeedleType.FAT)
                          .needleShape(NeedleShape.FLAT)
                          .knobType(KnobType.FLAT)
                          .knobColor(Gauge.DARK_COLOR)
                          .borderPaint(Gauge.DARK_COLOR)
                          .animated(true)
                          .animationDuration(500)
                          .needleBehavior(NeedleBehavior.OPTIMIZED)
                          .barBackgroundColor(Color.BLACK)
                          .needleColor(selectedDay)
                          .prefSize(320,220)
                          .title("hdg")
                          .build();
        
        
        grid.getChildren().add(HDG);
        GridPane.setColumnIndex(HDG,0);
        GridPane.setRowIndex(HDG, 0); 
        
        TEMP = GaugeBuilder.create()
                        .minValue(-100)
                        .maxValue(100)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("ºC")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(Color.CRIMSON)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("temp")
                        .build();
        
        grid.getChildren().add(TEMP);
        
        GridPane.setColumnIndex(TEMP,1);
        GridPane.setRowIndex(TEMP, 0);
        
        ROLL = GaugeBuilder.create()
                        .minValue(-180)
                        .maxValue(180)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("º")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("roll")
                        .build();
        
        grid.getChildren().add(ROLL);
        
        GridPane.setColumnIndex(ROLL,0);
        GridPane.setRowIndex(ROLL, 1);
        
        PITCH = GaugeBuilder.create()
                        .minValue(-180)
                        .maxValue(180)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("º")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("pitch")
                        .build();
        
        grid.getChildren().add(PITCH);
        
        GridPane.setColumnIndex(PITCH,1);
        GridPane.setRowIndex(PITCH, 1);
        
        
        latLabel.setFont(Font.font(latL.getFont().getName(),FontWeight.BOLD,20));
        lonLabel.setFont(Font.font(latL.getFont().getName(),FontWeight.BOLD,20));

        latL.setText(LAT);
        latL.setFont(Font.font(latL.getFont().getName(),FontWeight.LIGHT,45));
        

        lonL.setText(LON);
        lonL.setFont(Font.font(lonL.getFont().getName(),FontWeight.LIGHT,45));
        
        clat = new Circle();
        grid.getChildren().add(clat);
        GridPane.setColumnIndex(clat,0);
        GridPane.setRowIndex(clat,0);
        clat.setRadius(115);
        clat.setTranslateX(47.5);
        clat.setCenterY(120);
        clat.setFill(Color.TRANSPARENT);
        clat.setStroke(Color.BLACK);
        
        clon = new Circle();
        grid.getChildren().add(clon);
        GridPane.setColumnIndex(clon,1);
        GridPane.setRowIndex(clon,0);
        clon.setRadius(115);
        clon.setTranslateX(47.5);
        clon.setCenterY(120);
        clon.setFill(Color.TRANSPARENT);
        clon.setStroke(Color.BLACK);
        
        
        COG = GaugeBuilder.create()
                        .minValue(0)
                        .maxValue(360)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("º")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("cog")
                        .build();
        
        grid.getChildren().add(COG);
        
        GridPane.setColumnIndex(COG,0);
        GridPane.setRowIndex(COG, 1);
        
        SOG = GaugeBuilder.create()
                        .minValue(0)
                        .maxValue(10)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("kn")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("sog")
                        .build();
        
        grid.getChildren().add(SOG);
        
        GridPane.setColumnIndex(SOG,1);
        GridPane.setRowIndex(SOG, 1);
        
        TWD = GaugeBuilder.create()
                          .minValue(0)
                          .maxValue(359)
                          .startAngle(180)
                          .angleRange(360)
                          .autoScale(true)
                          .customTickLabelsEnabled(true)
                          .customTickLabels("N","","E","","S","","W","")
                          .customTickLabelFontSize(72)
                          .minorTickMarksVisible(false)
                          .mediumTickMarksVisible(true)
                          .majorTickMarksVisible(true)
                          .valueVisible(false)
                          .needleType(NeedleType.FAT)
                          .needleShape(NeedleShape.FLAT)
                          .knobType(KnobType.FLAT)
                          .knobColor(Gauge.DARK_COLOR)
                          .borderPaint(Gauge.DARK_COLOR)
                          .animated(true)
                          .animationDuration(500)
                          .needleBehavior(NeedleBehavior.OPTIMIZED)
                          .barBackgroundColor(Color.BLACK)
                          .needleColor(selectedDay)
                          .prefSize(320,220)
                          .title("twd")
                          .build();
        
        
        grid.getChildren().add(TWD);
        
        GridPane.setColumnIndex(TWD,0);
        GridPane.setRowIndex(TWD, 0);
        
        TWS = GaugeBuilder.create()
                        .minValue(0)
                        .maxValue(20)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("kn")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("tws")
                        .build();
        
        grid.getChildren().add(TWS);
        
        GridPane.setColumnIndex(TWS,1);
        GridPane.setRowIndex(TWS, 0);
        
        AWS = GaugeBuilder.create()
                        .minValue(0)
                        .maxValue(20)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("kn")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("aws")
                        .build();
        
        grid.getChildren().add(AWS);
        
        GridPane.setColumnIndex(AWS,1);
        GridPane.setRowIndex(AWS, 1);
        
        AWA = GaugeBuilder.create()
                        .minValue(0)
                        .maxValue(360)
                        .startFromZero(true)
                        .autoScale(true)
                        .decimals(2)
                        .unit("º")
                        .unitColor(Color.BLACK)
                        .titleColor(Color.BLACK)
                        .barColor(selectedDay)
                        .skinType(SkinType.SIMPLE_SECTION)
                        .borderPaint(Gauge.DARK_COLOR)
                        .animated(true)
                        .animationDuration(500)
                        .needleBehavior(NeedleBehavior.OPTIMIZED)
                        .barBackgroundColor(Color.web("0xe0e0e0"))
                        .valueColor(Color.BLACK)
                        .needleColor(selectedDay)
                        .prefSize(320,220)
                        .borderWidth(1)
                        .title("awa")
                        .build();
        
        grid.getChildren().add(AWA);
        
        GridPane.setColumnIndex(AWA,0);
        GridPane.setRowIndex(AWA, 1);
        

        NumberAxis yAxis = new NumberAxis();
        CategoryAxis xAxis = new CategoryAxis();
        
        xAxis.setLabel("");
        yAxis.setLabel("");
        
        windDirChart = new LineChart(xAxis,yAxis);
        windDirChart.setPrefSize(640,230);
        windDirChart.setTranslateX(0);
        windDirChart.setTranslateY(0);
        windDirChart.setAnimated(false);
        windDirChart.setTitle("twd");
        windDirChart.setCreateSymbols(false);
        windDirChart.setLegendVisible(false);
        
        windDirData = new ArrayList();
        
        pane.getChildren().add(windDirChart);
        
        NumberAxis yAxis1 = new NumberAxis();
        CategoryAxis xAxis1 = new CategoryAxis();
        
        xAxis1.setLabel("");
        yAxis1.setLabel("");
        
        windIntChart = new LineChart(xAxis1,yAxis1);
        windIntChart.setPrefSize(640,230);
        windIntChart.setTranslateX(0);
        windIntChart.setTranslateY(250);
        windIntChart.setAnimated(false);
        windIntChart.setTitle("tws");
        windIntChart.setCreateSymbols(false);
        windIntChart.setLegendVisible(false);
        
        windIntData = new ArrayList();
        
        
        pane.getChildren().add(windIntChart);
        
        dirChartSlider = new Slider();
        dirChartSlider.setMin(2);
        dirChartSlider.setMax(10);
        dirChartSlider.setPrefSize(540, 30);
        dirChartSlider.setTranslateY(225);
        dirChartSlider.setTranslateX(20);
        dirChartSlider.setShowTickLabels(false);
        dirChartSlider.setShowTickMarks(false);
        dirChartSlider.setMajorTickUnit(1);
        dirChartSlider.setBlockIncrement(1);
        dirChartSlider.setSnapToTicks(true);
        dirChartSlider.setMinorTickCount(0);
        pane.getChildren().add(dirChartSlider);
        
        selM = new Label("2 min");
        selM.setFont(Font.font(latL.getFont().getName(),FontWeight.BOLD,18));
        selM.setTranslateY(225);
        selM.setTranslateX(570);
        pane.getChildren().add(selM); 
        
        dirChartSlider.valueProperty().addListener((l,o,n)-> {    
           selM.setText(n.intValue()+" min"); 
        });
        
        //Model Listener ---------------------------------------------------------------------
        
        model=Model.getInstance();
        
        
        model.HDGProperty().addListener((l, o, n) -> {
            double dat = n.doubleValue();
                 Platform.runLater(() -> {
                HDG.setValue(toAngle(dat));
                HDG.setSubTitle(dat + "º");
            });
        });
        model.TEMPProperty().addListener((l,o,n) ->{
            double dat = n.doubleValue();
                 Platform.runLater(() -> {
                     TEMP.setValue(dat);
            });
        });
        model.ROLLProperty().addListener((l,o,n) ->{
            double dat = n.doubleValue();
            dat = toAngle(dat);
            if (dat >180) dat -= 360;
            final double val = dat;
                 Platform.runLater(() -> {
                ROLL.setValue(val);
            });
        });
        model.PITCHProperty().addListener((l,o,n) ->{
            double dat = n.doubleValue();
            dat = toAngle(dat);
            if (dat >180) dat -= 360;
            final double val = dat;
                 Platform.runLater(() -> {
                PITCH.setValue(val);
            });
        });
        model.GPSProperty().addListener((l,o,n)->{
            Platform.runLater(() -> {
                LON = String.valueOf("  "+Math.round(Math.abs(n.getLongitude())*100000.00)/100000.00) + "º\n " + n.getLongitudeHemisphere();
                LAT = String.valueOf("  "+Math.round(Math.abs(n.getLatitude())*100000.00)/100000.00) + "º\n " + n.getLatitudeHemisphere();
                latL.setText(LAT);
                lonL.setText(LON);
            });
        });

        model.COGProperty().addListener((l,o,n)->{
            double dat = n.doubleValue();
                 Platform.runLater(() -> {
                COG.setValue(dat);
            });
        });
        model.SOGProperty().addListener((l,o,n)->{
            double dat = n.doubleValue();
            Platform.runLater(() -> {
                SOG.setValue(dat);
            });
        });
        model.TWDProperty().addListener((l,o,n)->{ 
            double dat = n.doubleValue();
                 Platform.runLater(() -> {
                TWD.setValue(toAngle(dat));
                TWD.setSubTitle(dat + "º");
                windDirData.add(dat);
                updateChart(windDirChart,windDirData);
            });
        });
        model.TWSProperty().addListener((l,o,n)->{
            double dat = n.doubleValue();
            Platform.runLater(() -> {
                TWS.setValue(dat);
                windIntData.add(dat);
                updateChart(windIntChart,windIntData);
            });
        });
        model.AWSProperty().addListener((l,o,n)->{
            double dat = n.doubleValue();
            Platform.runLater(() -> {
                AWS.setValue(dat);
            });
        });
        model.AWAProperty().addListener((l,o,n)->{
            double dat = n.doubleValue();
                 Platform.runLater(() -> {
                AWA.setValue(toAngle(dat));
                AWA.setSubTitle(dat + "º");
            });
        });
        
        
        // Scenes Listener ---------------------------------------------------------------------
        scene1.selectedProperty().addListener((l,o,n)->{
            if (n){
                selectedScene = 1;
                setScene1(true);
            }
            else{
                if (scene2.isSelected() || scene3.isSelected() || scene4.isSelected()) {
                    setScene1(false);
                    }
                else scene1.setSelected(true);
            }
    });
        scene2.selectedProperty().addListener((l,o,n)->{
            if (n){
                selectedScene = 2;
                setScene2(true);
            }
            else {
                if (scene1.isSelected() || scene3.isSelected() || scene4.isSelected()) {
                    setScene2(false);
                }
                else scene2.setSelected(true);
            }
        });
        
        scene3.selectedProperty().addListener((l,o,n)->{
            if (n){
                selectedScene = 3;
                setScene3(true);
            }
            else {
                if (scene1.isSelected() || scene2.isSelected() || scene4.isSelected()) {
                    setScene3(false);
                }
                else scene3.setSelected(true);
            }
        });
        
        scene4.selectedProperty().addListener((l,o,n)->{
            if (n){
                selectedScene = 4;
                setScene4(true);
            }
            else {
                if (scene1.isSelected() || scene3.isSelected() || scene2.isSelected()) {
                    setScene4(false);
                }
                else scene4.setSelected(true);
            }
        });
        setScene();
    }    

    @FXML
    private void changeMode() {
        if (nightMode.isSelected()){
            isDay = false;
            scene.getStylesheets().clear();
            scene.getStylesheets().add(themeNight);
            pane.getStyleClass().add("anchor-pane");
            s1img.setImage(new Image("/resources/compassWhite.png"));
            s2img.setImage(new Image("/resources/mapWhite.png"));
            s3img.setImage(new Image("/resources/windWhite.png"));
            s4img.setImage(new Image("/resources/graphWhite.png"));
            nModeimg.setImage(new Image("/resources/day.png"));
        }
        else{
            isDay = true;
            scene.getStylesheets().clear();
            scene.getStylesheets().add(themeDay);
            s1img.setImage(new Image("/resources/compassBlack.png"));
            s2img.setImage(new Image("/resources/mapBlack.png"));
            s3img.setImage(new Image("/resources/windBlack.png"));
            s4img.setImage(new Image("/resources/graphBlack.png"));
            nModeimg.setImage(new Image("/resources/night.png"));
        }
        setScene();
    }
    
    private void setScene(){
        switch (selectedScene){
            case 1:
                setScene1(true);
                setScene2(false);
                setScene3(false);
                setScene4(false);
                break;
            case 2:
                setScene2(true);
                setScene1(false);
                setScene3(false);
                setScene4(false);
                break;
            case 3:
                setScene3(true);
                setScene1(false);
                setScene2(false);
                setScene4(false);
                break;
            case 4:
                setScene4(true);
                setScene1(false);
                setScene2(false);
                setScene3(false);
                break;
            default:
                break;
        }
    }

    private void setScene1(boolean b) {
       if (b){
            HDG.setVisible(true);
            TEMP.setVisible(true);
            ROLL.setVisible(true);
            PITCH.setVisible(true);
            if (isDay){
                
                //HDG
                HDG.setTickLabelColor(Color.BLACK);
                HDG.setBarBackgroundColor(Color.BLACK);
                HDG.setValueColor(Color.BLACK);
                HDG.setBorderPaint(Color.BLACK);
                HDG.setNeedleColor(selectedDay);
                HDG.setTitleColor(Color.BLACK);
                HDG.setSubTitleColor(Color.BLACK);
                HDG.setMajorTickMarkColor(Color.BLACK);
                HDG.setMediumTickMarkColor(Color.BLACK);
                
                //TEMP
                TEMP.setBarBackgroundColor(Color.web("0xe0e0e0"));
                TEMP.setValueColor(Color.BLACK);
                TEMP.setBorderPaint(Color.BLACK);
                TEMP.setNeedleColor(selectedDay);
                TEMP.setTitleColor(Color.BLACK);
                TEMP.setUnitColor(Color.BLACK);
                TEMP.setBarColor(selectedDay);
                
                //ROLL
                ROLL.setBarBackgroundColor(Color.web("0xe0e0e0"));
                ROLL.setValueColor(Color.BLACK);
                ROLL.setBorderPaint(Color.BLACK);
                ROLL.setNeedleColor(selectedDay);
                ROLL.setTitleColor(Color.BLACK);
                ROLL.setUnitColor(Color.BLACK);
                ROLL.setBarColor(selectedDay);
                
                //PITCH
                PITCH.setBarBackgroundColor(Color.web("0xe0e0e0"));
                PITCH.setValueColor(Color.BLACK);
                PITCH.setBorderPaint(Color.BLACK);
                PITCH.setNeedleColor(selectedDay);
                PITCH.setTitleColor(Color.BLACK);
                PITCH.setUnitColor(Color.BLACK);
                PITCH.setBarColor(selectedDay);
            }
            else{
                //HDG
                HDG.setBarBackgroundColor(Color.WHITE);
                HDG.setTickLabelColor(Color.WHITE);
                HDG.setValueColor(Color.WHITE);
                HDG.setBorderPaint(Color.WHITE);
                HDG.setTitleColor(Color.WHITE);
                HDG.setSubTitleColor(Color.WHITE);
                HDG.setMajorTickMarkColor(Color.WHITE);
                HDG.setMediumTickMarkColor(Color.WHITE);
                
                //TEMP
                TEMP.setBarBackgroundColor(Color.WHITE);
                TEMP.setValueColor(Color.WHITE);
                TEMP.setBorderPaint(Color.WHITE);
                TEMP.setNeedleColor(selectedNight);
                TEMP.setTitleColor(Color.WHITE);
                TEMP.setUnitColor(Color.WHITE);
                TEMP.setBarColor(selectedNight);
                
                //ROLL
                ROLL.setBarBackgroundColor(Color.WHITE);
                ROLL.setValueColor(Color.WHITE);
                ROLL.setBorderPaint(Color.WHITE);
                ROLL.setNeedleColor(selectedNight);
                ROLL.setTitleColor(Color.WHITE);
                ROLL.setUnitColor(Color.WHITE);
                ROLL.setBarColor(selectedNight);
                
                //PITCH
                PITCH.setBarBackgroundColor(Color.WHITE);
                PITCH.setValueColor(Color.WHITE);
                PITCH.setBorderPaint(Color.WHITE);
                PITCH.setNeedleColor(selectedNight);
                PITCH.setTitleColor(Color.WHITE);
                PITCH.setUnitColor(Color.WHITE);
                PITCH.setBarColor(selectedNight);
            }
       }
       else{
            HDG.setVisible(false);
            TEMP.setVisible(false);
            ROLL.setVisible(false);
            PITCH.setVisible(false);
       }
    }

    private void setScene2(boolean b) {
        if (b){
            latLabel.setVisible(true);
            latL.setVisible(true);
            lonLabel.setVisible(true);
            lonL.setVisible(true);
            COG.setVisible(true);
            SOG.setVisible(true);
            clat.setVisible(true);
            clon.setVisible(true);
            if (isDay){
                latLabel.setTextFill(Color.BLACK);
                latL.setTextFill(Color.BLACK);
                lonLabel.setTextFill(Color.BLACK);
                lonL.setTextFill(Color.BLACK);
                
                //LAT & LON
                clat.setStroke(Color.BLACK);
                clon.setStroke(Color.BLACK);
                
                //COG
                COG.setBarBackgroundColor(Color.web("0xe0e0e0"));
                COG.setValueColor(Color.BLACK);
                COG.setBorderPaint(Color.BLACK);
                COG.setNeedleColor(selectedDay);
                COG.setTitleColor(Color.BLACK);
                COG.setUnitColor(Color.BLACK);
                COG.setBarColor(selectedDay);
                
                //SOG
                SOG.setBarBackgroundColor(Color.web("0xe0e0e0"));
                SOG.setValueColor(Color.BLACK);
                SOG.setBorderPaint(Color.BLACK);
                SOG.setNeedleColor(selectedDay);
                SOG.setTitleColor(Color.BLACK);
                SOG.setUnitColor(Color.BLACK);
                SOG.setBarColor(selectedDay);
            }
            else{
                latLabel.setTextFill(Color.WHITE);
                latL.setTextFill(Color.WHITE);
                lonLabel.setTextFill(Color.WHITE);
                lonL.setTextFill(Color.WHITE);
                
                //LAT & LON
                clat.setStroke(Color.WHITE);
                clon.setStroke(Color.WHITE);
                
                //COG
                COG.setBarBackgroundColor(Color.WHITE);
                COG.setValueColor(Color.WHITE);
                COG.setBorderPaint(Color.WHITE);
                COG.setNeedleColor(selectedNight);
                COG.setTitleColor(Color.WHITE);
                COG.setUnitColor(Color.WHITE);
                COG.setBarColor(selectedNight);
                
                //SOG
                SOG.setBarBackgroundColor(Color.WHITE);
                SOG.setValueColor(Color.WHITE);
                SOG.setBorderPaint(Color.WHITE);
                SOG.setNeedleColor(selectedNight);
                SOG.setTitleColor(Color.WHITE);
                SOG.setUnitColor(Color.WHITE);
                SOG.setBarColor(selectedNight);
            }
            
        }
        else{
            latLabel.setVisible(false);
            latL.setVisible(false);
            lonLabel.setVisible(false);
            lonL.setVisible(false);
            COG.setVisible(false);
            SOG.setVisible(false);
            clat.setVisible(false);
            clon.setVisible(false);
        }
    }

    private void setScene3(boolean b) {
        if (b){
            TWD.setVisible(true);
            TWS.setVisible(true);
            AWA.setVisible(true);
            AWS.setVisible(true);
            if (isDay){
                //TWD
                TWD.setTickLabelColor(Color.BLACK);
                TWD.setBarBackgroundColor(Color.BLACK);
                TWD.setValueColor(Color.BLACK);
                TWD.setBorderPaint(Color.BLACK);
                TWD.setNeedleColor(selectedDay);
                TWD.setTitleColor(Color.BLACK);
                TWD.setSubTitleColor(Color.BLACK);
                TWD.setMajorTickMarkColor(Color.BLACK);
                TWD.setMediumTickMarkColor(Color.BLACK);
                
                //TWS
                TWS.setBarBackgroundColor(Color.web("0xe0e0e0"));
                TWS.setValueColor(Color.BLACK);
                TWS.setBorderPaint(Color.BLACK);
                TWS.setNeedleColor(selectedDay);
                TWS.setTitleColor(Color.BLACK);
                TWS.setUnitColor(Color.BLACK);
                TWS.setBarColor(selectedDay);
                
                //AWA
                AWA.setBarBackgroundColor(Color.web("0xe0e0e0"));
                AWA.setValueColor(Color.BLACK);
                AWA.setBorderPaint(Color.BLACK);
                AWA.setNeedleColor(selectedDay);
                AWA.setTitleColor(Color.BLACK);
                AWA.setUnitColor(Color.BLACK);
                AWA.setBarColor(selectedDay);
                
                //AWS
                AWS.setBarBackgroundColor(Color.web("0xe0e0e0"));
                AWS.setValueColor(Color.BLACK);
                AWS.setBorderPaint(Color.BLACK);
                AWS.setNeedleColor(selectedDay);
                AWS.setTitleColor(Color.BLACK);
                AWS.setUnitColor(Color.BLACK);
                AWS.setBarColor(selectedDay);
            }
            else{
                //TWD
                TWD.setBarBackgroundColor(Color.WHITE);
                TWD.setTickLabelColor(Color.WHITE);
                TWD.setValueColor(Color.WHITE);
                TWD.setBorderPaint(Color.WHITE);
                TWD.setTitleColor(Color.WHITE);
                TWD.setSubTitleColor(Color.WHITE);
                TWD.setMajorTickMarkColor(Color.WHITE);
                TWD.setMediumTickMarkColor(Color.WHITE);
                
                //TWS
                TWS.setBarBackgroundColor(Color.WHITE);
                TWS.setValueColor(Color.WHITE);
                TWS.setBorderPaint(Color.WHITE);
                TWS.setNeedleColor(selectedNight);
                TWS.setTitleColor(Color.WHITE);
                TWS.setUnitColor(Color.WHITE);
                TWS.setBarColor(selectedNight);
                
                //AWA
                AWA.setBarBackgroundColor(Color.WHITE);
                AWA.setValueColor(Color.WHITE);
                AWA.setBorderPaint(Color.WHITE);
                AWA.setNeedleColor(selectedNight);
                AWA.setTitleColor(Color.WHITE);
                AWA.setUnitColor(Color.WHITE);
                AWA.setBarColor(selectedNight);
                
                //AWS
                AWS.setBarBackgroundColor(Color.WHITE);
                AWS.setValueColor(Color.WHITE);
                AWS.setBorderPaint(Color.WHITE);
                AWS.setNeedleColor(selectedNight);
                AWS.setTitleColor(Color.WHITE);
                AWS.setUnitColor(Color.WHITE);
                AWS.setBarColor(selectedNight);
            }
        }
        else {
            TWD.setVisible(false);
            TWS.setVisible(false);
            AWA.setVisible(false);
            AWS.setVisible(false);
        }
    }

    private void setScene4(boolean b) {
        if (b){
            windDirChart.setVisible(true);
            windIntChart.setVisible(true);
            dirChartSlider.setVisible(true);
            selM.setVisible(true);
            if (isDay){
                windDirChart.getXAxis().setTickLabelFill(Color.BLACK);
                windDirChart.getYAxis().setTickLabelFill(Color.BLACK);
                
                windIntChart.getXAxis().setTickLabelFill(Color.BLACK);
                windIntChart.getYAxis().setTickLabelFill(Color.BLACK);
                
                selM.setTextFill(Color.BLACK);
                
            }
            else{
                windDirChart.getXAxis().setTickLabelFill(Color.WHITE);
                windDirChart.getYAxis().setTickLabelFill(Color.WHITE);
                
                windIntChart.getXAxis().setTickLabelFill(Color.WHITE);
                windIntChart.getYAxis().setTickLabelFill(Color.WHITE);
                
                selM.setTextFill(Color.WHITE);
            }
        }
        else{
            windDirChart.setVisible(false);
            windIntChart.setVisible(false);
            dirChartSlider.setVisible(false);
            selM.setVisible(false);
        }
    }
    
}
