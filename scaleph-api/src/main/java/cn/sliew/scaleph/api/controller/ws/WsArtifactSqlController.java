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
import cn.sliew.scaleph.common.exception.ScalephException;
import cn.sliew.scaleph.engine.sql.service.WsFlinkArtifactSqlService;
import cn.sliew.scaleph.engine.sql.service.dto.WsFlinkArtifactSqlDTO;
import cn.sliew.scaleph.engine.sql.service.param.*;
import cn.sliew.scaleph.system.snowflake.exception.UidGenerateException;
import cn.sliew.scaleph.system.vo.ResponseVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = "Flink管理-artifact-sql")
@RestController
@RequestMapping(path = "/api/flink/artifact/sql")
public class WsArtifactSqlController {

    @Autowired
    private WsFlinkArtifactSqlService wsFlinkArtifactSqlService;

    @Logging
    @GetMapping
    @ApiOperation(value = "查询 artifact sql 列表", notes = "查询 artifact sql 列表")
    public ResponseEntity<Page<WsFlinkArtifactSqlDTO>> list(@Valid WsFlinkArtifactSqlParam param) {
        Page<WsFlinkArtifactSqlDTO> result = wsFlinkArtifactSqlService.list(param);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Logging
    @GetMapping("/all")
    @ApiOperation(value = "根据 id 查询 artifact sql 列表", notes = "根据 id 查询 artifact sql 列表")
    public ResponseEntity<List<WsFlinkArtifactSqlDTO>> listAllByArtifact(@RequestParam("id") Long id) {
        final List<WsFlinkArtifactSqlDTO> result = wsFlinkArtifactSqlService.listAllByArtifact(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Logging
    @GetMapping("/page")
    @ApiOperation(value = "根据 id 分页查询 artifact sql 列表", notes = "根据 id 分页查询 artifact sql 列表")
    public ResponseEntity<Page<WsFlinkArtifactSqlDTO>> listByArtifact(@Valid WsFlinkArtifactSqlHistoryParam param) {
        final Page<WsFlinkArtifactSqlDTO> result = wsFlinkArtifactSqlService.listByArtifact(param);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Logging
    @GetMapping("/{id}")
    @ApiOperation(value = "查询 artifact sql 详情", notes = "查询 artifact sql 详情")
    public ResponseEntity<WsFlinkArtifactSqlDTO> selectOne(@PathVariable("id") Long id) {
        WsFlinkArtifactSqlDTO result = wsFlinkArtifactSqlService.selectOne(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Logging
    @PutMapping
    @ApiOperation(value = "新增 artifact sql", notes = "新增 artifact sql")
    public ResponseEntity<ResponseVO> insert(@RequestBody @Valid WsFlinkArtifactSqlInsertParam param) throws IOException, UidGenerateException {
        wsFlinkArtifactSqlService.insert(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @PostMapping
    @ApiOperation(value = "修改 artifact sql", notes = "修改 artifact sql")
    public ResponseEntity<ResponseVO> update(@RequestBody @Valid WsFlinkArtifactSqlUpdateParam param) {
        wsFlinkArtifactSqlService.update(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @PostMapping("script")
    @ApiOperation(value = "修改 artifact sql", notes = "修改 artifact sql")
    public ResponseEntity<ResponseVO> updateScript(@RequestBody @Valid WsFlinkArtifactSqlScriptUpdateParam param) {
        wsFlinkArtifactSqlService.updateScript(param);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除 artifact sql", notes = "删除 artifact sql")
    public ResponseEntity<ResponseVO> delete(@PathVariable("id") Long id) throws ScalephException, IOException {
        wsFlinkArtifactSqlService.deleteOne(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }

    @Logging
    @DeleteMapping("/all/{id}")
    @ApiOperation(value = "删除所有 artifact sql", notes = "删除所有 artifact sql")
    public ResponseEntity<ResponseVO> deleteAll(@PathVariable("id") Long id) throws ScalephException {
        wsFlinkArtifactSqlService.deleteAll(id);
        return new ResponseEntity<>(ResponseVO.success(), HttpStatus.OK);
    }
}