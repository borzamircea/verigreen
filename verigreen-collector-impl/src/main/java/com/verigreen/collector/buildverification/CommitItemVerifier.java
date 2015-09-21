/*******************************************************************************
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *******************************************************************************/
package com.verigreen.collector.buildverification;

import java.util.ArrayList;
import java.util.List;

import com.verigreen.collector.common.log4j.VerigreenLogger;
import com.verigreen.collector.model.CommitItem;
import com.verigreen.collector.spring.CollectorApi;
import com.verigreen.common.concurrency.RuntimeUtils;
import com.verigreen.common.utils.StringUtils;

public class CommitItemVerifier {
    
    private boolean _canceled = false;
    private String _commitItemKey = StringUtils.EMPTY_STRING;
    private List<CommitItem> createCommitItems = new ArrayList<>();
    private static volatile CommitItemVerifier instance = null;
    
	protected CommitItemVerifier() {
	      // Exists only to defeat instantiation.
	}
	
	public static CommitItemVerifier getInstance()
    { 
    	if(instance == null)
    	{
    		synchronized(CommitItemVerifier.class)
    		{ 
    			if(instance == null)
    			{ 
    				instance = new CommitItemVerifier();  
    			}
    		}
    	}
    	return instance; 
    }
    
    public void verify(final CommitItem item) {

       _commitItemKey = item.getKey();

        
        createCommitItems.add(item);
    }
    
    public List<CommitItem> getCommitItems(){
		return this.createCommitItems;
	}
 
	public void cancel() {
        
        _canceled = true;
        if (!StringUtils.isNullOrEmpty(_commitItemKey)) {
            CommitItem commitItem = CollectorApi.getCommitItemContainer().get(_commitItemKey);
            VerigreenLogger.get().log(
                    getClass().getName(),
                    RuntimeUtils.getCurrentMethodName(),
                    String.format("Cancelling verification of %s...", commitItem));
            CollectorApi.getJenkinsVerifier().stop(
                    CollectorApi.getVerificationJobName(),
                    String.valueOf(commitItem.getBuildNumber()));
        }
    }
    
    public boolean isCanceled() {
        
        return _canceled;
    }
    
  
}
