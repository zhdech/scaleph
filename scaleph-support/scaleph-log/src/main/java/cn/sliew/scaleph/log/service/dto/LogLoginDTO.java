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

package cn.sliew.scaleph.log.service.dto;

import java.util.Date;

import cn.sliew.scaleph.system.model.BaseDTO;
import cn.sliew.scaleph.system.service.vo.DictVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户登录登出日志
 * </p>
 *
 * @author liyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "登录日志信息", description = "用户登录登出日志")
public class LogLoginDTO extends BaseDTO {

    private static final long serialVersionUID = 722981712401825463L;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "登录类型 1-登录，2-登出，0-未知")
    private DictVO loginType;

    @ApiModelProperty(value = "客户端信息")
    private String clientInfo;

    @ApiModelProperty(value = "操作系统信息")
    private String osInfo;

    @ApiModelProperty(value = "浏览器信息")
    private String browserInfo;

    @ApiModelProperty(value = "接口执行信息，包含请求结果")
    private String actionInfo;


}
