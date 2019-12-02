package com.soft863.controller;

import com.alibaba.fastjson.JSON;
import com.soft863.entity.JobInfo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-25 14:43
 **/
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public String create() {
        elasticsearchTemplate.createIndex (JobInfo.class);
        return "创建成功";
    }

    @RequestMapping(value = "/index", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete() {
        elasticsearchTemplate.deleteIndex (JobInfo.class);
        return "删除成功";
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public String insert() {
        JobInfo jobInfo = new JobInfo ();
        jobInfo.setId (1);
        jobInfo.setJobName ("Java开发工程师");
        jobInfo.setCity ("北京");
        jobInfo.setSalary (20000.0);
        jobInfo.setRemark ("这是一个很不错的行业");
        IndexQuery indexQuery = new IndexQueryBuilder ().withId (jobInfo.getId ().toString ())
                .withObject (jobInfo).build ();
        elasticsearchTemplate.index (indexQuery);
        return "插入成功";
    }

    @RequestMapping("/mul")
    @ResponseBody
    public String mulInsert() {
        List<IndexQuery> indexQueryList = new ArrayList<> ();
        List<JobInfo> jobInfoList = createInfos (1400);
        int counter = 0;
        for (JobInfo info : jobInfoList) {
            IndexQuery indexQuery = new IndexQuery ();
            indexQuery.setId (info.getId ().toString ());
            indexQuery.setSource (JSON.toJSONString (info));
            indexQuery.setIndexName ("jobinfo1");
            indexQuery.setType ("_doc");
            indexQueryList.add (indexQuery);

            if (counter % 500 == 0) {
                elasticsearchTemplate.bulkIndex (indexQueryList);
                indexQueryList.clear ();
                System.out.println ("提交了500条记录，当前条数：" + counter);
            }
            counter++;
        }
        if (indexQueryList.size () > 0) {
            elasticsearchTemplate.bulkIndex (indexQueryList);
        }
        elasticsearchTemplate.refresh (JobInfo.class);
        return "批量插入成功";
    }

    private List<JobInfo> createInfos(int num) {
        List<JobInfo> jobInfoList = new ArrayList<> ();
        JobInfo jobInfo = null;
        for (int i = 1; i <= num; i++) {
            jobInfo = new JobInfo ();
            jobInfo.setId (i);
            jobInfo.setJobName ("是Java开发工程师");
            if (i < 500) {
                jobInfo.setCity ("郑州");
            } else if (i < 1000) {
                jobInfo.setCity ("杭州行业");
            } else {
                jobInfo.setCity ("北京");
            }
            jobInfo.setSalary (Double.valueOf (Integer.valueOf (i)));
            jobInfo.setRemark ("这是一个很不错的行业" + i);
            jobInfoList.add (jobInfo);
        }

        return jobInfoList;
    }

    @DeleteMapping("/data")
    @ResponseBody
    public String deleteData() {
//        elasticsearchTemplate.delete (JobInfo.class,"1");

        //条件删除
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery ();
        queryBuilder.must (QueryBuilders.queryStringQuery ("郑州"));
        DeleteQuery deleteQuery = new DeleteQuery ();
        deleteQuery.setQuery (queryBuilder);
        deleteQuery.setIndex ("jobinfo1");
        deleteQuery.setType ("_doc");
        elasticsearchTemplate.delete (deleteQuery);
        return "删除成功";
    }

    @GetMapping("/data")
    @ResponseBody
    public String queryData() {

        //使用queryStringQuery完成单字符串查询
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery ("北京");
        //单个字段查询
        queryBuilder = QueryBuilders.matchQuery ("jobName", "大"); //字段区分大小写
        //multiMatchQuery 查询的值在多个字段中进行匹配
        queryBuilder = QueryBuilders.multiMatchQuery ("大", "jobName", "city");
        //term全等查询是不进行分词的
        queryBuilder = QueryBuilders.termQuery ("jobName", "大");
        //范围查询
        queryBuilder = QueryBuilders.rangeQuery ("salary").from ("0").to ("10");
        queryBuilder = QueryBuilders.rangeQuery ("salary").lt (20);
        //多条件查询
        queryBuilder = QueryBuilders
                .boolQuery ()
                .filter (QueryBuilders.termQuery ("jobName", "大数据"))
                .filter (QueryBuilders.termQuery ("salary", "1000"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder ()
                .withQuery (queryBuilder)
                .withPageable (PageRequest.of (0, 5))
                .build ();
        List<JobInfo> list = elasticsearchTemplate.queryForList (searchQuery, JobInfo.class);
        System.out.println (list.size ());
        for (JobInfo info : list) {
            System.out.println (info);
        }
        return "查询成功";
    }
}

