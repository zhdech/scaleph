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

package cn.sliew.scaleph.engine.sql.service.dto;

import cn.sliew.scaleph.common.dict.common.YesOrNo;
import cn.sliew.scaleph.common.dict.flink.FlinkVersion;
import cn.sliew.scaleph.system.model.BaseDTO;
import cn.sliew.scaleph.project.service.dto.WsFlinkArtifactDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "WsFlinkArtifactSql对象", description = "flink artifact sql")
public class WsFlinkArtifactSqlDTO extends BaseDTO {

    @ApiModelProperty("作业artifact")
    private WsFlinkArtifactDTO wsFlinkArtifact;

    @ApiModelProperty("flink版本")
    private FlinkVersion flinkVersion;
    
    @ApiModelProperty("script")
    private String script;

    @ApiModelProperty("`current`")
    private YesOrNo current;
}
