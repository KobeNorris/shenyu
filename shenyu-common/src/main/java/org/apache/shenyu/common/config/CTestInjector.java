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

    private static int pathIdx;

    private static String[] paramPath;

    private static String paramValue;

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
            String line;
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                paramPath = line.split("=");
                if(paramPath.length() < 2)
                    paramValue = "null";
                else
                    paramValue = paramPath[1];
                paramPath = paramPath.split(".");
                pathIdx = 0;
                updateShenyuConfig(shenyuConfig);
            }
            fileReader.close();
            logWriter.close();
        } catch (Exception e) {
            LOG.warn("FileNotFoundException Message: " + e.toString());
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateShenyuConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx]) {
            pathIdx++;
            case "switchconfig":
                break;
            case "file":
                break;
            case "exclude":
                break;
            case "health":
                break;
            case "fallback":
                break;
            case "extplugin":
                updateExtPluginConfig(shenyuConfig);
                break;
            case "matchcache":
                break;
            case "scheduler":
                updateSchedulerConfig(shenyuConfig);
                break;
            case "upstreamcheck":
                break;
            case "cross":
                break;
            case "instance":
                break;
            case "ribbon":
                break;
            case "local":
                break;
            case "websocket":
                break;
            case "sharedpool":
                break;
            case "metrics":
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateExtPluginConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx]) {
            pathIdx++;
            case "path":
                shenyuConfig.getExtPlugin().setPath(paramValue == null ? null : paramValue);
                break;
            case "":
                shenyuConfig.getExtPlugin().setEnable(Boolean.valueOf(paramValue));
                break;
            case "":
                shenyuConfig.getExtPlugin().setThreads(paramValue == null ? null : paramValue);
                break;
            case "":
                shenyuConfig.getExtPlugin().setScheduleTime(paramValue == null ? null : Integer.valueOf(paramValue));
                break;
            case "":
                shenyuConfig.getExtPlugin().setScheduledelay(paramValue == null ? null : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    //     /**
    //  * Function for updating the config parameters for ShenyuConfig.
    //  *
    //  * @param shenyuConfig The obejct waiting for update
    //  */
    // public static void updateSchedulerConfig(final ShenyuConfig shenyuConfig) {
    //     switch (paramPath[pathIdx]) {
    //         pathIdx++;
    //         case "":
    //             break;
    //         case "":
    //             break;
    //         case "":
    //             break;
    //         case "":
    //             break;
    //     }
    // }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateSchedulerConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx]) {
            pathIdx++;
            case "enabled":
                shenyuConfig.getScheduler().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "type":
                shenyuConfig.getScheduler().setType(paramValue);
                break;
            case "threads":
                shenyuConfig.getScheduler().setThreads(Integer.valueOf(paramValue));
                break;
            default:
        }
    }
}
