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

package cn.sliew.scaleph.api.controller.ws;

import cn.sliew.scaleph.api.annotation.Logging;
import cn.sliew.scaleph.engine.flink.kubernetes.resource.sessioncluster.FlinkSessionCluster;
import cn.sliew.scaleph.engine.flink.kubernetes.service.FlinkJobManagerEndpointService;
import cn.sliew.scaleph.engine.flink.kubernetes.service.WsFlinkKubernetesSessionClusterService;
import cn.sliew.scaleph.engine.flink.kubernetes.service.dto.WsFlinkKubernetesSessionClusterDTO;
import cn.sliew.scaleph.engine.flink.kubernetes.service.param.WsFlinkKubernetesSessionClusterListParam;
import cn.sliew.scaleph.engine.flink.kubernetes.service.param.WsFlinkKubernetesSessionClusterSelectListParam;
import cn.sliew.scaleph.system.model.ResponseVO;
import cn.sliew.scaleph.system.snowflake.exception.UidGenerateException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(tags = "Flink Kubernetes管理-Flink SessionCluster管理")
@RestController
@RequestMapping(path = "/api/flink/kubernetes/session-cluster")
public class WsFlinkKubernetesSessionClusterController {

    @Autowired
    private WsFlinkKubernetesSessionClusterService wsFlinkKubernetesSessionClusterService;
    @Autowired
    private FlinkJobManagerEndpointService flinkJobManagerEndpointService;

    @Logging
    @GetMapping
    @ApiOperation(value = "查询 SessionCluster 列表", notes = "分页查询 SessionCluster 列表")
    public ResponseEntity<Page<WsFlinkKubernetesSessionClusterDTO>> list(@Valid WsFlinkKubernetesSessionClusterListParam param) {
        Page<WsFlinkKubernetesSessionClusterDTO> page = wsFlinkKubernetesSessionClusterService.list(param);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Logging
    @GetMapping("all")
    @ApiOperation(value = "查询 SessionCluster 列表", notes = "查询 SessionCluster 列表")
    public ResponseEntity<List<WsFlinkKubernetesSessionClusterDTO>> listAll(@Valid WsFlinkKubernetesSessionClusterSelectListParam param) {
        List<WsFlinkKubernetesSessionClusterDTO> page = wsFlinkKubernetesSessionClusterService.listAll(param);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Logging
    @GetMapping("/{id}")
    @ApiOperation(value = "查询 SessionCluster", notes = "查询 SessionCluster")
    public ResponseEntity<ResponseVO<WsFlinkKubernetesSessionClusterDTO>> selectOne(@PathVariable("id") Long id) {
        WsFlinkKubernetesSessionClusterDTO dto = wsFlinkKubernetesSessionClusterService.selectOne(id);
        return new ResponseEntity(ResponseVO.success(dto), HttpStatus.OK);
    }

    @Logging
    @PostMapping("asYAML")
    @ApiOperation(value = "转换 SessionCluster", notes = "转换 SessionCluster")
    public ResponseEntity<ResponseVO<FlinkSessionCluster>> asYAML(@RequestBody WsFlinkKubernetesSessionClusterDTO dto) {
        FlinkSessionCluster sessionCluster = wsFlinkKubernetesSessionClusterService.asYAML(dto);
        return new ResponseEntity(ResponseVO.success(sessionCluster), HttpStatus.OK);
    }

    @Logging
    @GetMapping("fromTemplate")
    @ApiOperation(value = "转换 SessionCluster", notes = "转换 SessionCluster")
    public ResponseEntity<ResponseVO<WsFlinkKubernetesSessionClusterDTO>> fromTemplate(@RequestParam("templateId") Long templateId) {
        WsFlinkKubernetesSessionClusterDTO sessionCluster = wsFlinkKubernetesSessionClusterService.fromTemplate(templateId);
        return new ResponseEntity(ResponseVO.success(sessionCluster), HttpStatus.OK);
    }

    @Logging
    @PutMapping
    @ApiOperation(value = "新增 SessionCluster", notes = "新增 SessionCluster")
    public ResponseEntity<ResponseVO> insert(@Valid @RequestBody WsFlinkKubernetesSessionClusterDTO param) throws UidGenerateException {
        wsFlinkKubernetesSessionClusterService.insert(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @PostMapping
    @ApiOperation(value = "修改 SessionCluster", notes = "修改 SessionCluster")
    public ResponseEntity<ResponseVO> update(@Valid @RequestBody WsFlinkKubernetesSessionClusterDTO param) {
        wsFlinkKubernetesSessionClusterService.update(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除 SessionCluster", notes = "删除 SessionCluster")
    public ResponseEntity<ResponseVO> delete(@PathVariable("id") Long id) {
        wsFlinkKubernetesSessionClusterService.deleteById(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除 SessionCluster", notes = "批量删除 SessionCluster")
    public ResponseEntity<ResponseVO> deleteBatch(@RequestBody List<Long> ids) {
        wsFlinkKubernetesSessionClusterService.deleteBatch(ids);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @GetMapping("{id}/flinkui")
    @ApiOperation(value = "获取 flink-ui 链接", notes = "获取 flink-ui 链接")
    public ResponseEntity<ResponseVO<URI>> getFlinkUI(@PathVariable("id") Long id) throws Exception {
        URI endpoint = flinkJobManagerEndpointService.getSessionClusterJobManagerEndpoint(id);
        return new ResponseEntity<>(ResponseVO.success(endpoint), HttpStatus.OK);
    }

    @Logging
    @PostMapping("deploy/{id}")
    @ApiOperation(value = "启动 SessionCluster", notes = "启动 SessionCluster")
    public ResponseEntity<ResponseVO> deploySessionCluster(@PathVariable("id") Long id) throws Exception {
        wsFlinkKubernetesSessionClusterService.deploy(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @PostMapping("shutdown/{id}")
    @ApiOperation(value = "关闭 SessionCluster", notes = "关闭 SessionCluster")
    public ResponseEntity<ResponseVO> shutdownSessionCluster(@PathVariable("id") Long id) throws Exception {
        wsFlinkKubernetesSessionClusterService.shutdown(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @GetMapping("{id}/status")
    @ApiOperation(value = "获取 SessionCluster 状态", notes = "获取 SessionCluster 状态")
    public ResponseEntity<ResponseVO<GenericKubernetesResource>> getSessionClusterStatus(@PathVariable("id") Long id) throws Exception {
        GenericKubernetesResource sessionCluster = wsFlinkKubernetesSessionClusterService.getStatusWithoutManagedFields(id);
        return new ResponseEntity<>(ResponseVO.success(sessionCluster), HttpStatus.OK);
    }
}
