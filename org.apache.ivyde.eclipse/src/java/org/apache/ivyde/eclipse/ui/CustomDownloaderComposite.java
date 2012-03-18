package org.apache.ivyde.eclipse.ui;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.ivyde.eclipse.IvyPlugin;
import org.apache.ivyde.eclipse.retrieve.CustomDownloaderSetup;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CustomDownloaderComposite extends Composite {
	
	public static final String TOOLTIP_DOWNLOADER_ARGUMENTS = "Command line parameters to pass to the custom downlodaer";
    
    private PathEditor downloaderPath;
    
    private Text downloaderArgumentsText;
	
	public CustomDownloaderComposite(Composite parent, int style) {
		super(parent, style);
		
        FormLayout layout = new FormLayout();;
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

         FormData formData = new FormData();
         formData.left = new FormAttachment(0, 0);
         formData.top = new FormAttachment(0, 0);
         formData.right = new FormAttachment(100);
         downloaderPath.setLayoutData(formData);
                  
         Label label = new Label(this, SWT.NONE);
         label.setText("Downloader arguments: ");
         
         formData = new FormData();
         formData.left = new FormAttachment(0, 0);
         formData.top = new FormAttachment(downloaderPath, 3);
         label.setLayoutData(formData);
         
         downloaderArgumentsText = new Text(this, SWT.SINGLE | SWT.BORDER);
         downloaderArgumentsText.setToolTipText(TOOLTIP_DOWNLOADER_ARGUMENTS);
         
         formData = new FormData();
         formData.left = new FormAttachment(label);
         formData.top = new FormAttachment(downloaderPath);
         formData.right = new FormAttachment(100);
         downloaderArgumentsText.setLayoutData(formData);
	}
	
	public void init(CustomDownloaderSetup setup) {
        setEnabled(true);
    }
	
	public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
