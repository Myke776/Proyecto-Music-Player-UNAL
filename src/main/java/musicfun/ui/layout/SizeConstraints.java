package musicfun.ui.layout;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;

public class SizeConstraints {
    private final DoubleProperty minWidth;
    private final DoubleProperty prefWidth;
    private final DoubleProperty maxWidth;
    private final DoubleProperty minHeight;
    private final DoubleProperty prefHeight;
    private final DoubleProperty maxHeight;


    public SizeConstraints() {
        this.minWidth = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        this.prefWidth = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        this.maxWidth = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        this.minHeight = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        this.prefHeight = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
        this.maxHeight = new SimpleDoubleProperty(Region.USE_COMPUTED_SIZE);
    }

    public SizeConstraints(double minWidth, double prefWidth, double maxWidth,
                           double minHeight, double prefHeight, double maxHeight) {
        this.minWidth = new SimpleDoubleProperty(minWidth);
        this.prefWidth = new SimpleDoubleProperty(prefWidth);
        this.maxWidth = new SimpleDoubleProperty(maxWidth);
        this.minHeight = new SimpleDoubleProperty(minHeight);
        this.prefHeight = new SimpleDoubleProperty(prefHeight);
        this.maxHeight = new SimpleDoubleProperty(maxHeight);
    }

	public SizeConstraints(double width, double height) {
        this(width, width, width, height, height, height);
    }

	public SizeConstraints(double value){
		this(value, value);
	}

    public double getMinWidth() { return minWidth.get(); }
    public void setMinWidth(double value) { minWidth.set(value); }
    public DoubleProperty minWidthProperty() { return minWidth; }

    public double getPrefWidth() { return prefWidth.get(); }
    public void setPrefWidth(double value) { prefWidth.set(value); }
    public DoubleProperty prefWidthProperty() { return prefWidth; }

    public double getMaxWidth() { return maxWidth.get(); }
    public void setMaxWidth(double value) { maxWidth.set(value); }
    public DoubleProperty maxWidthProperty() { return maxWidth; }

    public double getMinHeight() { return minHeight.get(); }
    public void setMinHeight(double value) { minHeight.set(value); }
    public DoubleProperty minHeightProperty() { return minHeight; }

    public double getPrefHeight() { return prefHeight.get(); }
    public void setPrefHeight(double value) { prefHeight.set(value); }
    public DoubleProperty prefHeightProperty() { return prefHeight; }

    public double getMaxHeight() { return maxHeight.get(); }
    public void setMaxHeight(double value) { maxHeight.set(value); }
    public DoubleProperty maxHeightProperty() { return maxHeight; }

    public void setPrefSize(double width, double height) {
        setPrefWidth(width);
        setPrefHeight(height);
    }

    public void setMinSize(double width, double height) {
        setMinWidth(width);
        setMinHeight(height);
    }

    public void setMaxSize(double width, double height) {
        setMaxWidth(width);
        setMaxHeight(height);
    }

    public void applyTo(Region region) {
        region.minWidthProperty().bind(minWidth);
        region.prefWidthProperty().bind(prefWidth);
        region.maxWidthProperty().bind(maxWidth);
        region.minHeightProperty().bind(minHeight);
        region.prefHeightProperty().bind(prefHeight);
        region.maxHeightProperty().bind(maxHeight);
    }

    public SizeConstraints copy() {
        return new SizeConstraints(
            getMinWidth(), getPrefWidth(), getMaxWidth(),
            getMinHeight(), getPrefHeight(), getMaxHeight()
        );
    }

    @Override
    public String toString() {
        return String.format(
            "SizeConstraints[minWidth=%.1f, prefWidth=%.1f, maxWidth=%.1f, " +
            "minHeight=%.1f, prefHeight=%.1f, maxHeight=%.1f]",
            getMinWidth(), getPrefWidth(), getMaxWidth(),
            getMinHeight(), getPrefHeight(), getMaxHeight()
        );
    }
}