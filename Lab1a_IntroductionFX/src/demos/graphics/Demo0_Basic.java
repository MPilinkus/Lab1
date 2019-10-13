/****************************************************************
 * Šioje klasėje pateikamas įvadas į JavaFX grafiką
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo0_Basic extends BaseGraphics {
        
    // pradžioje brėšime horizontalias ir vertikalias linijas per centrą
    private void drawHVtoCenter() {  
        gc.setLineWidth(3);       // brėžimo linijos plotis
        gc.setStroke(Color.RED);  // ir tos linijos spalva
        gc.strokeLine(0, canvasH / 2, canvasW, canvasH / 2);
        gc.strokeLine(canvasW / 2, 0, canvasW / 2, canvasH);
    }
    // po to brėšime įstrižaines per centrą
    private void drawXtoCenter() {
        gc.setLineWidth(4);         // brėžimo linijos plotis
        gc.setStroke(Color.GREEN);  // ir tos linijos spalva
        gc.strokeLine(0, 0, canvasW, canvasH);
        gc.strokeLine(0, canvasH, canvasW, 0);
    }  
// UŽDUOTIS_1: plonomis linijomis su žingsniu step=50 nubrėžkite tinklelį
    private void drawGrid1() {
        double step = 50;
        gc.setLineWidth(0.15);         // linijos plotis galimai mažesnis
        for(double u = step; u < Math.max(canvasW, canvasH); u += step) {
            gc.setStroke(Color.BLACK);
            gc.strokeLine(0, u, canvasW, u);   // horizontalios linijos
            gc.strokeLine(u, 0, u, canvasH);   // vertikalios linijos
        }
    }
// https://examples.javacodegeeks.com/desktop-java/javafx/javafx-canvas-example/    
    private void drawExamples1() {
        double lw = 3.0;
        gc.setLineWidth(lw);        // brėžimo linijos plotis
        gc.setStroke(Color.BLUE);   // ir tos linijos spalva
        gc.setFill(Color.RED);      // dažymo spalva figūroms
        int x=10, y=10, w=80, h=50, 
            d=20, ax=10, ay=20; // d-tarpas tarp elementų, ax,ay-apvalinimai
        gc.strokeRoundRect(x, y, w, h, ax, ay);
        x+=w+d; // sekantis į dešinę
        gc.fillRoundRect(  x, y, w, h, ax, ay);
        gc.setLineWidth(0.5);
        gc.strokeText("Wolf and Bear", x, y+h);
        //-------------------
        gc.setLineWidth(2*lw);    // dvigubai pastoriname liniją      
        gc.setFill(Color.YELLOW);
        x = 10;    // grįžtame horizontaliai
        y += h+d;  // ir pereiname žemyn
        gc.strokeOval(x, y, w, h);
        x += w+d; // sekantis į dešinę
        gc.fillOval( x, y, h, w);
        x = 10;     // grįžtame horizontaliai
        y += h+2*d; // ir pereiname žemyn ir brėžiame lankus
        gc.strokeArc  (x, y, w, w, 30,  90, ArcType.ROUND);
        gc.fillArc(x+w+d, y, w, w, 45, 180, ArcType.OPEN);
    }  
    private void drawUnicode(){
        // išbandykite ir kitus simbolius
        // https://en.wikipedia.org/wiki/List_of_Unicode  skyrius 31
        StringBuilder sb = new StringBuilder();
        for(char ch = '\u2654'; ch <= '\u265F'; ch++)
            sb.append(ch);
        gc.setFont(Font.font("Lucida Console", 36));
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.strokeText(sb.toString(), 50, 350);
//        for(char ch = '\u2554'; ch <= '\u255F'; ch++)
//            sb.append(ch);
//        gc.setFont(Font.font("Lucida Console", 36));
//        gc.setLineWidth(1);
//        gc.setStroke(Color.BLACK);
//        gc.strokeText(sb.toString(), 50, 350);
    }
// UŽDUOTIS_2: nubrėžkite polilinijas ir poligonus   
// https://www.tutorialspoint.com/javafx/2dshapes_polygon    
    private void drawExamples2() {
        gc.setLineWidth(2.5);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.WHITE);
        gc.fillPolygon(new double[] {50, 40, 40, 50}, new double[] {30, 30, 20, 20}, 4);
        gc.strokePolygon(new double[]{100, 150, 100}, new double[] {20, 30, 70}, 3);
        gc.strokePolyline(new double[] {200, 210, 250}, new double[] {40, 30, 60}, 3);
    }
// UŽDUOTIS_3: nubrėžkite taisyklingus 3, 4, 5, ..., 9-kampius  
    private void drawExamples3() {    
        // Nurodymas: parašykite funkciją, kuri paskaičiuoja skaičių masyvus
        // kuriuose surašomos taisyklingo daugiakampio koordinatės
        gc.setLineWidth(2.5);
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.WHITE);
        for(int i = 3; i<=9; i++){
            double centerX = 65 * (i-3) + 40;
            double centerY = 150;
            double radius = 30;
            double[] xPoints = new double[i];
            double[] yPoints = new double[i];
            for (int j = 0; j < i; j++) {
                double x = centerX + radius * Math.sin(j * 2 * Math.PI / i);
                double y = centerY + radius * Math.cos(j * 2 * Math.PI / i);
                xPoints[j] = x;
                yPoints[j] = y;
            }
            gc.strokePolygon(xPoints, yPoints, i);
        }
    }
// UŽDUOTIS_4: nubrėžkite žiedus https://en.wikipedia.org/wiki/Olympic_symbols
    private void drawOlympicRings() {
        gc.setLineWidth(7.0);
        gc.setStroke(Color.BLUE);
        int x = 10, y = 10, w = 80, h = 80, d = 20;
        gc.strokeOval(x, y, w, h);
        gc.setStroke(Color.YELLOW);
        x += 50;
        y += 40;
        gc.strokeOval(x, y, w, h);
        gc.setStroke(Color.BLACK);
        x += 50;
        y -= 40;
        gc.strokeOval(x, y, w, h);
        gc.setStroke(Color.GREEN);
        x += 50;
        y += 40;
        gc.strokeOval(x, y, w, h);
        gc.setStroke(Color.RED);
        x += 50;
        y -= 40;
        gc.strokeOval(x, y, w, h);
    }
// UŽDUOTIS_5: pasirinktinai nubrėžkite savo tematiką:
// kelių valstybių sudėtingesnes vėliavas http://flagpedia.net/index
// pvz: Pietų Afrikos, Makedonijos, Norvegijos, Graikijos, Britanijos, ...
// arba futbolo, krepšinio ar ledo ritulio aikštes su žaidėjų pozicijomis  
    private void drawFreeThema() {
        gc.setLineWidth(0);
        //Italy
        gc.setFill(Color.rgb(0,146,70));
        gc.fillPolygon(new double[] {10, 60, 60, 10}, new double[] {10, 10, 90, 90}, 4);
        gc.setFill(Color.rgb(255,255,255));
        gc.fillPolygon(new double[] {60, 110, 110, 60}, new double[] {10, 10, 90, 90}, 4);
        gc.setFill(Color.rgb(206,43,45));
        gc.fillPolygon(new double[] {110, 160, 160, 110}, new double[] {10, 10, 90, 90}, 4);
        //Japan
        gc.setFill(Color.rgb(255,255,255));
        gc.fillPolygon(new double[] {10, 160, 160, 10}, new double[] {100, 100, 180, 180}, 4);
        gc.setFill(Color.RED);
        gc.fillOval(60, 115, 50, 50);
    }    
// kontrolinės užduotys gynimo metu:
// įvairios vėliavos, tiesiog tokios sudėtinės figūros kaip namukas,
// medis, eglė, sniego senio siluetas :-) ir t.t.    
    @Override
    public void createControls(){
        addButton("clear", e -> clearCanvas()); 
        addButton("grid",  e -> baseGrid());
        addButton("grid1",  e -> drawGrid1());
        addButton("HVC",   e -> drawHVtoCenter());
        addButton("XC",    e -> drawXtoCenter());
        addButton("pvz1",  e -> drawExamples1());
        addButton("UniCode",  e -> drawUnicode());
        addButton("PolygonPolyline", e -> drawExamples2());
        addButton("Regular polygons", e -> drawExamples3());
        addButton("Olympic", e -> drawOlympicRings());
        addButton("Flags", event -> drawFreeThema());
        addNewHBox();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Braižymai Canvas lauke (KTU IF)");        
        setCanvas(Color.CYAN.brighter(), 600, 400);
        super.start(stage);
    }       
    public static void main(String[] args) {
        launch(args);
    }    
}
