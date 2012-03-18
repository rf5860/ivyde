package org.apache.ivyde.eclipse.retrieve;

public class CustomDownloaderSetup {
	private String downloaderPath = "";
	private String downloaderArguments = "";
	
    public String getDownloaderPath() {
		return downloaderPath;
	}

	public void setDownloaderPath(String downloaderPath) {
		this.downloaderPath = downloaderPath;
	}

	public String getDownloaderArguments() {
		return downloaderArguments;
	}

	public void setDownloaderArguments(String downloaderArguments) {
		this.downloaderArguments = downloaderArguments;
	}
	
	public void set(CustomDownloaderSetup setup) {
        this.downloaderPath = setup.downloaderPath;
        this.downloaderArguments = setup.downloaderArguments;
    }
}
