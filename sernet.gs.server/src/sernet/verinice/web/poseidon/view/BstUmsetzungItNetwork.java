/*******************************************************************************
 * Copyright (c) 2017 Benjamin Weißenfels.
 *
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *     @author Benjamin Weißenfels <bw[at]sernet[dot]de> - initial API and implementation
 ******************************************************************************/
package sernet.verinice.web.poseidon.view;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.chart.BarChartModel;

import sernet.verinice.web.poseidon.services.ControlService;
import sernet.verinice.web.poseidon.services.strategy.GroupByStrategy;
import sernet.verinice.web.poseidon.services.strategy.GroupByStrategyNormalized;
import sernet.verinice.web.poseidon.services.strategy.GroupByStrategySum;

/**
 * @author Benjamin Weißenfels <bw[at]sernet[dot]de>
 *
 */
@ManagedBean(name = "bstUmsetzungItNetworkView")
public class BstUmsetzungItNetwork {

    private BarChartModel horizontalChartModel;

    private BarChartModel verticalChartModel;

    @ManagedProperty(value = "#{param.itNetwork}")
    private String itNetwork;

    @ManagedProperty(value = "#{param.scopeId}")
    private String scopeId;

    @ManagedProperty(value = "#{param.crunchStrategy}")
    private String crunchStrategy;

    @ManagedProperty("#{controlService}")
    private ControlService controlService;

    @PostConstruct
    public void init() {
        GroupByStrategy strategy = getStrategy();
        Map<String, Map<String, Number>> data = controlService.groupByMassnahmenStates(scopeId, strategy);
        ModulChartsFactory chartModelFactory = new ModulChartsFactory(data);
        horizontalChartModel = chartModelFactory.getHorizontalBarChartModel();
        verticalChartModel = chartModelFactory.getVerticalBarChartModel();
    }

    private GroupByStrategy getStrategy() {
        if (GroupByStrategySum.GET_PARAM_IDENTIFIER.equals(crunchStrategy)) {
            return new GroupByStrategySum();
        } else if (GroupByStrategyNormalized.GET_PARAM_IDENTIFIER.equals(crunchStrategy)) {
            return new GroupByStrategyNormalized();
        } else {
            return new GroupByStrategySum();
        }
    }

    public ControlService getControlService() {
        return controlService;
    }

    public void setControlService(ControlService controlService) {
        this.controlService = controlService;
    }

    public BarChartModel getVerticalChartModel() {
        return verticalChartModel;
    }

    public void setVerticalChartModel(BarChartModel verticalChartModel) {
        this.verticalChartModel = verticalChartModel;
    }

    public BarChartModel getHorizontalChartModel() {
        return horizontalChartModel;
    }

    public void setHorizontalChartModel(BarChartModel horizontalChartModel) {
        this.horizontalChartModel = horizontalChartModel;
    }

    public String getItNetwork() {
        return itNetwork;
    }

    public void setItNetwork(String itNetwork) {
        this.itNetwork = itNetwork;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getCrunchStrategy() {
        return crunchStrategy;
    }

    public void setCrunchStrategy(String crunchStrategy) {
        this.crunchStrategy = crunchStrategy;
    }
}
