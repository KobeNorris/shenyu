/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;

import java.util.Scanner;

/**
 * The CTest config parameter value injection class.
 */
public class CTestInjector {
    
    private static final Logger LOG = LoggerFactory.getLogger(CTestInjector.class);

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateCParams(final ShenyuConfig shenyuConfig) {
        try {
            File fileObject = new File("InjectionValuePair.txt");
            FileWriter logWriter = new FileWriter("Injector.log");
            Scanner fileReader = new Scanner(fileObject);
            String data;
            while (fileReader.hasNextLine()) {
                data = fileReader.nextLine();
                logWriter.write(data);
            }
            fileReader.close();
            logWriter.close();
        } catch (Exception e) {
            LOG.warn("FileNotFoundException Message: " + e.toString());
        }
    }
}
