import com.bric.colorpicker.ColorPicker;
import uibooster.components.SimpleBlockingDialog;
import uibooster.model.DialogClosingState;

import java.awt.*;
//Class borrowed from UiBooster processing library
//This class had to be modified to work in pure Java, which is why we have it
public class ColorPickerDialog {

	public static Color showColorPicker(String message, String title, String iconPath) {

		ColorPicker picker = createColorPicker();

		SimpleBlockingDialog dialog = new SimpleBlockingDialog(picker);
		DialogClosingState closingState = dialog.showDialog(message, title, iconPath);

		return closingState.isClosedByUser() ? null : picker.getColor();
	}

	public static ColorPicker createColorPicker() {
		ColorPicker picker = new ColorPicker(false, false);
		picker.setColor(new Color(219, 185, 47));
		picker.setRGBControlsVisible(false);
		picker.setHexControlsVisible(false);
		picker.setPreviewSwatchVisible(false);
		picker.setHSBControlsVisible(false);
		picker.setVisible(true);
		return picker;
	}

}