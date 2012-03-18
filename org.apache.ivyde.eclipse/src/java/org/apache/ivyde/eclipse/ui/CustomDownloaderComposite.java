package org.apache.ivyde.eclipse.ui;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.ivyde.eclipse.IvyPlugin;
import org.apache.ivyde.eclipse.retrieve.CustomDownloaderSetup;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CustomDownloaderComposite extends Composite {
	
	public static final String TOOLTIP_DOWNLOADER_ARGUMENTS = "Command line parameters to pass to the custom downlodaer";
    
    private PathEditor downloaderPath;
    
    private Text downloaderArgumentsText;
	
	public CustomDownloaderComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout layout = new GridLayout(2, false);
        setLayout(layout);
        
        downloaderPath = new PathEditor(this, SWT.NONE, "Downloader path:", null, "*.exe") {
            protected void setFile(String f) {
                 try {
                     getText().setText(new File(f).toURI().toURL().toExternalForm());
                     textUpdated();
                 } catch (MalformedURLException ex) {
                     IvyPlugin.log(IStatus.ERROR, "The file got from the file browser has not a valid URL", ex);
                 }
             }
         };
         
         downloaderPath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
                  
         Label label = new Label(this, SWT.NONE);
         label.setText("Downloader arguments: ");
         
         downloaderArgumentsText = new Text(this, SWT.SINGLE | SWT.BORDER | SWT.FILL);
         downloaderArgumentsText.setToolTipText(TOOLTIP_DOWNLOADER_ARGUMENTS);
	}
	
	public void init(CustomDownloaderSetup setup) {
		downloaderArgumentsText.setText(setup.getDownloaderArguments());
		downloaderPath.getText().setText(setup.getDownloaderPath());
        setEnabled(true);
    }
	
	public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        downloaderArgumentsText.setEnabled(enabled);
        downloaderPath.setEnabled(enabled);
    }
	
	public CustomDownloaderSetup getCustomDownloaderSetup() {
		CustomDownloaderSetup customDownloaderSetup = new CustomDownloaderSetup();
		customDownloaderSetup.setDownloaderArguments(downloaderArgumentsText.getText());
		customDownloaderSetup.setDownloaderPath(downloaderPath.getText().getText());
		
		return customDownloaderSetup;
	}
}
