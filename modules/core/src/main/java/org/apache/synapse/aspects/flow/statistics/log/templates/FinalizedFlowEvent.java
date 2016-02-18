/*
 *   Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.synapse.aspects.flow.statistics.log.templates;

import org.apache.synapse.MessageContext;
import org.apache.synapse.aspects.flow.statistics.collectors.RuntimeStatisticCollector;
import org.apache.synapse.aspects.flow.statistics.data.raw.StatisticDataUnit;
import org.apache.synapse.aspects.flow.statistics.log.StatisticsReportingEvent;
import org.apache.synapse.aspects.flow.statistics.util.StatisticsConstants;

/**
 * Try to finish message flow, unless callback or open logs exists
 */
public class FinalizedFlowEvent implements StatisticsReportingEvent {

	private final StatisticDataUnit statisticDataUnit;

	public FinalizedFlowEvent(MessageContext messageContext, long endTime) {
		String statisticId = (String) messageContext.getProperty(StatisticsConstants.FLOW_STATISTICS_ID);
		statisticDataUnit = new StatisticDataUnit(statisticId, messageContext.getEnvironment(), endTime);
	}

	@Override
	public void process() {
		RuntimeStatisticCollector.closeStatisticEntry(statisticDataUnit, StatisticsConstants.ATTEMPT_TO_CLOSE);
	}
}
