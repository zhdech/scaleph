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

package cn.sliew.scaleph.plugin.file.fetcher.core;

import cn.sliew.scaleph.plugin.file.fetcher.core.cli.FetchOptions;
import cn.sliew.scaleph.plugin.file.fetcher.core.cli.OptionsParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FetcherExecutor implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CommandLine line = OptionsParser.parse(new Options(), args.getSourceArgs(), true);
        FetchOptions options = new FetchOptions(line);

        Optional<FileFetcher> fileFetcher = FileFetcherFactory.find(options.getUri(), options.getProperties());
        fileFetcher.orElseThrow().fetch(options.getUri(), options.getPath());
    }
}