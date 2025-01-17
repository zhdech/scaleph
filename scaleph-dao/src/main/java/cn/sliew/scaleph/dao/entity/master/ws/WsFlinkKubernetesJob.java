/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.sliew.scaleph.dao.entity.master.ws;

import cn.sliew.scaleph.common.dict.flink.FlinkDeploymentMode;
import cn.sliew.scaleph.common.dict.flink.FlinkJobType;
import cn.sliew.scaleph.common.dict.flink.FlinkRuntimeExecutionMode;
import cn.sliew.scaleph.common.dict.flink.kubernetes.DeploymentKind;
import cn.sliew.scaleph.dao.entity.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * flink kubernetes job
 * </p>
 */
@Data
@TableName("ws_flink_kubernetes_job")
@ApiModel(value = "WsFlinkKubernetesJob对象", description = "flink kubernetes job")
public class WsFlinkKubernetesJob extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableField("project_id")
    private Long projectId;

    @TableField("`name`")
    private String name;

    @TableField("job_id")
    private String jobId;

    @TableField("execution_mode")
    private FlinkRuntimeExecutionMode executionMode;

    @TableField("deployment_kind")
    private DeploymentKind deploymentKind;

    @TableField("flink_deployment_id")
    private Long flinkDeploymentId;

    @TableField(exist = false)
    private WsFlinkKubernetesDeployment flinkDeployment;

    @TableField("flink_session_cluster_id")
    private Long flinkSessionClusterId;

    @TableField(exist = false)
    private WsFlinkKubernetesSessionCluster flinkSessionCluster;

    @TableField("`type`")
    private FlinkJobType type;

    @TableField("flink_artifact_jar_id")
    private Long flinkArtifactJarId;

    @TableField(exist = false)
    private WsFlinkArtifactJar flinkArtifactJar;

    @TableField("flink_artifact_sql_id")
    private Long flinkArtifactSqlId;

    @TableField(exist = false)
    private WsFlinkArtifactSql flinkArtifactSql;

    @TableField("ws_di_job_id")
    private Long wsDiJobId;

    @TableField(exist = false)
    private WsDiJob wsDiJob;

    @TableField("remark")
    private String remark;

}
