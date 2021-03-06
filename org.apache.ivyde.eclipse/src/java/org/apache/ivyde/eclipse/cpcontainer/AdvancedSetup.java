/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.ivyde.eclipse.cpcontainer;

import org.apache.ivyde.eclipse.retrieve.CustomDownloaderSetup;
import org.apache.ivyde.eclipse.retrieve.RetrieveSetup;

public class AdvancedSetup {

    private boolean resolveBeforeLaunch;

    private boolean useExtendedResolveId;
    
    private boolean useCustomDownloader;
    
    private CustomDownloaderSetup customDownloaderSetup = new CustomDownloaderSetup();

    public AdvancedSetup() {
        // default constructor
    }

    public void set(AdvancedSetup setup) {
        this.resolveBeforeLaunch = setup.resolveBeforeLaunch;
        this.useExtendedResolveId = setup.useExtendedResolveId;
        this.useCustomDownloader = setup.useCustomDownloader;
        this.customDownloaderSetup.set(setup.customDownloaderSetup);
    }

    public boolean isResolveBeforeLaunch() {
        return resolveBeforeLaunch;
    }

    public void setResolveBeforeLaunch(boolean resolveBeforeLaunch) {
        this.resolveBeforeLaunch = resolveBeforeLaunch;
    }

    public boolean isUseExtendedResolveId() {
        return useExtendedResolveId;
    }
    
    public boolean isUseCustomDownloader() {
        return useCustomDownloader;
    }

    public void setUseExtendedResolveId(boolean useExtendedResolveId) {
        this.useExtendedResolveId = useExtendedResolveId;
    }
    
    public void setUseCustomDownloader(boolean useCustomDownloader) {
        this.useCustomDownloader = useCustomDownloader;
    }
    
    public CustomDownloaderSetup getCustomDownloaderSetup() {
        return customDownloaderSetup;
    }

    public void setCustomDownloaderSetup(CustomDownloaderSetup customDownloaderSetup) {
        this.customDownloaderSetup = customDownloaderSetup;
    }
}
