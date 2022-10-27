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

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;
import java.util.Scanner;

/**
 * The CTest config parameter value injection class.
 */
public class CTestInjector {
    
    private static final Logger LOG = LoggerFactory.getLogger(CTestInjector.class);

    private static String[] paramPath;
    
    private static String paramValue;

    private static int pathIdx;

    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateCParams(final ShenyuConfig shenyuConfig) {
        try {
            File fileObject = new File("./src/main/java/org/apache/shenyu/common/config/InjectionValuePair.txt");
            Scanner fileReader = new Scanner(fileObject);
            String[] strs;
            String data;
            String line;
            
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                strs = line.split("=");
                paramPath = strs[0].split("\\.");
                paramValue = strs[1];
                pathIdx = 0;
                switch (paramPath[pathIdx]) {
                    case "Scheduler":
                        updateSchedulerConfig(shenyuConfig);
                        break;
                    case "ExtPlugin":
                        updateExtPluginConfig(shenyuConfig);
                        break;
                    case "MatchCache":
                        updateMatchCacheConfig(shenyuConfig);
                        break;
                    case "ExcludePath":
                        updateExcludePathConfig(shenyuConfig);
                        break;
                    case "FallbackPath":
                        updateFallbackPathConfig(shenyuConfig);
                        break;
                    case "Health":
                        updateHealthConfig(shenyuConfig);
                        break;
                    case "FileConfig":
                        updateFileConfig(shenyuConfig);
                        break;
                    case "SwitchConfig":
                        updateSwitchConfig(shenyuConfig);
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
                        updateLocalConfig(shenyuConfig);
                        break;
                    case "WebsocketConfig":
                        updateWebsocketConfig(shenyuConfig);
                        break;
                    case "SharedPool":
                        updateSharedPoolConfig(shenyuConfig);
                        break;
                    case "MetricsConfig":
                        updateMetricsConfig(shenyuConfig);
                        break;
                    default:
                        updateShenyuConfig(shenyuConfig);
                }
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

    }
    
    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateSchedulerConfig(final ShenyuConfig shenyuConfig) {
        pathIdx++;
        switch (paramPath[pathIdx]) {
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
    
    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateExtPluginConfig(final ShenyuConfig shenyuConfig) {
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "path":
                shenyuConfig.getExtPlugin().setPath(paramValue);
                break;
            case "enabled":
                shenyuConfig.getExtPlugin().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "threads":
                shenyuConfig.getExtPlugin().setThreads(Integer.valueOf(paramValue));
                break;
            case "scheduletime":
                if ("null".equals(paramValue)) {
                    shenyuConfig.getExtPlugin().setScheduleTime(null);
                } else {
                    shenyuConfig.getExtPlugin().setScheduleTime(Integer.valueOf(paramValue));
                }
                break;
            case "scheduledelay":
                shenyuConfig.getExtPlugin().setScheduleDelay(Integer.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getMatchCache().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "maxfreememory":
                shenyuConfig.getMatchCache().setMaxFreeMemory(Integer.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getExclude().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "paths":
                String[] strs = paramValue.split(",");
                List<String> paths = new LinkedList<>();
                for (String str : strs) {
                    paths.add(str);
                }
                shenyuConfig.getExclude().setPaths(paths);
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getFallback().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "paths":
                String[] strs = paramValue.split(";");
                List<String> paths = new LinkedList<>();
                for (String str : strs) {
                    paths.add(str);
                }
                shenyuConfig.getFallback().setPaths(paths);
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getHealth().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "paths":
                String[] strs = paramValue.split(";");
                List<String> paths = new LinkedList<>();
                for (String str : strs) {
                    paths.add(str);
                }
                shenyuConfig.getHealth().setPaths(paths);
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getFile().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "maxsize":
                shenyuConfig.getFile().setMaxSize(Integer.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "local":
                shenyuConfig.getSwitchConfig().setLocal(Boolean.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getUpstreamCheck().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "timeout":
                shenyuConfig.getUpstreamCheck().setTimeout(Integer.valueOf(paramValue));
                break;
            case "healthythreshold":
                shenyuConfig.getUpstreamCheck().setHealthyThreshold(Integer.valueOf(paramValue));
                break;
            case "unhealthythreshold":
                shenyuConfig.getUpstreamCheck().setUnhealthyThreshold(Integer.valueOf(paramValue));
                break;
            case "interval":
                shenyuConfig.getUpstreamCheck().setInterval(Integer.valueOf(paramValue));
                break;
            case "printenabled":
                shenyuConfig.getUpstreamCheck().setPrintEnabled(Boolean.valueOf(paramValue));
                break;
            case "printinterval":
                shenyuConfig.getUpstreamCheck().setPrintInterval(Integer.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getCross().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "allowedheaders":
                shenyuConfig.getCross().setAllowedHeaders(paramValue);
                break;
            case "allowedmethods":
                shenyuConfig.getCross().setAllowedMethods(paramValue);
                break;
            case "allowedanyorigin":
                shenyuConfig.getCross().setAllowedAnyOrigin(Boolean.valueOf(paramValue));
                break;
            case "maxage":
                shenyuConfig.getCross().setMaxAge(paramValue);
                break;
            case "allowcredentials":
                shenyuConfig.getCross().setAllowCredentials(Boolean.valueOf(paramValue));
                break;
            case "AllowedOriginConfig":
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
        pathIdx++;
        String[] strs;
        switch (paramPath[pathIdx]) {
            case "spacer":
                shenyuConfig.getCross().getAllowedOrigin().setSpacer(paramValue);
                break;
            case "domain":
                shenyuConfig.getCross().getAllowedOrigin().setDomain(paramValue);
                break;
            case "prefixes":
                Set prefixes = new HashSet<>();
                strs = paramValue.split(";");
                for (String str : strs) {
                    prefixes.add(str);
                }
                shenyuConfig.getCross().getAllowedOrigin().setPrefixes(prefixes);
                break;
            case "origins":
                Set origins = new HashSet<>();
                strs = paramValue.split(";");
                for (String str : strs) {
                    origins.add(str);
                }
                shenyuConfig.getCross().getAllowedOrigin().setOrigins(origins);
                break;
            case "originregex":
                shenyuConfig.getCross().getAllowedOrigin().setOriginRegex(paramValue);
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getInstance().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "registertype":
                shenyuConfig.getInstance().setRegisterType(paramValue);
                break;
            case "serverlists":
                shenyuConfig.getInstance().setServerLists(paramValue);
                break;
            case "props":
                Properties props = new Properties();
                for (String str : paramValue.split(";")) {
                    String[] strs = str.split(":");
                    props.setProperty(strs[0], strs[1]);
                }
                shenyuConfig.getInstance().setProps(props);
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "serverlistrefreshinterval":
                shenyuConfig.getRibbon().setServerListRefreshInterval(Integer.valueOf(paramValue));
                break;
            default:
        }
    }
    
    /**
     * Function for updating the config parameters for ShenyuConfig.
     *
     * @param shenyuConfig The obejct waiting for update
     */
    public static void updateLocalConfig(final ShenyuConfig shenyuConfig) {
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enabled":
                shenyuConfig.getLocal().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "sha512key":
                shenyuConfig.getLocal().setSha512Key(paramValue);
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "maxframepayloadsize":
                shenyuConfig.getWebsocket().setMaxFramePayloadSize(Integer.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enable":
                shenyuConfig.getSharedPool().setEnable(Boolean.valueOf(paramValue));
                break;
            case "prefix":
                shenyuConfig.getSharedPool().setPrefix(paramValue);
                break;
            case "corepoolsize":
                shenyuConfig.getSharedPool().setCorePoolSize(Integer.valueOf(paramValue));
                break;
            case "maximumpoolsize":
                shenyuConfig.getSharedPool().setMaximumPoolSize(Integer.valueOf(paramValue));
                break;
            case "keepalivetime":
                shenyuConfig.getSharedPool().setKeepAliveTime(Long.valueOf(paramValue));
                break;
            case "maxworkqueuememory":
                shenyuConfig.getSharedPool().setMaxWorkQueueMemory(Long.valueOf(paramValue));
                break;
            case "maxfreememory":
                shenyuConfig.getSharedPool().setMaxFreeMemory(Integer.valueOf(paramValue));
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
        pathIdx++;
        switch (paramPath[pathIdx]) {
            case "enable":
                shenyuConfig.getMetrics().setEnabled(Boolean.valueOf(paramValue));
                break;
            case "name":
                shenyuConfig.getMetrics().setName(paramValue);
                break;
            case "host":
                shenyuConfig.getMetrics().setHost(paramValue);
                break;
            case "port":
                shenyuConfig.getMetrics().setPort(Integer.valueOf(paramValue));
                break;
            case "jmxconfig":
                shenyuConfig.getMetrics().setJmxConfig(paramValue);
                break;
            case "props":
                Properties props = new Properties();
                for (String str : paramValue.split(";")) {
                    String[] strs = str.split(":");
                    props.setProperty(strs[0], strs[1]);
                }
                shenyuConfig.getMetrics().setProps(props);
                break;
            default:
        }
    }
}
