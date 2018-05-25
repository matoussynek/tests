package deliverable.pkg2;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import net.sf.marineapi.nmea.sentence.HDGSentence;
import net.sf.marineapi.nmea.sentence.MDASentence;
import net.sf.marineapi.nmea.sentence.MWVSentence;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.XDRSentence;
import net.sf.marineapi.nmea.util.Measurement;
import net.sf.marineapi.nmea.util.Position;

/**
 *
 * @author Matouš Synek & Manuel Roselló
 */
public class Model {
    //======================================================
    //== This class implements singleton. It ensures that only one instance 
    //== of the class model is created. Thus, it is possible to access to the 
    //== same instance from every place in the project.
    
    private static Model model;

    private Model() {
    }
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
    
    
    //===================================================================
    // ALERT. reader object, of the class SentenceReader, it is executed in
    // a seconday thread, so it is not possible to modify graphic node properties 
    // from any method of it
    
    private SentenceReader reader;
    
     //==================================================================
    // Add all needed properties you need (one for each sentence type). 
    // In the main thread (main controller)you can add all the needed listeners 
    // to these properties which modify the interface nodes.    

    //True Wind Dir
    private final DoubleProperty TWD = new SimpleDoubleProperty();
    public DoubleProperty TWDProperty() {
        return TWD;
    }
    //Wind intensity
    private final DoubleProperty TWS = new SimpleDoubleProperty();
    public DoubleProperty TWSProperty() {
        return TWS;
    }
    
    //Heading - Magnetic compass
    private final DoubleProperty HDG = new SimpleDoubleProperty();
    public DoubleProperty HDGProperty() {
        return HDG;
    }
    
    //Temperature
    private final DoubleProperty TEMP = new SimpleDoubleProperty();
    public DoubleProperty TEMPProperty() {
        return TEMP;
    }
    
    // Position -- GPS
    private final ObjectProperty<Position> GPS = new SimpleObjectProperty();
    public ObjectProperty<Position> GPSProperty() {
        return GPS;
    }
    
    // COG -- course GPS
    private final DoubleProperty COG = new SimpleDoubleProperty();
    public DoubleProperty COGProperty() {
        return COG;
    }
    // SOG -- speed  GPS
    private final DoubleProperty SOG = new SimpleDoubleProperty();
    public DoubleProperty SOGProperty() {
        return SOG;
    }
    private final DoubleProperty ROLL = new SimpleDoubleProperty();
    public DoubleProperty ROLLProperty() {
        return ROLL;
    }
    private final DoubleProperty PITCH = new SimpleDoubleProperty();
    public DoubleProperty PITCHProperty() {
        return PITCH;
    }
    private final DoubleProperty AWS = new SimpleDoubleProperty();
    public DoubleProperty AWSProperty(){
        return AWS;
    }
    private final DoubleProperty AWA = new SimpleDoubleProperty();
    public DoubleProperty AWAProperty(){
        return AWA;
    }
    

    
    //more...

    //====================================================================
    //Add all the sentenceListener needed, one for each sentecne type we are interesting in.
    //
    class HDGSentenceListener
            extends AbstractSentenceListener<HDGSentence> {

        @Override
        public void sentenceRead(HDGSentence sentence) {
            // anadimos el codigo necesario para guardar la información de la sentence    
            HDG.set(sentence.getHeading());
        }
    }
    class MWVSentenceListener
            extends AbstractSentenceListener<MWVSentence>{
        @Override
        public void sentenceRead(MWVSentence sentence){
            AWS.set(sentence.getSpeed());
            AWA.set(sentence.getAngle());
        }
    }
 


    class MDASentenceListener
            extends AbstractSentenceListener<MDASentence> {

        @Override
        public void sentenceRead(MDASentence sentence) {
            // anadimos el codigo necesario para guardar la información de la sentence 
            TWD.set(sentence.getTrueWindDirection());
            TWS.set(sentence.getWindSpeedKnots());
            TEMP.set(sentence.getAirTemperature());
   
        }
    }
    
    class XDRSentenceListener
            extends AbstractSentenceListener<XDRSentence>{
        
        @Override
        public void sentenceRead(XDRSentence sentence){
            List<Measurement> mess = sentence.getMeasurements();
            for (Measurement m : mess){
                if (m.getName().equals("ROLL")){
                    ROLL.set(m.getValue());
                }
                if (m.getName().equals("PTCH")){
                    PITCH.set(m.getValue());
                }
            }
        }
    }
        

    class RMCSentenceListener
            extends AbstractSentenceListener<RMCSentence> {

        @Override
        public void sentenceRead(RMCSentence sentence) {
            GPS.set(sentence.getPosition());
            COG.set(sentence.getCourse());
            SOG.set(sentence.getSpeed());
        }
    }
    
    
    // more....
    
//=====================================================================
// this method initializes the reader and starts it

    public void addSentenceReader(File file) throws FileNotFoundException {

        InputStream stream = new FileInputStream(file);
        if (reader != null) {  // esto ocurre si ya estamos leyendo un fichero
            reader.stop();
        }
        reader = new SentenceReader(stream);
 
        //==================================================================
        //============= Registra todos los sentenceListener que necesites
        HDGSentenceListener hdg = new HDGSentenceListener();
        reader.addSentenceListener(hdg);
        
        MDASentenceListener mda = new MDASentenceListener();
        reader.addSentenceListener(mda);
        
        XDRSentenceListener xdr = new XDRSentenceListener();
        reader.addSentenceListener(xdr);

        RMCSentenceListener rmd = new RMCSentenceListener();
        reader.addSentenceListener(rmd);
        
        MWVSentenceListener mwv = new MWVSentenceListener();
        reader.addSentenceListener(mwv);
                
         //===============================================================

         //===============================================================
         //==This exceptionListener captures all no treated sentences, and informs about them by console 
         reader.setExceptionListener(e->{});//System.out.println(e.getMessage());});
         
         //================================================================
         //======== Starts the SentenceReader, so it begins to read the sentences           
        reader.start();
    }
}
