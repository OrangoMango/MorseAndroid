package com.orangomango.morse;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Insets;

import java.lang.reflect.*;
import android.os.Build;
import android.os.Vibrator;
import android.content.Context;
import javafxports.android.FXActivity;

public class MainApplication extends Application{
	@Override
	public void start(Stage stage) throws Exception{
		if (Build.VERSION.SDK_INT >= 29){
			Method forName = Class.class.getDeclaredMethod("forName", String.class);
			Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
			Class vmRuntimeClass = (Class) forName.invoke(null, "dalvik.system.VMRuntime");
			Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
			Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[] { String[].class} );
			Object vmRuntime = getRuntime.invoke(null);
			setHiddenApiExemptions.invoke(vmRuntime, new String[][] { new String[] { "L" } });
		}
		
		Vibrator vibrator = (Vibrator) FXActivity.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
		
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(200, 100, 200, 75));
		layout.setVgap(10);
		
		Label text = new Label("Insert a text: ");
		TextField field = new TextField();
		Button button = new Button("Get morse");
		button.setOnAction(e -> vibrator.vibrate(Morse.stringToMorse(field.getText()), -1));
		layout.add(text, 0, 0);
		layout.add(field, 0, 1);
		layout.add(button, 0, 2);
		
		stage.setScene(new Scene(layout, bounds.getWidth(), bounds.getHeight()));
		stage.show();
	}
}
