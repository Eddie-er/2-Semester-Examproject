package ArlaScreens.BLL.Utils;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import java.text.DecimalFormat;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.text.DecimalFormat;

//http://www.java2s.com/Tutorials/Java/javafx.scene.control/Tooltip/0320__Tooltip.install_Node_node_Tooltip_t_.htm
public class TresholdNode extends StackPane {

    private DecimalFormat df = new DecimalFormat("0.0#");

    /**
     * Shows the values in charts, if you hover over them.
     * @param value
     */
    public TresholdNode(Number value) {
        setPrefSize(15, 15);

        final Tooltip tooltip = new Tooltip();
        tooltip.setText(df.format(value));
        tooltip.showDelayProperty().set(Duration.ZERO);
        tooltip.setStyle("-fx-font-size: 15px;");

        Tooltip.install(this, tooltip);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCursor(Cursor.NONE);
                toFront();
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCursor(Cursor.CROSSHAIR);
            }
        });
    }

}
