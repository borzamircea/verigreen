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
package com.verigreen.buildverification;

import org.easymock.EasyMock;

import com.verigreen.collector.buildverification.BuildVerifier;

public class JenkinsVerifierMockFactory {
    
    private static BuildVerifier _buildVerifier = EasyMock.createNiceMock(BuildVerifier.class);
    
    public static BuildVerifier getMock() {
        
        return _buildVerifier;
    }
<<<<<<< HEAD
    
/*    public static void setPassedMock() {
        
        setMock(VerificationStatus.PASSED);
    }
    
    public static void setFailedMock() {
        
        setMock(VerificationStatus.FAILED);
    }
    
/*    public static void setHangMock() {
        
        reset();
        EasyMock.expect(
                _buildVerifier.BuildAndVerify(
                        EasyMock.anyString(),
                        EasyMock.anyString(),
                        EasyMock.anyString(),
                        EasyMock.anyObject(BuildDataCallback.class))).andAnswer(
                new IAnswer<BuildVerificationResult>() {
                    
                    @Override
                    public BuildVerificationResult answer() throws Throwable {
                        
                        Thread.sleep(400);
                        
                        return null;
                    }
                });
        replay();
    }
    
    private static void setMock(VerificationStatus status) {
        
        reset();
        try {
            EasyMock.expect(
                    _buildVerifier.BuildAndVerify(
                            EasyMock.anyString(),
                            EasyMock.anyString(),
                            EasyMock.anyString(),
                            EasyMock.anyObject(BuildDataCallback.class))).andReturn(
                    new BuildVerificationResult(100, new URI(url), status)).atLeastOnce();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        replay();
    }
    */
=======
>>>>>>> 03e4f4d046ae521bd65d1ea10e9032cea2563215

    public static void replay() {
        
        EasyMock.replay(_buildVerifier);
    }
    
    public static void reset() {
        
        EasyMock.reset(_buildVerifier);
    }
}
