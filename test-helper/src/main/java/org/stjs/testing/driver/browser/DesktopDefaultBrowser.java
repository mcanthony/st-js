package org.stjs.testing.driver.browser;

import java.awt.Desktop;
import java.net.URL;

import org.stjs.testing.driver.DriverConfiguration;

public class DesktopDefaultBrowser extends AbstractBrowser {

	public DesktopDefaultBrowser(DriverConfiguration config) {
		super(config);
	}

	@Override
	public void start(long browserId) {
		System.out.println("Starting the default browser ...");

		try {
			if (isRunningOnWindows()) {
				// On windows, we use the awt way to launch the default browser
				Desktop.getDesktop().browse(new URL(getConfig().getServerURL(), getStartPageUri(browserId)).toURI());
			} else {
				// Under linux, we've encountered some strange behavior when using Desktop.browse(),
				// so we'll use xdg-open instead
				new ProcessBuilder("xdg-open", getStartPageUrl(browserId)).start();
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}