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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
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
            File fileObject = new File("./src/main/java/org/apache/shenyu/common/config/InjectionValuePair.txt");
            Scanner fileReader = new Scanner(fileObject);
            String line;
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                paramPath = line.split("=");
                if (paramPath.length < 2) {
                    paramValue = "null";
                } else {
                    paramValue = paramPath[1];
                }
                paramPath = paramPath[0].split("\\.");
                pathIdx = 0;
                updateShenyuConfig(shenyuConfig);
            }
            fileReader.close();
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
        switch (paramPath[pathIdx++]) {
            case "FileConfig":
                updateFileConfig(shenyuConfig);
                break;
            case "ExcludePath":
                updateExcludePathConfig(shenyuConfig);
                break;
            case "Health":
                updateHealthConfig(shenyuConfig);
                break;
            case "FallbackPath":
                updateFallbackPathConfig(shenyuConfig);
                break;
            case "ExtPlugin":
                updateExtPluginConfig(shenyuConfig);
                break;
            case "MatchCache":
                updateMatchCacheConfig(shenyuConfig);
                break;
            case "Scheduler":
                updateSchedulerConfig(shenyuConfig);
                break;
            case "UpstreamCheck":
                updateUpstreamCheckConfig(shenyuConfig);
                break;
            case "CrossFilterConfig":
                updateCrossFilterConfig(shenyuConfig);
                break;
            case "InstanceConfig":
                updateInstanceConfig(shenyuConfig);
                break;
            case "RibbonConfig":
                updateRibbonConfig(shenyuConfig);
                break;
            case "Local":
                updateLocal(shenyuConfig);
                break;
            case "WebsocketConfig":
                updateWebsocketConfig(shenyuConfig);
                break;
            case "SharedPool":
                updateSharedPoolConfig(shenyuConfig);
                break;
            case "MetricsConfig":
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateFileConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getFile().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "maxSize":
                shenyuConfig.getFile().setMaxSize("null".equals(paramValue) ? 0 : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateExcludePathConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getExclude().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "paths":
                shenyuConfig.getExclude().setPaths("null".equals(paramValue) ? null : new ArrayList<>());
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateHealthConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getHealth().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "paths":
                shenyuConfig.getHealth().setPaths("null".equals(paramValue) ? null : new ArrayList<>());
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateFallbackPathConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getFallback().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "paths":
                shenyuConfig.getFallback().setPaths("null".equals(paramValue) ? null : new ArrayList<>());
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateMatchCacheConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getMatchCache().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "maxFreeMemory":
                shenyuConfig.getMatchCache().setMaxFreeMemory("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateUpstreamCheckConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "SwitchConfig":
                updateSwitchConfig(shenyuConfig);
                break;
            case "enabled":
                shenyuConfig.getUpstreamCheck().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "timeout":
                shenyuConfig.getUpstreamCheck().setTimeout("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "healthyThreshold":
                shenyuConfig.getUpstreamCheck().setHealthyThreshold("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "unhealthyThreshold":
                shenyuConfig.getUpstreamCheck().setUnhealthyThreshold("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "interval":
                shenyuConfig.getUpstreamCheck().setInterval("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "printEnabled":
                shenyuConfig.getUpstreamCheck().setPrintEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "printInterval":
                shenyuConfig.getUpstreamCheck().setPrintInterval("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateCrossFilterConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getCross().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "allowedHeaders":
                shenyuConfig.getCross().setAllowedHeaders("null".equals(paramValue) ? "" : paramValue);
                break;
            case "allowedAnyOrigin":
                shenyuConfig.getCross().setAllowedAnyOrigin("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "maxAge":
                shenyuConfig.getCross().setMaxAge("null".equals(paramValue) ? "0" : paramValue);
                break;
            case "allowCredentials":
                shenyuConfig.getCross().setAllowCredentials("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "allowedOrigin":
                updateAllowedOriginConfig(shenyuConfig);
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateAllowedOriginConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "spacer":
                shenyuConfig.getCross().getAllowedOrigin().setSpacer("null".equals(paramValue) ? "" : paramValue);
                break;
            case "domain":
                shenyuConfig.getCross().getAllowedOrigin().setDomain("null".equals(paramValue) ? "" : paramValue);
                break;
            case "prefixes":
                shenyuConfig.getCross().getAllowedOrigin().setPrefixes("null".equals(paramValue) ? null : new HashSet<>());
                break;
            case "origins":
                shenyuConfig.getCross().getAllowedOrigin().setOrigins("null".equals(paramValue) ? null : new HashSet<>());
                break;
            case "originRegex":
                shenyuConfig.getCross().getAllowedOrigin().setOriginRegex("null".equals(paramValue) ? "" : paramValue);
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateInstanceConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getInstance().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "registerType":
                shenyuConfig.getInstance().setRegisterType("null".equals(paramValue) ? "" : paramValue);
                break;
            case "serverLists":
                shenyuConfig.getInstance().setServerLists("null".equals(paramValue) ? "" : paramValue);
                break;
            case "props":
                shenyuConfig.getInstance().setProps("null".equals(paramValue) ? null : new Properties());
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateRibbonConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "serverListRefreshInterval":
                shenyuConfig.getRibbon().setServerListRefreshInterval("null".equals(paramValue) ? 0 : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateLocal(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getLocal().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "sha512Key":
                shenyuConfig.getLocal().setSha512Key("null".equals(paramValue) ? "" : paramValue);
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateWebsocketConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "maxFramePayloadSize":
                shenyuConfig.getWebsocket().setMaxFramePayloadSize("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateSharedPoolConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enable":
                shenyuConfig.getSharedPool().setEnable("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "prefix":
                shenyuConfig.getSharedPool().setPrefix("null".equals(paramValue) ? null : paramValue);
                break;
            case "corePoolSize":
                shenyuConfig.getSharedPool().setCorePoolSize("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "maximumPoolSize":
                shenyuConfig.getSharedPool().setMaximumPoolSize("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "keepAliveTime":
                shenyuConfig.getSharedPool().setKeepAliveTime("null".equals(paramValue) ? null : Long.valueOf(paramValue));
                break;
            case "maxWorkQueueMemory":
                shenyuConfig.getSharedPool().setMaxWorkQueueMemory("null".equals(paramValue) ? null : 3000L);
                break;
            case "maxFreeMemory":
                shenyuConfig.getSharedPool().setMaxFreeMemory("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateMetricsConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getMetrics().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "name":
                shenyuConfig.getMetrics().setName("null".equals(paramValue) ? null : paramValue);
                break;
            case "host":
                shenyuConfig.getMetrics().setHost("null".equals(paramValue) ? null : paramValue);
                break;
            case "port":
                shenyuConfig.getMetrics().setPort("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "jmxConfig":
                shenyuConfig.getMetrics().setJmxConfig("null".equals(paramValue) ? null : paramValue);
                break;
            case "props":
                shenyuConfig.getMetrics().setProps("null".equals(paramValue) ? null : new Properties());
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
        switch (paramPath[pathIdx++]) {
            case "path":
                shenyuConfig.getExtPlugin().setPath("null".equals(paramValue) ? "" : paramValue);
                break;
            case "enabled":
                shenyuConfig.getExtPlugin().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "threads":
                shenyuConfig.getExtPlugin().setThreads("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "scheduleTime":
                shenyuConfig.getExtPlugin().setScheduleTime("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            case "scheduleDelay":
                shenyuConfig.getExtPlugin().setScheduleDelay("null".equals(paramValue) ? null : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateSchedulerConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "enabled":
                shenyuConfig.getScheduler().setEnabled("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            case "type":
                shenyuConfig.getScheduler().setType("null".equals(paramValue) ? "" : paramValue);
                break;
            case "threads":
                shenyuConfig.getScheduler().setThreads("null".equals(paramValue) ? 16 : Integer.valueOf(paramValue));
                break;
            default:
        }
    }

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateSwitchConfig(final ShenyuConfig shenyuConfig) {
        switch (paramPath[pathIdx++]) {
            case "local":
                shenyuConfig.getSwitchConfig().setLocal("null".equals(paramValue) ? false : Boolean.valueOf(paramValue));
                break;
            default:
        }
    }
}
