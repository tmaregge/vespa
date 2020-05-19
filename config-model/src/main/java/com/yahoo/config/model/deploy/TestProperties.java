// Copyright 2019 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.config.model.deploy;

import com.google.common.collect.ImmutableList;
import com.yahoo.config.model.api.ApplicationRoles;
import com.yahoo.config.model.api.ConfigServerSpec;
import com.yahoo.config.model.api.ContainerEndpoint;
import com.yahoo.config.model.api.EndpointCertificateSecrets;
import com.yahoo.config.model.api.ModelContext;
import com.yahoo.config.provision.ApplicationId;
import com.yahoo.config.provision.AthenzDomain;
import com.yahoo.config.provision.HostName;
import com.yahoo.config.provision.Zone;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A test-only Properties class
 *
 * <p>Unfortunately this has to be placed in non-test source tree since lots of code already have test code (fix later)
 *
 * @author hakonhall
 */
public class TestProperties implements ModelContext.Properties {

    private boolean multitenant = false;
    private ApplicationId applicationId = ApplicationId.defaultId();
    private List<ConfigServerSpec> configServerSpecs = Collections.emptyList();
    private HostName loadBalancerName = null;
    private URI ztsUrl = null;
    private String athenzDnsSuffix = null;
    private boolean hostedVespa = false;
    private Zone zone;
    private Set<ContainerEndpoint> endpoints = Collections.emptySet();
    private boolean useDedicatedNodeForLogserver = false;
    private boolean useAdaptiveDispatch = false;
    private double topKProbability = 1.0;
    private boolean useDistributorBtreeDb = false;
    private boolean useThreePhaseUpdates = false;
    private double defaultTermwiseLimit = 1.0;
    private double softStartSeconds = 0.0;
    private double threadPoolSizeFactor = 0.0;
    private double queueSizeFactor = 0.0;
    private Optional<EndpointCertificateSecrets> endpointCertificateSecrets = Optional.empty();
    private AthenzDomain athenzDomain;
    private ApplicationRoles applicationRoles;

    @Override public boolean multitenant() { return multitenant; }
    @Override public ApplicationId applicationId() { return applicationId; }
    @Override public List<ConfigServerSpec> configServerSpecs() { return configServerSpecs; }
    @Override public HostName loadBalancerName() { return loadBalancerName; }
    @Override public URI ztsUrl() { return ztsUrl; }
    @Override public String athenzDnsSuffix() { return athenzDnsSuffix; }
    @Override public boolean hostedVespa() { return hostedVespa; }
    @Override public Zone zone() { return zone; }
    @Override public Set<ContainerEndpoint> endpoints() { return endpoints; }

    @Override public boolean isBootstrap() { return false; }
    @Override public boolean isFirstTimeDeployment() { return false; }
    @Override public boolean useAdaptiveDispatch() { return useAdaptiveDispatch; }
    @Override public boolean useDedicatedNodeForLogserver() { return useDedicatedNodeForLogserver; }
    @Override public Optional<EndpointCertificateSecrets> endpointCertificateSecrets() { return endpointCertificateSecrets; }
    @Override public double defaultTermwiseLimit() { return defaultTermwiseLimit; }

    @Override
    public double threadPoolSizeFactor() {
        return threadPoolSizeFactor;
    }

    @Override
    public double queueSizeFactor() {
        return queueSizeFactor;
    }

    @Override
    public double defaultSoftStartSeconds() {
        return softStartSeconds;
    }

    @Override public double defaultTopKProbability() { return topKProbability; }
    @Override public boolean useDistributorBtreeDb() { return useDistributorBtreeDb; }
    @Override public boolean useThreePhaseUpdates() { return useThreePhaseUpdates; }
    @Override public Optional<AthenzDomain> athenzDomain() { return Optional.ofNullable(athenzDomain); }
    @Override public Optional<ApplicationRoles> applicationRoles() { return Optional.ofNullable(applicationRoles); }

    public TestProperties setDefaultTermwiseLimit(double limit) {
        defaultTermwiseLimit = limit;
        return this;
    }

    public TestProperties setTopKProbability(double probability) {
        topKProbability = probability;
        return this;
    }

    public TestProperties setUseDistributorBtreeDB(boolean useBtreeDb) {
        useDistributorBtreeDb = useBtreeDb;
        return this;
    }

    public TestProperties setUseThreePhaseUpdates(boolean useThreePhaseUpdates) {
        this.useThreePhaseUpdates = useThreePhaseUpdates;
        return this;
    }

    public TestProperties setSoftStartSeconds(double softStartSeconds) {
        this.softStartSeconds = softStartSeconds;
        return this;
    }
    public TestProperties setThreadPoolSizeFactor(double threadPoolSizeFactor) {
        this.threadPoolSizeFactor = threadPoolSizeFactor;
        return this;
    }

    public TestProperties setQueueSizeFactor(double queueSizeFactor) {
        this.queueSizeFactor = queueSizeFactor;
        return this;
    }
    public TestProperties setApplicationId(ApplicationId applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public TestProperties setHostedVespa(boolean hostedVespa) {
        this.hostedVespa = hostedVespa;
        return this;
    }

    public TestProperties setUseAdaptiveDispatch(boolean useAdaptiveDispatch) {
        this.useAdaptiveDispatch = useAdaptiveDispatch;
        return this;
    }

    public TestProperties setMultitenant(boolean multitenant) {
        this.multitenant = multitenant;
        return this;
    }

    public TestProperties setConfigServerSpecs(List<Spec> configServerSpecs) {
        this.configServerSpecs = ImmutableList.copyOf(configServerSpecs);
        return this;
    }

    public TestProperties setUseDedicatedNodeForLogserver(boolean useDedicatedNodeForLogserver) {
        this.useDedicatedNodeForLogserver = useDedicatedNodeForLogserver;
        return this;
    }

    public TestProperties setEndpointCertificateSecrets(Optional<EndpointCertificateSecrets> endpointCertificateSecrets) {
        this.endpointCertificateSecrets = endpointCertificateSecrets;
        return this;
    }

    public TestProperties setZone(Zone zone) {
        this.zone = zone;
        return this;
    }

    public TestProperties setAthenzDomain(AthenzDomain domain) {
        this.athenzDomain = domain;
        return this;
    }

    public TestProperties setApplicationRoles(ApplicationRoles applicationRoles) {
        this.applicationRoles = applicationRoles;
        return this;
    }

    public static class Spec implements ConfigServerSpec {

        private final String hostName;
        private final int configServerPort;
        private final int zooKeeperPort;

        public String getHostName() {
            return hostName;
        }

        public int getConfigServerPort() {
            return configServerPort;
        }

        public int getZooKeeperPort() {
            return zooKeeperPort;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof ConfigServerSpec) {
                ConfigServerSpec other = (ConfigServerSpec)o;

                return hostName.equals(other.getHostName()) &&
                        configServerPort == other.getConfigServerPort() &&
                        zooKeeperPort == other.getZooKeeperPort();
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return hostName.hashCode();
        }

        public Spec(String hostName, int configServerPort, int zooKeeperPort) {
            this.hostName = hostName;
            this.configServerPort = configServerPort;
            this.zooKeeperPort = zooKeeperPort;
        }
    }

}
