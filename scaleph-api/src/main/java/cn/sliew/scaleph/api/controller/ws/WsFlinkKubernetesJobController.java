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
import cn.sliew.scaleph.engine.flink.kubernetes.service.WsFlinkKubernetesJobService;
import cn.sliew.scaleph.engine.flink.kubernetes.service.dto.WsFlinkKubernetesJobDTO;
import cn.sliew.scaleph.engine.flink.kubernetes.service.param.WsFlinkKubernetesJobAddParam;
import cn.sliew.scaleph.engine.flink.kubernetes.service.param.WsFlinkKubernetesJobListParam;
import cn.sliew.scaleph.engine.flink.kubernetes.service.param.WsFlinkKubernetesJobUpdateParam;
import cn.sliew.scaleph.system.model.ResponseVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Flink Kubernetes管理-Job管理")
@RestController
@RequestMapping(path = "/api/flink/kubernetes/job")
public class WsFlinkKubernetesJobController {

    @Autowired
    private WsFlinkKubernetesJobService wsFlinkKubernetesJobService;

    @Logging
    @GetMapping
    @ApiOperation(value = "查询 Job 列表", notes = "分页查询 Job 列表")
    public ResponseEntity<Page<WsFlinkKubernetesJobDTO>> list(@Valid WsFlinkKubernetesJobListParam param) {
        Page<WsFlinkKubernetesJobDTO> page = wsFlinkKubernetesJobService.list(param);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @Logging
    @GetMapping("/{id}")
    @ApiOperation(value = "查询 Job", notes = "查询 Job")
    public ResponseEntity<ResponseVO<WsFlinkKubernetesJobDTO>> selectOne(@PathVariable("id") Long id) {
        WsFlinkKubernetesJobDTO dto = wsFlinkKubernetesJobService.selectOne(id);
        return new ResponseEntity(ResponseVO.success(dto), HttpStatus.OK);
    }

    @Logging
    @GetMapping("/asYaml/{id}")
    @ApiOperation(value = "查询 YAML 格式 Job", notes = "查询 YAML 格式 Job")
    public ResponseEntity<ResponseVO<Object>> asYaml(@PathVariable("id") Long id) {
        Object dto = wsFlinkKubernetesJobService.asYaml(id);
        return new ResponseEntity(ResponseVO.success(dto), HttpStatus.OK);
    }

    @Logging
    @PutMapping
    @ApiOperation(value = "创建 Job", notes = "创建 Job")
    public ResponseEntity<ResponseVO> insert(@RequestBody @Valid WsFlinkKubernetesJobAddParam param) {
        wsFlinkKubernetesJobService.insert(param);
        return new ResponseEntity(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @PostMapping
    @ApiOperation(value = "更新 Job", notes = "更新 Job")
    public ResponseEntity<ResponseVO> update(@RequestBody @Valid WsFlinkKubernetesJobUpdateParam param) {
        wsFlinkKubernetesJobService.update(param);
        return new ResponseEntity(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("{id}")
    @ApiOperation(value = "删除 Job", notes = "删除 Job")
    public ResponseEntity<ResponseVO> delete(@PathVariable("id") Long id) {
        wsFlinkKubernetesJobService.deleteById(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("batch")
    @ApiOperation(value = "批量删除 Job", notes = "批量删除 Job")
    public ResponseEntity<ResponseVO> deleteBatch(@RequestBody List<Long> ids) {
        wsFlinkKubernetesJobService.deleteBatch(ids);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }
}
