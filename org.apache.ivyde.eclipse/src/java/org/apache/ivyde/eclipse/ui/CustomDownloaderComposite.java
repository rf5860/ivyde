package org.apache.ivyde.eclipse.ui;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.ivyde.eclipse.IvyPlugin;
import org.apache.ivyde.eclipse.cpcontainer.AdvancedSetup;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CustomDownloaderComposite extends Composite {
	
	public static final String TOOLTIP_DOWNLOADER_ARGUMENTS = "Command line parameters to pass to the custom downlodaer";
	
    private Button useCustomDownloader;
    
    private PathEditor downloaderPath;
    
    private Text downloaderArgumentsText;
	
	public CustomDownloaderComposite(Composite parent, int style) {
		super(parent, style);
        GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        setLayout(layout);
        
        useCustomDownloader = new Button(this, SWT.CHECK);
        useCustomDownloader.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
                false));
        useCustomDownloader.setText("Use custom downloader");
        useCustomDownloader
                .setToolTipText("Use an external program to download files during a retrieve");
        
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
         downloaderPath.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
         
         Label label = new Label(this, SWT.NONE);
         label.setText("Downloader arguments:");
         
         downloaderArgumentsText = new Text(this, SWT.SINGLE | SWT.BORDER);
         downloaderArgumentsText.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
         downloaderArgumentsText.setToolTipText(TOOLTIP_DOWNLOADER_ARGUMENTS);
	}
	
	public void init(AdvancedSetup setup) {
        useCustomDownloader.setSelection(setup.isUseCustomDownloader());
        setEnabled(true);
    }
	
	public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        useCustomDownloader.setEnabled(enabled);
    }
}
